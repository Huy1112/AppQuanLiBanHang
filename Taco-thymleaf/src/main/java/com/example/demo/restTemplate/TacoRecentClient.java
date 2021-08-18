package com.example.demo.restTemplate;


import com.example.demo.Model.Taco;
import com.example.demo.resources.TacoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TacoRecentClient {
    @Autowired
    private Traverson traverson;
    @Autowired
    private RestTemplate restTemplate;

    public Iterable<Taco> getRecentTacos(){
        ParameterizedTypeReference<CollectionModel<Taco>> tacosType =
                new ParameterizedTypeReference<CollectionModel<Taco>>() {};
        CollectionModel<Taco> tacoResources = traverson
                .follow("tacos")
                .follow("recent")
                .toObject(tacosType);
        return Objects.requireNonNull(tacoResources).getContent();
    }
}
