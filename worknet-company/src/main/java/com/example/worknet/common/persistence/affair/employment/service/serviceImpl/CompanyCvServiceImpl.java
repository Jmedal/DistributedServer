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
@Transactional
public class CompanyCvServiceImpl extends ServiceImpl<CompanyCvMapper, CompanyCv> implements CompanyCvService {

    /**
     * 添加公司简历
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
     * 获取公司简历信息
     * @param companyCvId
     * @param userId
     * @return
     */
    @Override
    public HashMap<String, Object> getCompanyCvInfo(Long companyCvId, Long userId) {
        HashMap<String, Object> companyCv = (HashMap<String, Object>) super.selectMap(new EntityWrapper<CompanyCv>().eq("id", companyCvId));
        if(Long.valueOf(companyCv.get("userId").toString()).equals(userId))
            return companyCv;
        return null;
    }


    /**
     * 获取用户投放的简历
     */
    @Override
    public Page<HashMap<String,Object>> getCompanyCvPage(Page<HashMap<String, Object>> pager, String userId, String searchText){
        if(userId == null || userId.equals("null") || userId.equals(""))
            userId = "[digit]*";
        if(searchText == null || searchText.equals("null") || searchText.equals(""))
            searchText = "[\\w]*";
        else
            searchText = searchText.trim().replaceAll("\\s+"," ").replace(" ","|");
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        return page.setRecords(companyCvMapper.getMyResume(page,userId,searchText));
    }

    @Autowired
    private CompanyCvMapper companyCvMapper;
}
