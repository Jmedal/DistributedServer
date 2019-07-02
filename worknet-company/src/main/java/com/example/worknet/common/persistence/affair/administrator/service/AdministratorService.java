package com.example.worknet.common.persistence.affair.administrator.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.worknet.common.persistence.template.modal.Administrator;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
public interface AdministratorService extends IService<Administrator> {

    Administrator getAdministratorInfo(String account);

    boolean verify(String account, String password);

    boolean checkAccount(String account);
}
