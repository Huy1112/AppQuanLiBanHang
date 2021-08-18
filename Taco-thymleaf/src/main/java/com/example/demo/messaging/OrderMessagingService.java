package com.example.demo.messaging;

import com.example.demo.Model.Order;
import org.springframework.stereotype.Component;


public interface OrderMessagingService {
    void sendOrder(Order order);
}
