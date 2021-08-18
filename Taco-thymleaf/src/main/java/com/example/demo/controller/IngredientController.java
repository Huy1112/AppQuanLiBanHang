package com.example.demo.controller;

import com.example.demo.Model.Ingredient;
import com.example.demo.data.IngredientRepository;
import com.example.demo.resources.IngredientResource;
import com.example.demo.resources.IngredientResourceAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@Slf4j
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {

    @Autowired
    private IngredientRepository ingRepo;
	@Autowired
	private IngredientResourceAssembler ingResource;
	@GetMapping
	public ResponseEntity<CollectionModel<IngredientResource>> getAll(){
		Iterable<Ingredient> ingredients=  ingRepo.findAll();

		 return new ResponseEntity<>(
		 		ingResource.toCollectionModel(ingredients)
				 , HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<IngredientResource> getIngredientById(@PathVariable("id") String id){
		return ingRepo.findById(id)
				.map(ingResource::toModel)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound()
						.build());
	}
	@PutMapping("/{id}")
	public void updateIngredientById(@PathVariable("id") String id , @RequestBody Ingredient ingredient){
		if (!ingredient.getId().equals(id)){
			throw new IllegalStateException("Given ingredient's ID doesn't match the ID in the path");
		}
		ingRepo.save(ingredient);
	}
	@PostMapping
	public ResponseEntity<IngredientResource> postIngredient(@RequestBody Ingredient ingredient) {
		Ingredient saved = ingRepo.save(ingredient);
//		ingResource.toModel(saved);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(URI.create("http://localhost:8080/ingredients/" + ingredient.getId()));
		return new ResponseEntity<>(ingResource.toModel(saved),HttpStatus.CREATED);
	}
	@DeleteMapping("/{id}")
	public void deleteIngredient(@PathVariable("id") String id) {
		ingRepo.deleteById(id);
	}
}
