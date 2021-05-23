package com.starter.admin.service.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.admin.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
@Slf4j
public class QueueConsumer {

//    @JmsListener(destination = "${queueName}")
//    public void receiveQueue(String text) {
//        System.out.println("QueueConsumer消费了消息： "+text);
//    }

    @JmsListener(destination = "delay-queue-test")
    public void receiveDelayQueueTest(ActiveMQMessage message, Session session) throws JMSException,
            JsonProcessingException, InterruptedException {
        log.info("receiveDelayQueueTest");
        String text = null;
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            text = textMessage.getText();
            log.info("delayQueue接收到消息：{}", text);
            ObjectMapper objectMapper = new ObjectMapper();
            Student student = objectMapper.readValue(text, Student.class);
            Thread.sleep(2000);
            log.info("delayQueue接收到student：{}", student);
            // 手动确认
            message.acknowledge();
        }
    }

    /**
     * queue-test普通队列：消费者1
     */
//    @JmsListener(destination = "queue-test", containerFactory = "queueListener")
//    public void receiveQueueTest1(ActiveMQMessage message, Session session) throws JMSException,
//            JsonProcessingException, InterruptedException {
//        log.info("receiveQueueTest:1");
//        String text = null;
//        if (message instanceof TextMessage) {
//            TextMessage textMessage = (TextMessage) message;
//            text = textMessage.getText();
//            log.info("queue1接收到消息：{}", text);
//            ObjectMapper objectMapper = new ObjectMapper();
//            Student student = objectMapper.readValue(text, Student.class);
//            Thread.sleep(5000);
//            log.info("queue1接收到student：{}", student);
//            // 手动确认
//            message.acknowledge();
//        }
//    }

    /**
     * queue-test普通队列：消费者2
     */
//    @JmsListener(destination = "queue-test", containerFactory = "queueListener")
//    public void receiveQueueTest2(ActiveMQMessage message, Session session) throws JMSException,
//            JsonProcessingException {
//        log.info("receiveQueueTest:2");
//        String text = null;
//        if (message instanceof TextMessage) {
//            TextMessage textMessage = (TextMessage) message;
//            text = textMessage.getText();
//            log.info("queue2接收到消息：{}", text);
//            ObjectMapper objectMapper = new ObjectMapper();
//            Student student = objectMapper.readValue(text, Student.class);
//            sleep(5000);
//            log.info("queue2接收到student：{}", student);
//            // 手动确认
//            message.acknowledge();
//        }
//    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * queue-test普通队列：消费者3，专门用来让消息重发，从而进入死信队列
     */
    @JmsListener(destination = "queue-test", containerFactory = "queueListener")
    public void receiveQueueTest3(ActiveMQMessage message, Session session) throws JMSException,
            JsonProcessingException {
        log.info("receiveQueueTest:3");
        String text = null;
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            text = textMessage.getText();
            log.info("queue3接收到消息：{}", text);
            ObjectMapper objectMapper = new ObjectMapper();
            Student student = objectMapper.readValue(text, Student.class);
            // 该消费者用来让消息重发，从而进入死信队列
            session.recover();
            if (true) {
                return;
            }
            sleep(5000);
            log.info("queue3接收到student：{}", student);
            // 手动确认
            message.acknowledge();
        }
    }


}

