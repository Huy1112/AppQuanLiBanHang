
package com.example.demo.controller;

import com.example.demo.resources.TacoResource;
import com.example.demo.resources.TacoResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Taco;
import com.example.demo.data.TacoRepository;

import java.util.List;

@RestController
@RequestMapping(path="/tacos",                      // <1>
        produces="application/json")
@CrossOrigin(origins="*")        // <2>
public class DesignTacoController {
    @Autowired
    private TacoRepository tacoRepo;
    @Autowired
    private TacoResourceAssembler tacoResourceAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<TacoResource>> getAll(){
        Iterable<Taco> tacos = tacoRepo.findAll();
        return new ResponseEntity<>(
                tacoResourceAssembler.toCollectionModel(tacos)
                ,HttpStatus.OK
        );
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<TacoResource> tacoById(@PathVariable("id") Long id){
        return tacoRepo.findById(id)
                .map(tacoResourceAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping(path = "/recent", produces="application/hal+json")
    public ResponseEntity<CollectionModel<TacoResource>> recentTacos(){
        //variable createAt come from Model
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createAt").descending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();
        return new ResponseEntity<>(
                tacoResourceAssembler.toCollectionModel(tacos)
                ,HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<TacoResource> createTaco(@RequestBody Taco taco){
        Taco saved = tacoRepo.save(taco);
        return new ResponseEntity<>(tacoResourceAssembler.toModel(saved),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public void deleteTaco(@PathVariable("id") long id){
        tacoRepo.deleteById(id);
    }

}