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
public class Pedido {
    private String nome;
    private int cpf;
    private String rua;
    private int numero;
    private String bairro;
    private Equipamento equipamento;
    private String descricao;
    
    
    public Pedido(String nome, int cpf, String rua, int numero, String bairro, Equipamento e, String descricao){
        this.nome = nome;
        this.cpf=cpf;
        this.rua=rua;
        this.numero=numero;
        this.bairro=bairro;
        this.equipamento=e;
        this.descricao=descricao;
        
    }

    Pedido() {
    }

  

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Pedido{" + "nome=" + nome + ", cpf=" + cpf + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", equipamento=" + equipamento + ", descricao=" + descricao + '}';
    }
}
