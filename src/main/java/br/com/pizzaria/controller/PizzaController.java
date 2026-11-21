package br.com.pizzaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pizzaria.dto.PizzaRequest;
import br.com.pizzaria.dto.PizzaResponse;
import br.com.pizzaria.service.PizzaService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired 
    private PizzaService pizzaService;

    @PostMapping 
    public ResponseEntity<PizzaResponse> salvar(@RequestBody @Valid PizzaRequest requestDto){
        PizzaResponse novaPizza = pizzaService.salvar(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPizza);
    }

    @GetMapping 
    public ResponseEntity<List<PizzaResponse>> listar(){
        List<PizzaResponse> pizzas = pizzaService.listarTodas();
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaResponse> buscarPorId(@PathVariable Long id){
        PizzaResponse pizza = pizzaService.buscarPorId(id);
        if(pizza != null){
            return ResponseEntity.ok(pizza);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid PizzaRequest requestDto){
        PizzaResponse pizzaAtualizada = pizzaService.atualizar(id, requestDto);
        if(pizzaAtualizada != null){
            return ResponseEntity.ok(pizzaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        PizzaResponse pizza = pizzaService.buscarPorId(id);
        if(pizza != null){
            pizzaService.excluir(id);
            return ResponseEntity.noContent().build(); // 204 No Content é o padrão para exclusão
        }
        return ResponseEntity.notFound().build();
    }
    
    
}
