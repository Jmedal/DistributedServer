package com.example.worknet.controller.service;


import com.example.worknet.common.persistence.affair.api.user.serivce.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试API
 * Service的测试类
 * @Author: YunJieJiang
 * @Date: Created in 2:20 2018/8/24 0024
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseClickTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() throws Exception {
       //System.out.println(userService.getNickname((long)3));
    }

}