package br.com.gerenciador.dao;

import br.com.gerenciador.database.ConnectionFactory;
import br.com.gerenciador.entity.Aluguel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AluguelDAO {

    public void insertAluguel(Aluguel aluguel){
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "INSERT INTO aluguel(idImovel, validade, dataContratado, idLocador, idLocatario) VALUES(?,?,now(),(select idLocador from locador where cpfCnpj = ?), (select idLocatario from locatario where cpfCnpj = ?))";
            ps = conn.prepareStatement(sql);

            ps.setInt(1,aluguel.getIdImovel());
            ps.setString(2,aluguel.getValidadeContrato());
            ps.setString(3, aluguel.getCpfCnpjLocador());
            ps.setString(4, aluguel.getCpfCnpjLocatario());

            ps.execute();

            String sql2 = "UPDATE imovel set situacao = 1 where idImovel = ?";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, aluguel.getIdImovel());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso");

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

    }

}
