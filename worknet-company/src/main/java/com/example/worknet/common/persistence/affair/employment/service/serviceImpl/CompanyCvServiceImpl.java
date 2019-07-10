package com.example.worknet.common.persistence.affair.employment.service.serviceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.persistence.affair.employment.dao.CompanyCvMapper;
import com.example.worknet.common.persistence.affair.employment.service.CompanyCvService;
import com.example.worknet.common.persistence.template.modal.CompanyCv;
import com.example.worknet.common.persistence.template.modal.CompanyInvitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.OverridesAttribute;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 * 简历表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CompanyCvServiceImpl extends ServiceImpl<CompanyCvMapper, CompanyCv> implements CompanyCvService {

    /**
     * 添加投递简历
     * @param companyCv
     * @return
     */
    @Override
    public Long deliverCompanyCv(CompanyCv companyCv){
        if(super.insert(companyCv))
            return companyCv.getId();
        return null;
    }

    /**
     * 获取投递简历信息
     * @param companyCvId
     * @return
     */
    @Override
    public HashMap<String, Object> getCompanyCvInfo(Long companyCvId) {
        return companyCvMapper.getCompanyCvInfo(companyCvId);
    }

    /**
     * 获取简历头像相对路径
     * @param companyCvId
     * @return
     */
    @Override
    public String getCompanyCvAvatarPath(Long companyCvId) {
        CompanyCv companyCv = super.selectById(companyCvId);
        if(companyCv != null)
            return companyCv.getHeadPath();
        return null;
    }

    /**
     * 更新简历头像相对路径
     * @param companyCvId
     * @param headPath
     * @return
     */
    @Override
    public boolean setCompanyCvAvatarPath(Long companyCvId, String headPath) {
        CompanyCv companyCv = super.selectById(companyCvId);
        if(companyCv != null){
            companyCv.setHeadPath(headPath);
            return super.updateById(companyCv);
        }
        return false;
    }

    /**
     * 获取投递简历列表
     * @param pager
     * @param userId
     * @param keyword
     * @return
     */
    @Override
    public Page<HashMap<String,Object>> getCompanyCvPage(Page<HashMap<String, Object>> pager, Long userId, String keyword){
        if(keyword == null || keyword.equals("null") || keyword.equals(""))
            keyword = "[\\w]*";
        else
            keyword = keyword.trim().replaceAll("\\s+"," ").replace(" ","|");
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(), pager.getSize());
        return page.setRecords(companyCvMapper.getCompanyCvPage(page, userId, keyword));
    }

    @Autowired
    private CompanyCvMapper companyCvMapper;
}
