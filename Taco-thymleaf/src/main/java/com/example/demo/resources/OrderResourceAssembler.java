package com.example.demo.resources;

import com.example.demo.Model.Ingredient;
import com.example.demo.Model.Order;
import com.example.demo.Model.Taco;
import com.example.demo.controller.DesignTacoController;
import com.example.demo.controller.OrderController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderResourceAssembler extends RepresentationModelAssemblerSupport<Order,OrderResource> {

    public OrderResourceAssembler() {
        super(OrderController.class, OrderResource.class);
    }

    @Override
    public OrderResource toModel(Order entity) {
        OrderResource orderResource = instantiateModel(entity);
        orderResource.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(OrderController.class)
                        .getbyId(entity.getId()))
                .withSelfRel());
        orderResource.setId(entity.getId());
        orderResource.setPlacedAt(entity.getPlacedAt());
        orderResource.setDeliveryName(entity.getDeliveryName());
        orderResource.setDeliveryStreet(entity.getDeliveryStreet());
        orderResource.setDeliveryCity(entity.getDeliveryCity());
        orderResource.setDeliveryState(entity.getDeliveryState());
        orderResource.setDeliveryZip(entity.getDeliveryZip());
        orderResource.setCcExpiration(entity.getCcExpiration());
        orderResource.setCcCVV(entity.getCcCVV());
        orderResource.setCcNumber(entity.getCcNumber());
        orderResource.setTacos(toTacosModel(entity.getTacos()));
        return orderResource;
    }
    @Override
    public CollectionModel<OrderResource> toCollectionModel(Iterable<? extends Order> entities){
        CollectionModel<OrderResource> orderResources = super.toCollectionModel(entities);
        orderResources.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(OrderController.class)
                        .getAll()
        ).withSelfRel());
        return orderResources;
    }

    private List<TacoResource> toTacosModel(List<Taco> tacos){
        if (tacos.isEmpty()){
            return Collections.emptyList();
        }else{
             return tacos.stream().map(taco -> TacoResource.builder()
            .id(taco.getId())
            .name(taco.getName())
            .createAt(taco.getCreateAt())
            .ingredients(TacoResourceAssembler.toIngredientModel(taco.getIngredients()))
            .build()
            .add(WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(DesignTacoController.class)
                    .tacoById(taco.getId()))
                    .withSelfRel()))
            .collect(Collectors.toList());
        }
    }
}
