package br.com.gerenciador.entity;

import lombok.Data;

@Data
public class Locador {

    private Integer idLocador;
    private String nomeLocador;
    private String cpfCnpj;
    private String telefone;
    private String email;
    private String representante;

}
