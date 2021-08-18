//package com.example.demo.messaging;
//
//
//import com.example.demo.Model.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.MessageCreator;
//import org.springframework.stereotype.Service;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.Session;
//
//@Service
//public class JmsOrderMessagingService implements OrderMessagingService{
//
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    public void sendOrder(Order order){
//       jmsTemplate.convertAndSend("tacocloud.queue.cc",order,this::addOrder);
//    }
//    public Message addOrder(Message message) throws JMSException{
//        message.setStringProperty("X_ORDER_PROPERTY","WEB");
//        return message;
//    }
//}
