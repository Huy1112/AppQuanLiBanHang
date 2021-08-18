package com.example.demo.Model;


import java.io.Serializable;
import java.util.Date;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@RestResource(rel="tacos", path="tacos")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name="taco")
public class Taco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date createAt;

@NotNull
@Size(min = 5, message = "Name must be at least 5 characters long")
private String name;


@ManyToMany(targetEntity =Ingredient.class)
@NotNull
@Size(min = 1, message = "Ingredients must be at least 1 ingredient in list")
private List<Ingredient> ingredients;


@PrePersist
void createAt() {
	this.createAt = new Date();
}

}