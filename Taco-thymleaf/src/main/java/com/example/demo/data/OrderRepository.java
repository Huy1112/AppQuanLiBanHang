package com.example.demo.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Model.Order;
//import com.example.demo.Model.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order,Long> {
//	List<Order> findByUserOrderByPlacedAtDesc(User user,Pageable pageable);
//  Order save(Order order);
}