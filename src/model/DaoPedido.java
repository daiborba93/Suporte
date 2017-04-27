/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.ConexaoMySQL;

public class DaoPedido {

    public boolean inserir(Pedido pedido) {
        PreparedStatement comando = null;
        try {
            comando = ConexaoMySQL.getConexaoMySQL().prepareStatement(
                    "insert into pedido (nome, cpf, rua, numero, bairro, equipamento, descricao) values (?, ?, ?, ?, ?, ?, ?)");
            comando.setString(1, pedido.getNome());
            comando.setInt(2, pedido.getCpf());
            comando.setString(3, pedido.getRua());
            comando.setInt(4, pedido.getNumero());
            comando.setString(5, pedido.getBairro());
            comando.setInt(6, pedido.getEquipamento().getCodigo());
            comando.setString(7, pedido.getDescricao());
            return (comando.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        } finally {
            ConexaoMySQL.FecharConexao();
        }
    }

    public boolean atualizar(Pedido pedido) {
        PreparedStatement comando = null;
        try {
            comando = ConexaoMySQL.getConexaoMySQL().prepareStatement(
                    "update pedido set nome = ?, cpf = ?, rua = ?, numero = ?, bairro = ?, equipamento = ?, descricao = ?, where cpf = ?");
            comando.setString(1, pedido.getNome());
            comando.setInt(2, pedido.getCpf());
            comando.setString(3, pedido.getRua());
            comando.setInt(4, pedido.getNumero());
            comando.setString(5, pedido.getBairro());
            comando.setInt(6, pedido.getEquipamento().getCodigo());
            comando.setString(7, pedido.getDescricao());
            return (comando.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        } finally {
            ConexaoMySQL.FecharConexao();
        }
    }

    public boolean excluir(Pedido pedido) {
        PreparedStatement comando = null;
        try {
            comando = ConexaoMySQL.getConexaoMySQL().prepareStatement(
                    "delete from pedido where cpf = ?");
            comando.setInt(1, pedido.getCpf());
            return (comando.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        } finally {
            ConexaoMySQL.FecharConexao();
        }
    }

    public ArrayList<Pedido> listar() {
        Statement comando = null;
        DaoEquipamento daoEquipamento = new DaoEquipamento();
        try {
            comando = ConexaoMySQL.getConexaoMySQL().createStatement();
            ResultSet resultado = comando.executeQuery("select * from pedido");
            ArrayList<Pedido> pedidos = new ArrayList<>();
            while (resultado.next()) {
                Pedido p = new Pedido();
                p.setNome(resultado.getString("nome"));
                p.setCpf(resultado.getInt("cpf"));
                p.setRua(resultado.getString("rua"));
                p.setNumero(resultado.getInt("numero"));
                p.setBairro(resultado.getString("bairro"));
                p.setEquipamento(daoEquipamento.pesquisar(resultado.getInt("equipamento")));
                p.setDescricao(resultado.getString("descricao"));
                pedidos.add(p);
            }
            return pedidos;
        } catch (SQLException e) {
            return null;
        } finally {
            ConexaoMySQL.FecharConexao();
        }
    }

    public Pedido pesquisar(int cpf) {
        PreparedStatement comando = null;
        DaoEquipamento daoEquipamento = new DaoEquipamento();
        try {
            comando = ConexaoMySQL.getConexaoMySQL().prepareStatement("select * from pedido where cpf = ?");
            comando.setInt(1, cpf);
            ResultSet resultado = comando.executeQuery();
            ArrayList<Pedido> pedidos = new ArrayList<>();
            while (resultado.next()) {
                Pedido p = new Pedido();
                p.setNome(resultado.getString("nome"));
                p.setCpf(resultado.getInt("cpf"));
                p.setRua(resultado.getString("rua"));
                p.setNumero(resultado.getInt("numero"));
                p.setBairro(resultado.getString("bairro"));
                p.setEquipamento(daoEquipamento.pesquisar(resultado.getInt("equipamento")));
                p.setDescricao(resultado.getString("descricao"));
                return p;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            ConexaoMySQL.FecharConexao();
        }
    }

}
