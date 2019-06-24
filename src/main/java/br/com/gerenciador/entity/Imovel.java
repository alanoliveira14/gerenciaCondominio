package br.com.gerenciador.entity;


import lombok.Data;

@Data
public class Imovel {

    private Integer idImovel;
    private String endereco;
    private Double valorAluguel;
    private Integer situacao;
    private String descricaoImovel;


}
