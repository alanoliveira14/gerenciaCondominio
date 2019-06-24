package br.com.gerenciador.dao;

import br.com.gerenciador.database.ConnectionFactory;
import br.com.gerenciador.entity.Imovel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImovelDAO {

    public void insertImovel(Imovel imovel){
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "INSERT INTO imovel(endereco, valorAluguel, situacao, descricao) VALUES(?,?,0,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, imovel.getEndereco());
            ps.setDouble(2, imovel.getValorAluguel());
            ps.setString(3, imovel.getDescricaoImovel());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso");

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

    }

    public String imoveisDisponiveis(){

        String imoveis = "";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "SELECT  * FROM imovel WHERE situacao = 0";
            ps = conn.prepareStatement(sql);

            ps.execute();

            rs = ps.getResultSet();

            while (rs.next()){
                imoveis = imoveis +"\n Identificação do Imóvel: "+rs.getString(1)+" Endereco: "+rs.getString(2) + " Valor: " + rs.getString(3) +" Tipo: " + rs.getString(5);
            }


        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

        return imoveis;
    }

}
