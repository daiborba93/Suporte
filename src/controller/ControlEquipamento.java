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
import model.Equipamento;
import model.GerenciadorEquipamento;
import view.FCadEquipamento;
import view.FPesqEquipamento;
import view.ModelEquipamento;


/**
 *
 * @author Daiane
 */
public class ControlEquipamento {

    private FCadEquipamento fCadEquipamento;
    private FPesqEquipamento fPesqEquipamento;
    //private DaoEquipamento gerente;
    private GerenciadorEquipamento gerente;
    private ModelEquipamento modelEquipamento;
    private Equipamento equipamentoAtual;

    public ControlEquipamento() {
        this.fCadEquipamento = new FCadEquipamento(null, true);
        this.fPesqEquipamento = new FPesqEquipamento(null, true);
        this.gerente = new GerenciadorEquipamento();
        this.modelEquipamento = new ModelEquipamento();
        this.equipamentoAtual = null;
        inicializaComponentes();
    }

    private void inicializaComponentes() {
        fPesqEquipamento.tbEquipamentos.setModel(modelEquipamento);

        fCadEquipamento.btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gravar();
            }
        });

        fCadEquipamento.btLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });

        fCadEquipamento.btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });

        fPesqEquipamento.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarEquipamento();
            }
        });

        fPesqEquipamento.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
    }

    private void gravar() {
        if (equipamentoAtual == null) {
            Equipamento e = new Equipamento(Integer.parseInt(fCadEquipamento.edCodigo.getText()), fCadEquipamento.edNomeEquipamento.getText());
         
            if (gerente.inserir(e)) {
                JOptionPane.showMessageDialog(fCadEquipamento, "Equipamento inserido com sucesso");
                limpar();
            } 
        } else {
            Equipamento e = new Equipamento(Integer.parseInt(fCadEquipamento.edCodigo.getText()), fCadEquipamento.edNomeEquipamento.getText());
            if (gerente.atualizar(e)) {
                JOptionPane.showMessageDialog(fCadEquipamento, "Equipamento editado com sucesso");
                limpar();
                fCadEquipamento.btLimpar.setEnabled(true);
                fCadEquipamento.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(fCadEquipamento, "Erro ao editar equipamento");
            }
        }
    }

    private void editarEquipamento() {
        int posicao = fPesqEquipamento.tbEquipamentos.getSelectedRow();
        if (posicao >= 0) {
            equipamentoAtual = modelEquipamento.getEquipamento(posicao);
            fCadEquipamento.edCodigo.setText(Integer.toString(equipamentoAtual.getCodigo()));
            fCadEquipamento.edNomeEquipamento.setText(equipamentoAtual.toString());           
            fCadEquipamento.btLimpar.setEnabled(false);
            fCadEquipamento.setVisible(true);
            carregarEquipamentoModel();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um equipamento");
        }
    }

    private void excluir() {
        int posicao = fPesqEquipamento.tbEquipamentos.getSelectedRow();
        if (posicao >= 0) {
            equipamentoAtual = modelEquipamento.getEquipamento(posicao);
            if (gerente.excluir(equipamentoAtual)) {
                JOptionPane.showMessageDialog(null, "Equipamento removido com sucesso");
                modelEquipamento.removeEquipamento(posicao);
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao remover equipamento");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um equipamento");
        }
    }

    private void cancelar() {
        limpar();
        fCadEquipamento.btLimpar.setEnabled(true);
        fCadEquipamento.setVisible(false);
    }

    private void limpar() {
        fCadEquipamento.edCodigo.setText(null);
        fCadEquipamento.edNomeEquipamento.setText(null);
        equipamentoAtual = null;
    }

    private void carregarEquipamentoModel() {
        modelEquipamento.limpar();
        ArrayList<Equipamento> equipamentos = gerente.listar();
        for (Equipamento e : equipamentos) {
            modelEquipamento.addEquipamento(e);
        }
    }

    public void cadastrarEquipamento() {
        limpar();
        fCadEquipamento.setVisible(true);
    }

    public void pesquisarEquipamento() {
        carregarEquipamentoModel();
        fPesqEquipamento.setVisible(true);
    }
}
