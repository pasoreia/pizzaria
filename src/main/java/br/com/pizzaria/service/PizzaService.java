package br.com.pizzaria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pizzaria.dto.PizzaRequest;
import br.com.pizzaria.dto.PizzaResponse;
import br.com.pizzaria.entity.Pizza;
import br.com.pizzaria.repository.PizzaRepository;



@Service
public class PizzaService{
    
    @Autowired
    private PizzaRepository pizzaRepository;

    public PizzaResponse salvar(PizzaRequest requestDto){
        Pizza pizza = new Pizza();
        pizza.setNome(requestDto.getNome());
        pizza.setPreco(requestDto.getPreco());
        pizza.setTamanho(requestDto.getTamanho());
        pizza.setDescricao(requestDto.getDescricao());
        pizza.setCusto(requestDto.getCusto());

        Pizza pizzaSalva = pizzaRepository.save(pizza);
        return converterParaDto(pizzaSalva);
    }

    public List<PizzaResponse> listarTodas(){
        List<Pizza> pizzas= pizzaRepository.findAll();
        return pizzas.stream().map(this::converterParaDto).collect(Collectors.toList());
    }

    public PizzaResponse buscarPorId(Long id){
        Optional<Pizza> pizza = pizzaRepository.findById(id); 
        if(pizza.isPresent()){
            return converterParaDto(pizza.get());
        }
        return null;
    }

    public void excluir(Long id){
         pizzaRepository.deleteById(id);
    }    

    public PizzaResponse atualizar(Long id, PizzaRequest requestDto){
        Optional<Pizza> pizzaOpt = pizzaRepository.findById(id);

        if(pizzaOpt.isPresent()){
            Pizza pizza = pizzaOpt.get();
            pizza.setNome(requestDto.getNome());
            pizza.setPreco(requestDto.getPreco());
            pizza.setDescricao(requestDto.getDescricao());
            pizza.setTamanho(requestDto.getTamanho());
            pizza.setCusto(requestDto.getCusto());

            Pizza pizzaAtualizada = pizzaRepository.save(pizza);

            return converterParaDto(pizzaAtualizada);
        }

        return null;
    }

    private PizzaResponse converterParaDto(Pizza pizza){
        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setId(pizza.getId());
        pizzaResponse.setNome(pizza.getNome());
        pizzaResponse.setPreco(pizza.getPreco());
        pizzaResponse.setDescricao(pizza.getDescricao());
        pizzaResponse.setTamanho(pizza.getTamanho());
        pizzaResponse.setCusto(pizza.getCusto());

        double margem =pizza.getPreco()- pizza.getCusto();
        pizzaResponse.setMargemLucro(margem);

        return pizzaResponse;
    }


}