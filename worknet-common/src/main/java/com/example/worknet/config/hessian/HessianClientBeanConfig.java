package com.example.worknet.config.hessian;

import com.example.worknet.common.constant.ServiceURL;
import com.example.worknet.common.persistence.affair.administrator.service.AdministratorService;
import com.example.worknet.common.persistence.affair.companyContest.service.CompanyContestApplyService;
import com.example.worknet.common.persistence.affair.companyContest.service.CompanyContestService;
import com.example.worknet.common.persistence.affair.employment.service.CompanyCvService;
import com.example.worknet.common.persistence.affair.employment.service.CompanyInvitationService;
import com.example.worknet.common.persistence.affair.employment.service.CompanyProfessionService;
import com.example.worknet.common.persistence.affair.user.serivce.CompanyService;
import com.example.worknet.config.hessian.utils.HessianProxyFactoryUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: YunJieJiang
 * @Date: Created in 20:27 2019/6/28 0028
 */
@Configuration
public class HessianClientBeanConfig {

    @Bean
    public CompanyContestApplyService companyContestApplyService() {
        try {
            return HessianProxyFactoryUtil.getHessianClientBean(CompanyContestApplyService.class, ServiceURL.CompanyContestApplyService);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public CompanyContestService companyContestService() {
        try {
            return HessianProxyFactoryUtil.getHessianClientBean(CompanyContestService.class, ServiceURL.CompanyContestService);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public CompanyService companyService() {
        try {
            return HessianProxyFactoryUtil.getHessianClientBean(CompanyService.class, ServiceURL.CompanyService);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public CompanyProfessionService companyProfessionService() {
        try {
            return HessianProxyFactoryUtil.getHessianClientBean(CompanyProfessionService.class, ServiceURL.companyProfessionService);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public CompanyInvitationService companyInvitationService() {
        try {
            return HessianProxyFactoryUtil.getHessianClientBean(CompanyInvitationService.class, ServiceURL.companyInvitationService);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public CompanyCvService companyCompanyCvService() {
        try {
            return HessianProxyFactoryUtil.getHessianClientBean(CompanyCvService.class, ServiceURL.companyCvService);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public AdministratorService AdministratorService() {
        try {
            return HessianProxyFactoryUtil.getHessianClientBean(AdministratorService.class, ServiceURL.administratorService);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
