package com.starter.admin;

import com.starter.admin.dao.SysUserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class AdminApplicationTests {

    @Autowired
    private SysUserDao sysUserDao;

    @Test
    void contextLoads() {
        List<String> list = Arrays.asList("1","2");
//        list.add("1");
        sysUserDao.selectBatchIds(list);
    }

}
