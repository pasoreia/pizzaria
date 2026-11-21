package br.com.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pizzaria.entity.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long>{
    
}
