package com.example.worknet.config.hessian;

import com.example.worknet.common.persistence.affair.administrator.service.AdministratorService;
import com.example.worknet.common.persistence.affair.company.serivce.CompanyService;
import com.example.worknet.common.persistence.affair.companyContest.service.*;
import com.example.worknet.common.persistence.affair.message.service.MessageService;
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
public class HessionServiceConfig {

    @Bean("/administratorService")
    public HessianServiceExporter exportAdministratorHessian()
    {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(administratorService);
        exporter.setServiceInterface(AdministratorService.class);
        return exporter;
    }

    @Bean("/companyService")
    public HessianServiceExporter exportCompanyHessian()
    {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(companyService);
        exporter.setServiceInterface(CompanyService.class);
        return exporter;
    }

    @Bean("/messageService")
    public HessianServiceExporter exportMessageHessian()
    {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(messageService);
        exporter.setServiceInterface(MessageService.class);
        return exporter;
    }

    @Bean("/companyContestApplyService")
    public HessianServiceExporter exportCompanyContestApplyHessian()
    {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(companyContestApplyService);
        exporter.setServiceInterface(CompanyContestApplyService.class);
        return exporter;
    }

    @Bean("/companyContestChoiceOptionService")
    public HessianServiceExporter exportCompanyContestChoiceOptionHessian()
    {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(companyContestChoiceOptionService);
        exporter.setServiceInterface(CompanyContestChoiceOptionService.class);
        return exporter;
    }

    @Bean("/companyContestQuestionService")
    public HessianServiceExporter exportCompanyContestQuestionHessian()
    {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(companyContestQuestionService);
        exporter.setServiceInterface(CompanyContestQuestionService.class);
        return exporter;
    }

    @Bean("/companyContestResultService")
    public HessianServiceExporter exportCompanyContestResultHessian()
    {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(companyContestResultService);
        exporter.setServiceInterface(CompanyContestResultService.class);
        return exporter;
    }

    @Bean("/companyContestService")
    public HessianServiceExporter exportCompanyContestHessian()
    {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(companyContestService);
        exporter.setServiceInterface(CompanyContestService.class);
        return exporter;
    }

    @Resource
    private AdministratorService administratorService;

    @Resource
    private CompanyService companyService;

    @Resource
    private MessageService messageService;

    @Resource
    private CompanyContestApplyService companyContestApplyService;

    @Resource
    private CompanyContestChoiceOptionService companyContestChoiceOptionService;

    @Resource
    private CompanyContestQuestionService companyContestQuestionService;

    @Resource
    private CompanyContestResultService companyContestResultService;

    @Resource
    private CompanyContestService companyContestService;
}