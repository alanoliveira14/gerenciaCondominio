package br.com.gerenciador.entity;

import lombok.Data;

@Data
public class Aviso {

    private Integer idAviso;
    private String mensagem;
    private Integer destinatario;
    private Integer status;
    private String identificacaoDest;

}
