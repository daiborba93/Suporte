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

/**
 *
 * @author Daiane
 */
public class DaoEquipamento {

    public boolean inserir(Equipamento equipamento) {
        PreparedStatement comando = null;
        try {
            comando = ConexaoMySQL.getConexaoMySQL().prepareStatement(
                    "insert into equipamentos(cd_equipamento, nomeEquipamento) values (?, ?)");
            comando.setInt(1, equipamento.getCodigo());
            comando.setString(2, equipamento.getNomeEquipamento());
            return (comando.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        } finally {
            ConexaoMySQL.FecharConexao();
        }
    }

    public boolean atualizar(Equipamento equipamento) {
        PreparedStatement comando = null;
        try {
            comando = ConexaoMySQL.getConexaoMySQL().prepareStatement(
                    "update equipamentos set nomeEquipamento = ? where cd_equipamento = ?");
            comando.setInt(1, equipamento.getCodigo());
            comando.setString(2, equipamento.getNomeEquipamento());
            return (comando.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        } finally {
            ConexaoMySQL.FecharConexao();
        }
    }

    public boolean excluir(Equipamento equipamento) {
        PreparedStatement comando = null;
        try {
            comando = ConexaoMySQL.getConexaoMySQL().prepareStatement(
                    "delete from equipamentos where cd_equipamento = ?");
            comando.setInt(1, equipamento.getCodigo());
            return (comando.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        } finally {
            ConexaoMySQL.FecharConexao();
        }
    }

    public ArrayList<Equipamento> listar() {
        Statement comando = null;
        try {
            comando = ConexaoMySQL.getConexaoMySQL().createStatement();
            ResultSet resultado = comando.executeQuery("select * from equipamentos");
            ArrayList<Equipamento> equipamentos = new ArrayList<>();
            while (resultado.next()) {
                Equipamento e = new Equipamento();
                e.setCodigo(resultado.getInt("cd_equipamento"));
                e.setNomeEquipamento(resultado.getString("nomeEquipamento"));
                equipamentos.add(e);
            }
            return equipamentos;
        } catch (SQLException e) {
            return null;
        } finally {
            ConexaoMySQL.FecharConexao();
        }
    }

    public Equipamento pesquisar(int codigo) {
        PreparedStatement comando = null;
        try {
            comando = ConexaoMySQL.getConexaoMySQL().prepareStatement("select * from equipamentos where cd_equipamento = ?");
            comando.setInt(1, codigo);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Equipamento e = new Equipamento();
                e.setCodigo(resultado.getInt("cd_equipamento"));
                e.setNomeEquipamento(resultado.getString("nomeEquipamento"));
                return e;
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            ConexaoMySQL.FecharConexao();
        }
    }

}
