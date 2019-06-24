package br.com.gerenciador.entity;

import lombok.Data;

@Data
public class ContasComunitarias {

    private Integer idConta;
    private Integer idNomeConta;
    private Integer mes;
    private Integer ano;
    private Double valorConta;
    private String nomeConta;
    private String nomeEmpresaConta;

}
