package com.starter.admin;


import com.starter.AdminApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class ElkTestApplicationTests {


    @Value("${LOGSTASH_HOST}")
    private String ip;

    private final static Logger log = LoggerFactory.getLogger(AdminApplication.class);
    @Test
    public void test() {
        log.info("ip:{}的filebeat  测试 info 成功了！！！",ip);
        log.warn("ip:{}的filebeat   测试 warn 成功了！！！",ip);
        log.error("ip:{}的filebeat   测试 error 成功了！！",ip);
    }

}

