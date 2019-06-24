package br.com.gerenciador.entity;

import lombok.Data;

@Data
public class Aluguel {


    private Integer idAluguel;
    private Integer idLocador;
    private Integer idLocatario;
    private Integer idImovel;
    private String validadeContrato;
    private String dataContratado;
    private String nomeLocador;
    private String nomeLocatario;
    private String descImovel;
    private String cpfCnpjLocador;
    private String cpfCnpjLocatario;

}
