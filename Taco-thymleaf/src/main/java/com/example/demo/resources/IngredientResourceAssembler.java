package com.example.demo.resources;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.example.demo.Model.Ingredient;
import com.example.demo.controller.IngredientController;

@Component
public class IngredientResourceAssembler extends RepresentationModelAssemblerSupport<Ingredient,IngredientResource>{
	public IngredientResourceAssembler() {
		super(IngredientController.class, IngredientResource.class);
	}
	@Override
	public IngredientResource toModel(Ingredient entity) {
		// TODO Auto-generated method stub

		IngredientResource ingModel = instantiateModel(entity);

		ingModel.add(WebMvcLinkBuilder.linkTo(
	        		WebMvcLinkBuilder.methodOn(IngredientController.class)
	                .getIngredientById(entity.getId()))
	                .withSelfRel());
		ingModel.setId(entity.getId());
		ingModel.setName(entity.getName());
		ingModel.setType(entity.getType());

		return ingModel;
	}
	 @Override
	    public CollectionModel<IngredientResource> toCollectionModel(Iterable<? extends Ingredient> entities)
	    {
	        CollectionModel<IngredientResource> actorModels = super.toCollectionModel(entities);

	        actorModels.add(
	        		WebMvcLinkBuilder.linkTo(
	        				WebMvcLinkBuilder.methodOn(IngredientController.class)
									.getAll())
										.withSelfRel());
	        return actorModels;
	    }

}
