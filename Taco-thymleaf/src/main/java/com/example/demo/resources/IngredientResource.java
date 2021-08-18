package com.example.demo.resources;

import com.example.demo.Model.Ingredient;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class IngredientResource extends RepresentationModel<IngredientResource> {

  private String id;
  private String name;
  private Ingredient.Type type;


}