package com.example.demo.messaging.rabbit;

import com.example.demo.Model.Order;
import com.example.demo.messaging.OrderMessagingService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Override
    public void sendOrder(Order order) {
        rabbitTemplate.convertAndSend("tacocloud.order.queue", order, message -> {
            MessageProperties props = message.getMessageProperties();
            props.setHeader("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }
}
