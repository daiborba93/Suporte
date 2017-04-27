/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Daiane
 */
public class GerenciadorPedido {

    private final String NOME_ARQUIVO = "pedidos.dat";

    private RandomAccessFile arquivoAcessoAleatorio;

    public GerenciadorPedido() {
        abrirArquivo();
    }

    public void finalize() {
        fecharArquivo();
    }

    public void abrirArquivo() {
        try {
            File arquivo = new File(NOME_ARQUIVO);
            arquivoAcessoAleatorio = new RandomAccessFile(arquivo, "rw");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo!");
        }
    }

    public void fecharArquivo() {
        try {
            arquivoAcessoAleatorio.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar arquivo!");
        }
    }

    public boolean inserir(Pedido pedido) {
        if(pesquisar(pedido.getCpf())==null){ 
        try {
            arquivoAcessoAleatorio.seek(arquivoAcessoAleatorio.length());
            escreveRegistro(arquivoAcessoAleatorio, pedido);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            return false;
        }
        }
        else{
        JOptionPane.showMessageDialog(null, "CPF já cadastrado");
        return false;
        }
    }

    public void escreveRegistro(RandomAccessFile arquivo, Pedido pedido) throws IOException {
        escrevePalavra(arquivo, pedido.getNome(), 20);
        arquivo.writeInt(pedido.getCpf());
        escrevePalavra(arquivo, pedido.getRua(), 20);
        arquivo.writeInt(pedido.getNumero());
        escrevePalavra(arquivo, pedido.getBairro(), 20);
        arquivo.writeInt(pedido.getEquipamento().getCodigo());
        escrevePalavra(arquivo, pedido.getDescricao(), 25);
             }

    private void escrevePalavra(RandomAccessFile arquivo, String palavra, int tamanho) throws IOException {
        StringBuffer buf = null;
        if (palavra != null) {
            buf = new StringBuffer(palavra);
        } else {
            buf = new StringBuffer(tamanho);
        }
        buf.setLength(tamanho);
        arquivo.writeChars(buf.toString());
    }

    public ArrayList<Pedido> listar() {
        Pedido pedido;
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            arquivoAcessoAleatorio.seek(0);
            do {
                System.out.println("teste4");
                pedido = leRegistro(arquivoAcessoAleatorio);
                pedidos.add(pedido);
            } while (arquivoAcessoAleatorio.getFilePointer() < arquivoAcessoAleatorio.length());
        } catch (EOFException e) {

        } catch (IOException e) {

        }
        return pedidos;
    }

    private Pedido leRegistro(RandomAccessFile arquivo) throws IOException {
        Pedido pedido = new Pedido();
        pedido.setNome(montaPalavra(arquivo, 20).trim());
        pedido.setCpf(arquivo.readInt());
        pedido.setRua(montaPalavra(arquivo, 20).trim());
        pedido.setNumero(arquivo.readInt());
        pedido.setBairro(montaPalavra(arquivo, 20).trim());
        GerenciadorEquipamento gerenteEquipamento = new GerenciadorEquipamento();
        pedido.setEquipamento(gerenteEquipamento.pesquisar(arquivo.readInt()));
        pedido.getEquipamento().getCodigo();
        pedido.setDescricao(montaPalavra(arquivo, 25).trim());
        return pedido;
        
    }

    private String montaPalavra(RandomAccessFile arquivo, int tamanho) throws IOException {
        char palavra[] = new char[tamanho];
        char temp;
        for (int i = 0; i < palavra.length; i++) {
            temp = arquivo.readChar();
            palavra[i] = temp;
        }
        return new String(palavra).replace('\0', ' ');
    }

    public Pedido pesquisar(int cpf) {
        Pedido pedido = null;
        try {
            arquivoAcessoAleatorio.seek(0); //Posiciona no inicio do arquivo
            do {
                pedido = leRegistro(arquivoAcessoAleatorio);
                if (pedido.getCpf() == cpf) {
                    return pedido;
                }
            } while ((arquivoAcessoAleatorio.getFilePointer() < arquivoAcessoAleatorio.length()));
        } catch (EOFException e) {

        } catch (IOException e) {

        }
        return null;
    }

    public boolean atualizar(Pedido pedido) {
        int pos = 0;
        try {
            pos = procurarPosicao(pedido);
            if (pos != -1) {
                arquivoAcessoAleatorio.seek(pos * getTamanhoRegistro());
                escreveRegistro(arquivoAcessoAleatorio, pedido);
                return true;
            }
        } catch (EOFException e) {

        } catch (IOException e) {

        }
        return false;
    }

    public int procurarPosicao(Pedido p) {
        int cont = 0;
        Pedido pedido = null;
        try {
            arquivoAcessoAleatorio.seek(0); //Posiciona no inicio do arquivo
            do {
                pedido = leRegistro(arquivoAcessoAleatorio);
                if (pedido.getCpf() == p.getCpf()) {
                    return cont;
                }
                cont = cont + 1;
            } while ((arquivoAcessoAleatorio.getFilePointer() < arquivoAcessoAleatorio.length()));
        } catch (EOFException e) {

        } catch (IOException e) {

        }
        return -1;
    }

    public boolean excluir(Pedido pedido) {
        int ultimo = getQuantidadeRegistro();
        Pedido ultimoPedido = null;
        try {
            if (ultimo != -1) {
                arquivoAcessoAleatorio.seek((ultimo - 1) * getTamanhoRegistro());
                ultimoPedido = leRegistro(arquivoAcessoAleatorio);
                int pos = 0;
                pos = procurarPosicao(pedido);
                if (pos != -1) {
                    arquivoAcessoAleatorio.seek(pos * getTamanhoRegistro());
                    escreveRegistro(arquivoAcessoAleatorio, ultimoPedido);
                    arquivoAcessoAleatorio.setLength((getQuantidadeRegistro() - 1) * getTamanhoRegistro());
                    return true;
                }
            }
        } catch (EOFException e) {

        } catch (IOException e) {

        }
        return false;
    }

    public int getTamanhoRegistro() {
        // 20 nome + 20 rua + 20 bairro + 12 equipamento + 25 descrição + 4 cpf + 4 Número
        return (40 + 40 + 40 + 24 + 50 + 4 + 4);
    }

    public int getQuantidadeRegistro() {
        int cont = 0;
        try {
            Pedido p = null;
            arquivoAcessoAleatorio.seek(0);
            do {
                p = leRegistro(arquivoAcessoAleatorio);
                cont++;
            } while (arquivoAcessoAleatorio.getFilePointer() < arquivoAcessoAleatorio.length());
        } catch (IOException e) {

        }
        return cont;
    }
}