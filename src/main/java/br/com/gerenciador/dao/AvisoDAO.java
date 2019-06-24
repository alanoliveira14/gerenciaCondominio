package br.com.gerenciador.dao;

import br.com.gerenciador.database.ConnectionFactory;
import br.com.gerenciador.entity.Aviso;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvisoDAO {

    public void criaAvisoGenerico(Aviso aviso){
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "INSERT INTO avisos(mensagem, destinatario, statusAviso) VALUES(?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, aviso.getMensagem());
            ps.setInt(2, aviso.getDestinatario());
            ps.setInt(3,0);

            ps.execute();

            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso");

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

    }

    public  void criaAvisoDirecionado(Aviso aviso){

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "INSERT INTO avisos(mensagem, destinatario, statusAviso) VALUES(?,(select idLocatario from locatario where cpfCnpj = ?),?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, aviso.getMensagem());
            ps.setString(2, aviso.getIdentificacaoDest());
            ps.setInt(3,0);

            ps.execute();

            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso");

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }
    }


    public String mostraAvisos(String cpf){
        String avisos = "";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "SELECT  * FROM avisos where destinatario in (0, (select idLocatario from locatario where cpfCnpj = ?)) and statusAviso = 0";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.execute();

            rs = ps.getResultSet();

            while (rs.next()){
                avisos = avisos +"\n - " + rs.getString(2);
            }

            String sql2 = "update avisos set statusAviso = 1 where destinatario = (select idLocatario from locatario where cpfCnpj = ?)";
            ps = conn.prepareStatement(sql2);
            ps.setString(1,cpf);
            ps.execute();

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

        return avisos;

    }

}

