package com.example.worknet.config.hessian;

import com.example.worknet.common.persistence.affair.profession.serivce.ProfessionTypeService;
import com.example.worknet.common.persistence.affair.user.serivce.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

import javax.annotation.Resource;

/**
 * @Author: YunJieJiang
 * @Date: Created in 14:28 2019/6/28 0028
 * 1. HessianServiceExporter是由Spring.web框架提供的Hessian工具类，能够将bean转化为Hessian服务
 * 2. @Bean(name = "/helloHessian.do")加斜杠方式会被spring暴露服务路径,发布服务。
 */
@Configuration
public class HessianServiceConfig {

    @Bean("/userService")
    public HessianServiceExporter exportUserService()
    {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(userService);
        exporter.setServiceInterface(UserService.class);
        return exporter;
    }

    @Bean("/professionTypeService")
    public HessianServiceExporter exportProfessionTypeService()
    {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(professionTypeService);
        exporter.setServiceInterface(ProfessionTypeService.class);
        return exporter;
    }

    @Resource
    private UserService userService;

    @Resource
    private ProfessionTypeService professionTypeService;
}