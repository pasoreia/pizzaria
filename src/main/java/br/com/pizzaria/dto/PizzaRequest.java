package br.com.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class PizzaRequest {

    
    @NotBlank (message = "O nome nao pode ser em branco")
    private String nome;
    @Positive (message= "Valor deve ser maior que zero")
    @NotNull (message = "Tem q haver um preco")
    private double preco;
    private String descricao;
    private String tamanho;
    private double custo;

    
}
