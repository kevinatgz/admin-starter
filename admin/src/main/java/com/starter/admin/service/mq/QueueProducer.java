package com.starter.admin.service.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.admin.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class QueueProducer {
    //注入springboot封装的工具类，它是Jmstemplate的封装类
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    @Qualifier("springboot-activemq-queue")
    private Queue queue;

    @Autowired
    @Qualifier("delayQueue")
    private Queue delayQueue;

    public void sendQueue(String msg) {
        System.out.println("QueueProvider发送了消息 : " + msg);
        //方法一：添加消息到消息队列
        jmsMessagingTemplate.convertAndSend(queue, msg);
    }

    @Scheduled(fixedDelay = 3000) //该注解修改的方法不能有参数
    public void sendQueueScheduled() {
        String msg ="xixi";
        System.out.println("QueueProvider定时发送了消息 : " + msg+" "+System.currentTimeMillis());
        jmsMessagingTemplate.convertAndSend(queue, msg+System.currentTimeMillis());
    }


    /**
     * 发送延迟消息队列
     */

    public String sendDelayQueue() throws JsonProcessingException {
        Student student = new Student(2, "李四", new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        String msg = objectMapper.writeValueAsString(student);
        Map<String, Object> headers = new HashMap<>();
        // 延迟5秒
        headers.put(ScheduledMessage.AMQ_SCHEDULED_DELAY, 5000);
        log.info("延迟发送-开始");
        jmsMessagingTemplate.convertAndSend(delayQueue, msg, headers);
        log.info("延迟发送-结束");
        return "send delay queue success";
    }



}
