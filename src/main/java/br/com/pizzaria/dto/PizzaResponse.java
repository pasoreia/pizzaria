package br.com.pizzaria.dto;

import lombok.Data;

@Data 
public class PizzaResponse {

    private Long id;
    private String nome;
    private double preco;
    private Double margemLucro;
    private String descricao;
    private String tamanho;
    private double custo;

    
}
