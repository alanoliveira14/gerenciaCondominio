package br.com.gerenciador.entity;

import lombok.Data;

@Data
public class Locatario {

    private Integer idLocatario;
    private String nomeLocatario;
    private String cpfCnpj;
    private String telefone;
    private String email;

}
