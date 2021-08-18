package com.example.demo.controller;

import javax.validation.Valid;

import com.example.demo.messaging.OrderMessagingService;
import com.example.demo.resources.OrderResource;
import com.example.demo.resources.OrderResourceAssembler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.Model.Order;
import com.example.demo.Model.OrderProps;
//import com.example.demo.Model.User;
import com.example.demo.data.OrderRepository;

import java.util.Collection;

@RestController
@RequestMapping(path = "/orders" ,produces="application/json")
@SessionAttributes("order")
@CrossOrigin(origins = "*")
//@ConfigurationProperties(prefix = "taco.orders")
public class OrderController {
//	private int pageSize = 20;
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
	@Autowired
	private OrderMessagingService orderMessagingService;
	@Autowired
	private OrderProps orderProps;
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private OrderResourceAssembler orderResourceAssembler;

	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}


	@GetMapping
	public ResponseEntity<CollectionModel<OrderResource>> getAll(){
		Iterable<Order> orders = orderRepo.findAll();
		return new ResponseEntity<>(
				orderResourceAssembler.toCollectionModel(orders)
				,HttpStatus.OK
		);
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrderResource> getbyId(@PathVariable("id") long id){
			return orderRepo.findById(id)
					.map(orderResourceAssembler::toModel)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound()
							.build());
	}
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order order){
		orderMessagingService.sendOrder(order);

		return new ResponseEntity<>(orderRepo.save(order),HttpStatus.CREATED);
	}
//	@GetMapping
//	public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
//
//		Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
//		model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
//		return "orderList";
//	}
//
//	@PostMapping
//	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
//			@AuthenticationPrincipal User user) {
//		if (errors.hasErrors()) {
//			return "orderForm";
//		}
//
//		order.setUser(user);
//
//		orderRepo.save(order);
//		sessionStatus.setComplete();
//
//		return "redirect:/";
//	}
	@DeleteMapping("/{orderId}") @ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("orderId") Long orderId) {
	try { orderRepo.deleteById(orderId);
	  } catch (EmptyResultDataAccessException e) {}
	}


	@PatchMapping(path = "/{orderId}", consumes = "application/json")
	public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {
		Order order = orderRepo.findById(orderId).get();
		if (patch.getDeliveryName() != null) {
			order.setDeliveryName(patch.getDeliveryName());
		}
		if (patch.getDeliveryStreet() != null) {
			order.setDeliveryStreet(patch.getDeliveryStreet());
		}
		if (patch.getDeliveryCity() != null) {
			order.setDeliveryCity(patch.getDeliveryCity());
		}
		if (patch.getDeliveryState() != null) {
			order.setDeliveryState(patch.getDeliveryState());
		}
		if (patch.getDeliveryZip() != null) {
			order.setDeliveryZip(patch.getDeliveryState());
		}
		if (patch.getCcNumber() != null) {
			order.setCcNumber(patch.getCcNumber());
		}
		if (patch.getCcExpiration() != null) {
			order.setCcExpiration(patch.getCcExpiration());
		}
		if (patch.getCcCVV() != null) {
			order.setCcCVV(patch.getCcCVV());
		}
		return orderRepo.save(order);
	}


//	public ResponseEntity<>
}
