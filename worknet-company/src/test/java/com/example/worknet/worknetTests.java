package com.example.worknet;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.constant.ProfessionConst;
import com.example.worknet.common.persistence.affair.employment.service.CompanyCvService;
import com.example.worknet.common.persistence.affair.employment.service.CompanyProfessionService;
import javafx.geometry.Pos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class worknetTests {


    @Autowired
    private CompanyCvService companyCvService;
	@Test
	public void contextLoads() {
//        String date="1997-05-07 00:00:00.000000";
//        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
//        ParsePosition pos=new ParsePosition(0);
//        Date d=formatter.parse(date,pos);
//        System.out.println(companyCvService.deliverResume((long)1,(long)1,"张萨姆",1,d,"上海","111","本科","计算机"	,"海大学","1111111111","1111-1111 哈哈哈哈","8888@qq.com",	"我是XXXXX	","2222年 XXX奖",	"上海",	"三周内",0));
        System.out.println(companyCvService.getMyResumePage(new Page<>(),"1","腾讯").getRecords());

	}
}
