package com.example.demo.resources;

import java.util.Date;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

@Relation(value="taco", collectionRelation="tacos")
public class TacoResource extends RepresentationModel<TacoResource> {
	private Long id;
	private Date createAt;

	private String name;

	private List<IngredientResource> ingredients;

}