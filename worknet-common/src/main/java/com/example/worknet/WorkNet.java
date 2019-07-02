package com.example.worknet;

import com.example.worknet.common.constant.ServiceURL;
import com.example.worknet.common.persistence.affair.user.serivce.CompanyService;
import com.example.worknet.config.hessian.utils.HessianProxyFactoryUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.worknet.common.persistence.affair.*.dao")
@EnableScheduling
public class WorkNet {
	public static void main(String[] args) {
		SpringApplication.run(WorkNet.class, args);
		try {
			CompanyService companyService = HessianProxyFactoryUtil.getHessianClientBean(CompanyService.class, ServiceURL.CompanyService);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
