package com.starter.admin;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.AdminApplication;
import com.starter.admin.domain.Student;
import com.starter.admin.service.mq.QueueProducer;
import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.Queue;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@EnableScheduling   //开启定时发送消息功能
public class ActivemqTests {
    //注入springboot封装的工具类，它是Jmstemplate的封装类
    @Autowired
    private QueueProducer queueProducer;

    @Autowired
    @Qualifier("queue")// 因为我配置中还有个延时队列，所以采用通过bean的名称方式注入
    private Queue queue;


    @Value("${LOGSTASH_HOST}")
    private String ip;

    private final static Logger log2 = LoggerFactory.getLogger(ActivemqTests.class);

    private static Log log = LogFactory.getLog(ActivemqTests.class);

    private static final String connectorPort = "1093";
    private static final String connectorPath = "/jmxrmi";
    private static final String jmxDomain = "org.apache.activemq";


    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    public void test() {
        Map<String,Long> queueMap=new HashMap<String, Long>();
        BrokerViewMBean mBean=null;
        MBeanServerConnection connection=null;
        try{
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://192.168.128.21:" + connectorPort + connectorPath);
            JMXConnector connector = JMXConnectorFactory.connect(url);
            connector.connect();
            connection = connector.getMBeanServerConnection();
            ObjectName name = new ObjectName(jmxDomain + ":brokerName=localhost,type=Broker");
            mBean = MBeanServerInvocationHandler.newProxyInstance(connection, name, BrokerViewMBean.class, true);
        }catch (IOException e){
            log.error("ActiveMQUtil.getAllQueueSize",e);
        }catch (MalformedObjectNameException e){
            log.error("ActiveMQUtil.getAllQueueSize",e);
        }
        log.info(mBean);
        if(mBean!=null){
            for (ObjectName queueName : mBean.getQueues()) {
                QueueViewMBean queueMBean = MBeanServerInvocationHandler.newProxyInstance(connection, queueName, QueueViewMBean.class, true);
                queueMap.put(queueMBean.getName(),queueMBean.getQueueSize());
                System.out.println("Queue Name --- " + queueMBean.getName());// 消息队列名称
                System.out.println("Queue Size --- " + queueMBean.getQueueSize());// 队列中剩余的消息数
                System.out.println("Number of Consumers --- " + queueMBean.getConsumerCount());// 消费者数
                System.out.println("Number of Dequeue ---" + queueMBean.getDequeueCount());// 出队数
            }
        }
    }

    @Test
    public void testSendQueue() throws JsonProcessingException {
//        queueProducer.sendQueue("haha");

        queueProducer.sendDelayQueue();
    }


    /**
     * 发送普通消息队列
     */
    @Test
    public void sendQueue() throws JsonProcessingException {
        Student student = new Student(1, "张三", new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        String msg = objectMapper.writeValueAsString(student);
        log.info("发送-开始");
        jmsMessagingTemplate.convertAndSend(queue, msg);
        log.info("发送-结束");

    }


    @Test
    public void testScheduledSendQueue() {//间隔时间定投
        queueProducer.sendQueueScheduled();
        try {
            System.in.read(); //让程序一直执行下去
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  Map<String,Long> getAllQueueSize() {
        Map<String,Long> queueMap=new HashMap<String, Long>();
        BrokerViewMBean mBean=null;
        MBeanServerConnection connection=null;
        try{
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + connectorPort + connectorPath);
            JMXConnector connector = JMXConnectorFactory.connect(url);
            connector.connect();
            connection = connector.getMBeanServerConnection();
            ObjectName name = new ObjectName(jmxDomain + ":brokerName=localhost,type=Broker");
            mBean = MBeanServerInvocationHandler.newProxyInstance(connection, name, BrokerViewMBean.class, true);
        }catch (IOException e){
            log.error("ActiveMQUtil.getAllQueueSize",e);
        }catch (MalformedObjectNameException e){
            log.error("ActiveMQUtil.getAllQueueSize",e);
        }

        log.info(mBean);

        if(mBean!=null){
            for (ObjectName queueName : mBean.getQueues()) {
                QueueViewMBean queueMBean = MBeanServerInvocationHandler.newProxyInstance(connection, queueName, QueueViewMBean.class, true);
                queueMap.put(queueMBean.getName(),queueMBean.getQueueSize());
                System.out.println("Queue Name --- " + queueMBean.getName());// 消息队列名称
                System.out.println("Queue Size --- " + queueMBean.getQueueSize());// 队列中剩余的消息数
                System.out.println("Number of Consumers --- " + queueMBean.getConsumerCount());// 消费者数
                System.out.println("Number of Dequeue ---" + queueMBean.getDequeueCount());// 出队数
            }
        }

        return queueMap;
    }


}

