package com.example.demo.restTemplate;

import com.example.demo.Model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class TacoClient {

    private RestTemplate rest;
    @Autowired
    private Traverson traverson;

    public TacoClient(RestTemplate rest) {
        this.rest = rest;
    }
    public Ingredient getIngredientById(String id){
        ResponseEntity<Ingredient> responseEntity =
                rest.getForEntity("http://localhost:8080/ingredients/{id}",
                        Ingredient.class,id);
        log.info("Fetched time " + responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    public void updateIngredient(Ingredient ingredient){
        rest.put("http://localhost:8080/ingredients/{id}",ingredient ,ingredient.getId());
    }
//    public List<Ingredient> getAllIngredients() {
//
//        ResponseEntity<List<Ingredient>> responseEntity = rest.exchange(
//                "http://localhost:8080/ingredients",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Ingredient>>() {});
//        List<Ingredient> ingredients = responseEntity.getBody();
//        return ingredients;
//
//    }
    public Ingredient postAnIngredient(Ingredient ingredient){
        return rest.postForObject("http://localhost:8080/ingredients",
                        ingredient,Ingredient.class);
    }
    public void deleteAnIngredient(Ingredient ingredient){
                rest.delete("http://localhost:8080/ingredients/{id}",
                        ingredient.getId());
    }
    public Ingredient addIngredient(Ingredient ingredient) { String ingredientsUrl = traverson
            .follow("ingredients") .asLink()
            .getHref();
        return rest.postForObject(ingredientsUrl, ingredient,
                Ingredient.class);
    }

}
