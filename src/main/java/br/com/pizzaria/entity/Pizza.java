package br.com.pizzaria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table (name = "pizzaria")
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;
    private String descricao;
    private String tamanho;
    private double custo;

   
    
}
