package br.com.gerenciador.dao;

import br.com.gerenciador.database.ConnectionFactory;
import br.com.gerenciador.entity.ContasComunitarias;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaComumDAO {

    public String tipoContas(){
        String contas = "";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "SELECT  * FROM tipoConta";
            ps = conn.prepareStatement(sql);

            ps.execute();

            rs = ps.getResultSet();

            while (rs.next()){
                contas = contas +"\n Identificação da Conta: "+rs.getString(1)+" Conta: "+rs.getString(2) + " Empresa: " + rs.getString(3);
            }


        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

        return contas;
    }


    public void insertConta(ContasComunitarias conta){
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "INSERT INTO contascomunitarias(idNomeConta, mes, ano, valorConta) VALUES(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, conta.getIdNomeConta());
            ps.setInt(2, conta.getMes());
            ps.setInt(3, conta.getAno());
            ps.setDouble(4, conta.getValorConta());
            ps.execute();

            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso");

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }
    }

    public Double getValoresDeContasComuns(Integer mes, Integer ano){

        Double valorContas = 0.00;


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "select (SUM(valorConta)/(select count(distinct idAluguel) from aluguel where validade > now())) as valorDividido from contascomunitarias where mes = ? and ano = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, mes);
            ps.setInt(2, ano);

            ps.execute();

            rs = ps.getResultSet();

            if(rs.next()){
                valorContas = rs.getDouble(1);
            }

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

        return valorContas;
    }

    public String fazFechamento(String id, Double valorComum){

        Integer quantidadeDeVagas = this.quantidadeDeVagas(id);

        String retorno = "O valor total é de: R$";
        Double valorTotal = 100.00 * quantidadeDeVagas;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "select * from aluguel inner join imovel i where i.idImovel = aluguel.idImovel and aluguel.idLocatario = (select  idLocatario from locatario where cpfCnpj = ?) ";
            ps = conn.prepareStatement(sql);

            ps.setString(1, id);

            ps.execute();

            rs = ps.getResultSet();


            while(rs.next()){

                valorTotal = valorTotal + (rs.getDouble("valorAluguel") + valorComum);

            }

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

        return retorno + valorTotal;
    }


    public Integer quantidadeDeVagas(String cpfCnpj){

        Integer quantidade = 0;

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "select count(*) from possuiVaga where idLocatario = (select idLocatario from locatario where cpfCnpj = ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpfCnpj);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            if(rs.next()){
                quantidade = rs.getInt(1);
            }


        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

        return quantidade;

    }

    public void insertDeVaga(String cpf){

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = ConnectionFactory.obtemConexao();
            String sql = "INSERT INTO possuiVaga(idLocatario) VALUES((select idLocatario from locatario where cpfCnpj = ?))";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.execute();

            JOptionPane.showMessageDialog(null, "Operação realizada com sucesso");

        }catch (SQLException e){

            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR");

            e.printStackTrace();
        }

    }


}
