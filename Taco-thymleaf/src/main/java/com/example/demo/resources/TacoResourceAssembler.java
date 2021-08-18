package com.example.demo.resources;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.controller.DesignTacoController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.example.demo.Model.Ingredient;
import com.example.demo.Model.Taco;
import com.example.demo.controller.IngredientController;
@Component
public class TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource> {

	public TacoResourceAssembler() {
		super(DesignTacoController.class, TacoResource.class);
	}

	@Override
	public TacoResource toModel(Taco entity) {
		TacoResource tr = instantiateModel(entity);
		tr.add(
				WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(DesignTacoController.class)
				.tacoById(entity.getId()))
				.withSelfRel());

		tr.setId(entity.getId());
		tr.setName(entity.getName());
		tr.setCreateAt(entity.getCreateAt());
		tr.setIngredients(toIngredientModel(entity.getIngredients()));
		return tr;
	}

	@Override
	public CollectionModel<TacoResource> toCollectionModel(Iterable<? extends Taco> entities){
		 CollectionModel<TacoResource> actorModels = super.toCollectionModel(entities);

	        actorModels.add(
	        		WebMvcLinkBuilder.linkTo(
	        				WebMvcLinkBuilder.methodOn(DesignTacoController.class).getAll())
									.withSelfRel());

	        return actorModels;
	}
	public static List<IngredientResource> toIngredientModel(List<Ingredient> ingredients) {
        if (ingredients.isEmpty())
            return Collections.emptyList();
        return ingredients.stream().map(ingredient -> IngredientResource.builder()
                        .id(ingredient.getId())
                        .name(ingredient.getName())
						.type(ingredient.getType())
                        .build()
                        .add(WebMvcLinkBuilder.linkTo(
                        		WebMvcLinkBuilder.methodOn(IngredientController.class)
                                .getIngredientById(ingredient.getId()))
                                .withSelfRel()))
                				.collect(Collectors.toList());

    }
}
