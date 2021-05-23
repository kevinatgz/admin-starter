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

@Slf4j
@Component
public class ConsumerListener {

    /**
     * queue-test普通队列：消费者1
     */
    @JmsListener(destination = "queue-test")
    public void receiveQueueTest1(ActiveMQMessage message, Session session) throws JMSException,
            JsonProcessingException {
        log.info("receiveQueueTest:1");
        String text = null;
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            text = textMessage.getText();
            log.info("queue1接收到消息：{}", text);
            ObjectMapper objectMapper = new ObjectMapper();
            Student student = objectMapper.readValue(text, Student.class);
            sleep(5000);
            log.info("queue1接收到student：{}", student);
            // 手动确认
            message.acknowledge();
        }
    }

    /**
     * queue-test普通队列：消费者2
     */
    @JmsListener(destination = "queue-test")
    public void receiveQueueTest2(ActiveMQMessage message, Session session) throws JMSException,
            JsonProcessingException {
        log.info("receiveQueueTest:2");
        String text = null;
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            text = textMessage.getText();
            log.info("queue2接收到消息：{}", text);
            ObjectMapper objectMapper = new ObjectMapper();
            Student student = objectMapper.readValue(text, Student.class);
            sleep(5000);
            log.info("queue2接收到student：{}", student);
            // 手动确认
            message.acknowledge();
        }
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
