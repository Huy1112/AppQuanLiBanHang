package com.example.demo.Kitchen;

import com.example.demo.Model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@EnableJms
public class OrderListener {
    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    //
//    @JmsListener(destination = "tacocloud.queue.cc")
//    public void receiveOrder(Order order) {
//        ui.displayOrder(order);
//    }
    @RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        ui.displayOrder(order);
    }
}