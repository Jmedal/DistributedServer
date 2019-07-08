package com.example.worknet.common.persistence.affair.administrator.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.example.worknet.common.constant.UserConst;
import com.example.worknet.common.persistence.template.modal.LearnerInfo;
import com.example.worknet.common.persistence.template.modal.Administrator;
import com.example.worknet.common.persistence.template.modal.Company;

import java.util.HashMap;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
public interface AdministratorService extends IService<Administrator> {

    boolean verify(String account, String password);

    boolean userRegister(String account, String password, UserConst role);

    boolean changeUserPassword(Long userId, String password);

    boolean changeUserActivity(Long userId, Integer activity);

    Page<HashMap<String,Object>> getUserPage(Page<HashMap<String, Object>> page, UserConst role, String keyword);

    Page<HashMap<String,Object>> getUserInfoPage(Page<HashMap<String, Object>> page, UserConst role,String keyword);

    boolean changeLearnerInfo(LearnerInfo learnerInfo);

    boolean changeCompanyInfo(Company company);

    Page<HashMap<String,Object>> getCompanyCvPage(Page<HashMap<String, Object>> page, String keyword);

    Page<HashMap<String,Object>> getCompanyInvitationPage(Page<HashMap<String, Object>> page, String keyword);
}
