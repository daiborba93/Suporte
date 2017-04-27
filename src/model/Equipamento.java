/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Daiane
 */
public class Equipamento {
    private int codigo;
    private String nomeEquipamento;
     
    public Equipamento(int codigo, String nomeEquipamento){
        this.codigo = codigo;
        this.nomeEquipamento=nomeEquipamento;
    }
    
        Equipamento() {
    }
       

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    @Override
    public String toString() {
        return nomeEquipamento;
    }
    
    
    
}