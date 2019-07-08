package com.example.worknet.controller.service;


import com.example.worknet.common.persistence.affair.api.user.UserService;
import com.example.worknet.common.persistence.affair.employment.service.CompanyProfessionService;
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

    @Autowired
    private CompanyProfessionService companyProfessionService;

    @Test
    public void test() throws Exception {
       //System.out.println(userService.getNickname((long)3));

//        HessianPage<HashMap<String,Object>> hashMapHessianPage =  companyProfessionService.getProfessionPage(new Page<>(1, 20), PROFESSION_NEW, "", "", "", "");
//        System.out.println(companyProfessionService.getProfessionPage(new Page<>(1, 20), PROFESSION_NEW, "", "", "", "").getResult());
    }

}