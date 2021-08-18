//package com.example.demo.Kitchen;
//
//
//import com.example.demo.Model.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//
//public class OrderReceive implements OrderReceiveService {
//    @Autowired
//    public JmsTemplate jmsTemplate;
//    @Override
//    public Order receiveOrder() {
//        return (Order) jmsTemplate.receiveAndConvert("tacocloud.queue.cc");
//    }
//}
