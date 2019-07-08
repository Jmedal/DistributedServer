package com.example.worknet.common.persistence.affair.employment.service.serviceImpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.persistence.affair.employment.dao.CompanyInvitationMapper;
import com.example.worknet.common.persistence.affair.employment.service.CompanyInvitationService;
import com.example.worknet.common.persistence.template.modal.CompanyInvitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 面试邀约表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Service
public class CompanyInvitationServiceImpl extends ServiceImpl<CompanyInvitationMapper, CompanyInvitation> implements CompanyInvitationService {

    /**
     * 获取公司招聘邀请列表
     * @param pager
     * @param userId
     * @param keyword
     * @return
     */
    @Override
    public Page<HashMap<String,Object>> getCompanyInvitationPage(Page<HashMap<String, Object>> pager, Long userId, String keyword){
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        return page.setRecords(companyInvitationMapper.getCompanyInvitationPage(page,userId,keyword));
    }

    /**
     * 更新公司招聘邀请状态
     * @param inviteId
     * @param userId
     * @param status
     * @return
     */
    @Override
    public boolean changeInvitationStatus(Long inviteId, Long userId, Integer status){
        CompanyInvitation companyInvitation = super.selectById(inviteId);
        if(companyInvitation != null && companyInvitation.getUserId().equals(userId)
                && companyInvitation.getStatus().equals(0)) {
            companyInvitation.setStatus(status);
            return super.updateById(companyInvitation);
        }
        return false;
    }

    @Autowired
    private CompanyInvitationMapper companyInvitationMapper;
}
