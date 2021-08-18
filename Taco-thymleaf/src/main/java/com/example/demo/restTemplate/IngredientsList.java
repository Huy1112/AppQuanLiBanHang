package com.example.demo.restTemplate;

import com.example.demo.Model.Ingredient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class IngredientsList {
    private List<Ingredient> ingredientList;
    public IngredientsList(){
        ingredientList = new ArrayList<>();
    }
}
