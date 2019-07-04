package com.example.worknet;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.constant.ProfessionConst;
import com.example.worknet.common.persistence.affair.employment.service.CompanyProfessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class worknetTests {

    @Autowired
    private CompanyProfessionService companyProfessionService;

	@Test
	public void contextLoads() {

        System.out.println(companyProfessionService.getJobInfo((long)2,(long)2));
	}
}
