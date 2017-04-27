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
public class GerenciadorEquipamento {

    private final String NOME_ARQUIVO = "equipamentos2.dat";

    private RandomAccessFile arquivoAcessoAleatorio;

    public GerenciadorEquipamento() {
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
            System.out.println("Erro ao abrir o arquivo!");
        }
    }

    public void fecharArquivo() {
        try {
            arquivoAcessoAleatorio.close();
        } catch (IOException e) {
            System.out.println("Erro ao fechar o arquivo");
        }
    }

    public boolean inserir(Equipamento equipamento) {
        if(pesquisar(equipamento.getCodigo())==null){ 
        try {
            arquivoAcessoAleatorio.seek(arquivoAcessoAleatorio.length());
            escreveRegistro(arquivoAcessoAleatorio, equipamento);
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao inserir");
            return false;
        }
        }
        else{
            JOptionPane.showConfirmDialog(null, "Código '" +equipamento.getCodigo() + "' já existe!");
            return false;
        }
        
}

    public void escreveRegistro(RandomAccessFile arquivo, Equipamento equipamento) throws IOException {
        arquivo.writeInt(equipamento.getCodigo());
        escrevePalavra(arquivo, equipamento.getNomeEquipamento(), 20);
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

    public ArrayList<Equipamento> listar() {
        Equipamento equipamento = null;
        ArrayList<Equipamento> equipamentos = new ArrayList<>();
        try {
            arquivoAcessoAleatorio.seek(0);
            do {
                equipamento = leRegistro(arquivoAcessoAleatorio);
                equipamentos.add(equipamento);
            } while (arquivoAcessoAleatorio.getFilePointer() < arquivoAcessoAleatorio.length());
        } catch (EOFException e) {

        } catch (IOException e) {

        }
        return equipamentos;
    }

    private Equipamento leRegistro(RandomAccessFile arquivo) throws IOException {
        Equipamento equipamento = new Equipamento();
        equipamento.setCodigo(arquivo.readInt());
        equipamento.setNomeEquipamento(montaPalavra(arquivo, 20).trim());
        return equipamento;
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

    public Equipamento pesquisar(int codigo) {
        Equipamento equipamento = null;
        try {
            arquivoAcessoAleatorio.seek(0); //Posiciona no inicio do arquivo
            do {
                equipamento = leRegistro(arquivoAcessoAleatorio);
                if (equipamento.getCodigo() == codigo) {
                    return equipamento;
                }
            } while ((arquivoAcessoAleatorio.getFilePointer() < arquivoAcessoAleatorio.length()));
        } catch (EOFException e) {

        } catch (IOException e) {

        }
        return null;
    }

    public boolean atualizar(Equipamento equipamento) {
        int pos = 0;
        try {
            pos = procurarPosicao(equipamento);
            if (pos != -1) {
                arquivoAcessoAleatorio.seek(pos * getTamanhoRegistro());
                escreveRegistro(arquivoAcessoAleatorio, equipamento);
                return true;
            }
        } catch (EOFException e) {

        } catch (IOException e) {

        }
        return false;
    }

    public int procurarPosicao(Equipamento p) {
        int cont = 0;
        Equipamento equipamento = null;
        try {
            arquivoAcessoAleatorio.seek(0); //Posiciona no inicio do arquivo
            do {
                equipamento = leRegistro(arquivoAcessoAleatorio);
                if (equipamento.getCodigo() == p.getCodigo()) {
                    return cont;
                }
                cont = cont + 1;
            } while ((arquivoAcessoAleatorio.getFilePointer() < arquivoAcessoAleatorio.length()));
        } catch (EOFException e) {

        } catch (IOException e) {

        }
        return -1;
    }

    public boolean excluir(Equipamento equipamento) {
        int ultimo = getQuantidadeRegistro();
        Equipamento ultimoEquipamento= null;
        try {
            if (ultimo != -1) {
                arquivoAcessoAleatorio.seek((ultimo - 1) * getTamanhoRegistro());
                ultimoEquipamento= leRegistro(arquivoAcessoAleatorio);
                int pos = 0;
                pos = procurarPosicao(equipamento);
                if (pos != -1) {
                    arquivoAcessoAleatorio.seek(pos * getTamanhoRegistro());
                    escreveRegistro(arquivoAcessoAleatorio, ultimoEquipamento);
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
        // 20  nome 
        return (4 + 40);
    }

    public int getQuantidadeRegistro() {
        int cont = 0;
        try {
            Equipamento p = null;
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
