/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import model.Equipamento;

/**
 *
 * @author Daiane
 */
public class ModelEquipamento extends AbstractTableModel {

    private ArrayList<Equipamento> pedidos = new ArrayList<Equipamento>();

    @Override
    public int getRowCount() {
        return pedidos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Equipamento equipamento = pedidos.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return equipamento.getCodigo();
            }

            default: {
                return equipamento.getNomeEquipamento();
            }

        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Codigo";
            }
            case 1: {
                return "Nome do Equipamento";
            }

        }
        return null;
    }

    public void limpar() {
        pedidos.clear();
    }

    public void removeEquipamento(int posicao) {
        pedidos.remove(posicao);
        fireTableRowsDeleted(posicao, posicao);
    }

    public Equipamento getEquipamento(int posicao) {
        return pedidos.get(posicao);
    }

    public void addEquipamento(Equipamento equipamento) {
        pedidos.add(equipamento);
        fireTableRowsInserted(pedidos.size() - 1, pedidos.size() - 1);
    }

}

