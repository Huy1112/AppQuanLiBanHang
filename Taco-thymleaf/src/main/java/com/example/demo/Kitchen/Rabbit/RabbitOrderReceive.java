package com.example.demo.Kitchen.Rabbit;

import com.example.demo.Kitchen.OrderReceiveService;
import com.example.demo.Model.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderReceive implements OrderReceiveService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageConverter messageConverter;

    @Override
    public Order receiveOrder() {
        Message message = rabbitTemplate.receive("tacocloud.order.queue", 30000);
        return message != null
                ? (Order) messageConverter.fromMessage(message) : null;

    }
}
