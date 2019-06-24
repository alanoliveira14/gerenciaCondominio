package br.com.gerenciador.dao;

import br.com.gerenciador.database.ConnectionFactory;
import br.com.gerenciador.entity.Locatario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocatarioDAO {

    public void insetLocatario(Locatario locatario){

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "INSERT INTO locatario(nome, cpfCnpj, telefone, email) VALUES(?,?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1,locatario.getNomeLocatario());
            ps.setString(2, locatario.getCpfCnpj());
            ps.setString(3, locatario.getTelefone());
            ps.setString(4, locatario.getEmail());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso");

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

    }


    public String getLocatarios(){


        String locatarios = "";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "SELECT  * FROM locador ";
            ps = conn.prepareStatement(sql);

            ps.execute();

            rs = ps.getResultSet();

            while (rs.next()){
                locatarios = locatarios +"\n Nome: "+rs.getString(2)+" CPF/CNPJ: "+rs.getString(3) + " Telefone: " + rs.getString(4) +" Email: " + rs.getString(5);
            }


        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

        return locatarios;

    }


}
