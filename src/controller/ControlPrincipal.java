/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.FPrincipal;

/**
 *
 * @author Daiane
 */
public class ControlPrincipal {

    private FPrincipal fPrincipal;
    private ControlPedido controlPedido;
    private ControlEquipamento controlEquipamento;

    public ControlPrincipal() {
        fPrincipal = new FPrincipal();
        controlPedido = new ControlPedido();
        controlEquipamento = new ControlEquipamento();
        inicializaComponentes();
    }

    private void inicializaComponentes() {
        fPrincipal.mCadastroPedido.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarPedido();
            }
        });

        fPrincipal.mPesquisarPedido.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarPedido();
            }
        });

        fPrincipal.mCadastroEquipamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarEquipamento();
            }
        });
        
        fPrincipal.mPesquisarEquipamento.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarEquipamento();
            }
        });
    }

    private void cadastrarPedido() {
        controlPedido.cadastrarPedido();
    }

    private void pesquisarPedido() {
        controlPedido.pesquisarPedido();
    }

    private void cadastrarEquipamento() {
        controlEquipamento.cadastrarEquipamento();
    }
    
     private void pesquisarEquipamento(){
        controlEquipamento.pesquisarEquipamento();
    }

    public void executar() {
        fPrincipal.setVisible(true);
    }
}


