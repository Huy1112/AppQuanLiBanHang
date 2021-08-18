package com.example.demo.resources;

import com.example.demo.controller.DesignTacoController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class RecentProcessor implements RepresentationModelProcessor<CollectionModel<TacoResource>>{
    @Override
    public CollectionModel<TacoResource> process(CollectionModel<TacoResource> model) {
        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(DesignTacoController.class)
                        .recentTacos()
                ).withRel("recents")
        );
        return model;
    }
}
