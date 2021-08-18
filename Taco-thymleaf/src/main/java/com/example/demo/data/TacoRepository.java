package com.example.demo.data;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.Model.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco,Long> {
}