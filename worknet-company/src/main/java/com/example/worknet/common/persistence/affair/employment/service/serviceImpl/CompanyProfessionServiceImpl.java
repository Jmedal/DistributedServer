package com.example.worknet.common.persistence.affair.employment.service.serviceImpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.constant.ProfessionConst;
import com.example.worknet.common.persistence.affair.employment.dao.CompanyProfessionMapper;
import com.example.worknet.common.persistence.affair.employment.service.CompanyProfessionService;
import com.example.worknet.common.persistence.template.modal.CompanyProfession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 企业发布岗位表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Service
@Transactional
public class CompanyProfessionServiceImpl extends ServiceImpl<CompanyProfessionMapper, CompanyProfession> implements CompanyProfessionService {

    /**
     * 筛选分页获取公司招聘列表
     * @param pager
     * @param type
     * @param professionId
     * @param location
     * @param field
     * @param keyword
     * @return
     */
    @Override
    public Page<HashMap<String,Object>> getProfessionPage(Page<HashMap<String, Object>> pager, ProfessionConst type,
                                                          String professionId, String location, String field, String keyword){
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        if(professionId == null || professionId.equals("null") || professionId.equals(""))
            professionId = "[digit]*";
        if(keyword == null || keyword.equals("null") || keyword.equals(""))
            keyword = "[\\w]*";
        else
            keyword = keyword.trim().replaceAll("\\s+","|");
        switch(type){
            case PROFESSION_NEW:
                return page.setRecords(companyProfessionMapper.getNewProfessionPage(page, professionId, location, field, keyword));
            case PROFESSION_SALARY:
                return page.setRecords(companyProfessionMapper.getSalaryProfessionPage(page, professionId, location, field, keyword));
            case PROFESSION_DEFAULT:
                return page.setRecords(companyProfessionMapper.getDefaultProfessionPage(page, professionId, location, field, keyword));
            default:
                return page.setRecords(new ArrayList<HashMap<String,Object>>());
        }
    }

    /**
     * 根据公司id,分页获取该公司招聘列表
     * @param pager
     * @param companyId
     * @return
     */
    @Override
    public Page<HashMap<String,Object>> getEmployeeList(Page<HashMap<String, Object>> pager, Long companyId){
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        return page.setRecords(companyProfessionMapper.getEmployList(page,companyId));
    }

    /**
     * 获取公司招聘信息
     * @param employeeId
     * @return
     */
    @Override
    public HashMap<String, Object> getJobInfo(Long employeeId){
        return companyProfessionMapper.getJobInfo(employeeId);
    }

    @Autowired
    private CompanyProfessionMapper companyProfessionMapper;
}
