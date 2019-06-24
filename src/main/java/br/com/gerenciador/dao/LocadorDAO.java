package br.com.gerenciador.dao;

import br.com.gerenciador.database.ConnectionFactory;
import br.com.gerenciador.entity.Locador;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocadorDAO {

    public void insertLocador(Locador locador){

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "INSERT INTO locador(nome, cpfCnpj, telefone, email, representante) VALUES(?,?,?,?, ?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, locador.getNomeLocador());
            ps.setString(2, locador.getCpfCnpj());
            ps.setString(3, locador.getTelefone());
            ps.setString(4, locador.getEmail());
            ps.setString(5, locador.getRepresentante());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso");

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }



    }

    public String getLocadores(){
        String locadores = "";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "SELECT  * FROM locador";
            ps = conn.prepareStatement(sql);

            ps.execute();

            rs = ps.getResultSet();

            while (rs.next()){
                locadores = locadores +"\n Nome: "+rs.getString(2)+" CPF/CNPJ: "+rs.getString(3) + " Telefone: " + rs.getString(4) +" Email: " + rs.getString(5) + " Representante: " + rs.getString(6);
            }


        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

        return locadores;

    }

}
