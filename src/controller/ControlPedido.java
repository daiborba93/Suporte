/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.DaoEquipamento;
import model.DaoPedido;
import model.Equipamento;
import model.GerenciadorEquipamento;
import model.GerenciadorPedido;
import model.Pedido;
import view.FCadPedido;
import view.FPesqPedido;
import view.ModelPedido;

/**
 *
 * @author Daiane
 */
public class ControlPedido {

    private FCadPedido fCadPedido;
    private FPesqPedido fPesqPedido;
   // private DaoPedido gerente; //DAO
    private GerenciadorPedido gerente; //RAF
   // private DaoEquipamento gerenteEquipamento; // DAO
    private GerenciadorEquipamento gerenteEquipamento; //RAF
    private ModelPedido modelPedido;
    private Pedido pedidoAtual;
     private ArrayList<Equipamento> equipamentos = new ArrayList<>();

    public ControlPedido() {
        fCadPedido = new FCadPedido(null, true);
        fPesqPedido = new FPesqPedido(null, true);
        gerente = new GerenciadorPedido(); //DAO
        //gerente= new GerenciadorPedido(); //RAF
        gerenteEquipamento= new GerenciadorEquipamento();
        //gerentePizza= new GerenciadorPizza();// RAF
        this.modelPedido = new ModelPedido();
        pedidoAtual = null;
        inicializaComponentes();
    }

    private void inicializaComponentes() {
        fPesqPedido.tbPedido.setModel(modelPedido);
        carregarEquipamento();

        fCadPedido.btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravar();
            }
        });

        fCadPedido.btLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });

        fCadPedido.btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });

        fPesqPedido.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPedido();
            }
        });

        fPesqPedido.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
    }

    private void gravar() {
        Equipamento e = (Equipamento) fCadPedido.cbEquipamento.getSelectedItem();
      if (pedidoAtual == null)  {
            Pedido p = new Pedido(fCadPedido.edNome.getText(), Integer.parseInt(fCadPedido.edCpf.getText()), fCadPedido.edRua.getText(), Integer.parseInt(fCadPedido.edNumero.getText()), fCadPedido.edBairro.getText(), e, fCadPedido.edDescricao.getText());
                  if (gerente.inserir(p)) {
                JOptionPane.showMessageDialog(fCadPedido, "Pedido inserido com sucesso");
                limpar();
                
        }
            
        } else {
            Pedido p = new Pedido(fCadPedido.edNome.getText(), Integer.parseInt(fCadPedido.edCpf.getText()), fCadPedido.edRua.getText(), Integer.parseInt(fCadPedido.edNumero.getText()), fCadPedido.edBairro.getText(), e, fCadPedido.edDescricao.getText());
            if (gerente.atualizar(p)) {
                JOptionPane.showMessageDialog(fCadPedido, "Pedido editado com sucesso");
                limpar();
                fCadPedido.btLimpar.setEnabled(true);
                fCadPedido.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(fCadPedido, "Erro ao editar pedido");
            }
        }
    }

    private void editarPedido() {
        int posicao = fPesqPedido.tbPedido.getSelectedRow();
        if (posicao >= 0) {
            pedidoAtual = modelPedido.getPedido(posicao);
            fCadPedido.edNome.setText(pedidoAtual.getNome());
            fCadPedido.edCpf.setText(Integer.toString(pedidoAtual.getCpf()));
            fCadPedido.edRua.setText(pedidoAtual.getRua());
            fCadPedido.edNumero.setText(Integer.toString(pedidoAtual.getNumero()));
            fCadPedido.edBairro.setText(pedidoAtual.getBairro());
            fCadPedido.cbEquipamento.setSelectedItem(pedidoAtual.getEquipamento());
            fCadPedido.edDescricao.setText(pedidoAtual.getDescricao());
            fCadPedido.edCpf.setEditable(false);
            fCadPedido.btLimpar.setEnabled(false);
            fCadPedido.setVisible(true);
            carregarPedidoModel();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Pedido");
        }
    }

    private void excluir() {
        int posicao = fPesqPedido.tbPedido.getSelectedRow();
        if (posicao >= 0) {
            pedidoAtual = modelPedido.getPedido(posicao);
            if (gerente.excluir(pedidoAtual)) {
                JOptionPane.showMessageDialog(null, "Pedido removido com sucesso");
                modelPedido.removePedido(posicao);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao remover Pedido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Pedido");
        }
    }

    private void cancelar() {
        limpar();
        fCadPedido.btLimpar.setEnabled(true);
        fCadPedido.setVisible(false);
    }

        private void limpar() {
        fCadPedido.edNome.setText(null);
        fCadPedido.edCpf.setText(null);
        fCadPedido.edRua.setText(null);
        fCadPedido.edNumero.setText(null);
        fCadPedido.edBairro.setText(null);
        fCadPedido.cbEquipamento.setSelectedItem(0);
        fCadPedido.edDescricao.setText(null);
        pedidoAtual = null;
    }

    private void carregarPedidoModel() {
        modelPedido.limpar();
        ArrayList<Pedido> pedidos = gerente.listar();
        for (Pedido p : pedidos) {
            modelPedido.addPedido(p);
        }
    }

private void carregarEquipamento() {
        fCadPedido.cbEquipamento.removeAllItems();
       
        equipamentos = gerenteEquipamento.listar();
        for (Equipamento p : equipamentos) {
            fCadPedido.cbEquipamento.addItem(p);
        }
    }

    public void cadastrarPedido() {
        limpar();
        carregarEquipamento();
        if(equipamentos.size()!=0){
        fCadPedido.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(fCadPedido, "Sem equipamento cadastrado");
        }
    }

    public void pesquisarPedido() {
        carregarPedidoModel();
        fPesqPedido.setVisible(true);
    }
}
