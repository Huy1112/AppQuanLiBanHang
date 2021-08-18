package com.example.demo.Kitchen;

import com.example.demo.Model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class KitchenUI {
    public void displayOrder(Order order) {
        log.info("RECEIED ORDER: " + order);
    }

}
