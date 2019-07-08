package com.example.worknet.common.persistence.affair.employment.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.persistence.template.modal.CompanyInvitation;
import com.baomidou.mybatisplus.service.IService;

import java.util.HashMap;

/**
 * <p>
 * 面试邀约表 服务类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
public interface CompanyInvitationService extends IService<CompanyInvitation> {

    Page<HashMap<String,Object>> getCompanyInvitationPage(Page<HashMap<String, Object>> page, Long userId, String keyword);

    boolean changeInvitationStatus(Long inviteId, Long userId, Integer status);
}
