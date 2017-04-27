/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Pedido;

/**
 *
 * @author Daiane
 */
public class ModelPedido extends AbstractTableModel {

    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

    @Override
    public int getRowCount() {
        return pedidos.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pedido = pedidos.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return pedido.getNome();
            }
            case 1: {
                return pedido.getCpf();
            }
            case 2: {
                return pedido.getRua();
            }
            case 3: {
                return pedido.getNumero();
            }
            case 4: {
                return pedido.getBairro();
            }
             case 5: {
                return pedido.getEquipamento().getNomeEquipamento();
                
            }
              case 6: {
                return pedido.getDescricao();
            }
           
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
     switch (column) {
            case 0: {
                return "Nome";
            }
            case 1: {
                return "CPF";
            }
            case 2: {
                return "Rua";
            }
            case 3: {
                return "Númeo";
            }
            case 4: {
                return "Bairro";
            }
             case 5: {
                return "Equipamento";
            }
              case 6: {
                return "Descricao";
            }
            
            
        }
        return null;
    }

    public void limpar() {
        pedidos.clear();
    }

    public void removePedido(int posicao) {
        pedidos.remove(posicao);
        fireTableRowsDeleted(posicao, posicao);
    }

    public Pedido getPedido(int posicao) {
        return pedidos.get(posicao);
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
        fireTableRowsInserted(pedidos.size() - 1, pedidos.size() - 1);
    }

}

