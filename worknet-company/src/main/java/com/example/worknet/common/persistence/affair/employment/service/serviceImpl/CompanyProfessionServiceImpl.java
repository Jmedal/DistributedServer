package com.example.worknet.common.persistence.affair.employment.service.serviceImpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.constant.ProfessionConst;
import com.example.worknet.common.persistence.affair.employment.dao.CompanyProfessionMapper;
import com.example.worknet.common.persistence.affair.employment.service.CompanyProfessionService;
import com.example.worknet.common.persistence.template.modal.CompanyProfession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业发布岗位表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Service
public class CompanyProfessionServiceImpl extends ServiceImpl<CompanyProfessionMapper, CompanyProfession> implements CompanyProfessionService {


    /**
     * 获取职业分类
     * @return
     */
    @Override
    public List<HashMap<String, Object>> getProfessionType() {
        return companyProfessionMapper.getProfessionType();
    }

    /**
     * 获取某公司招聘岗位
     * @return
     */
    @Override
    public Page<HashMap<String,Object>> getProfessionPage(Page<HashMap<String, Object>> page, ProfessionConst type, String professionId,String location,String field,String keyword){
        if(professionId==null||professionId.equals(""))
            professionId="[digit]*";
        if(keyword==null||keyword.equals(""))
            keyword="[\\w]*";
        else
            keyword.replace(" ","|");
        switch(type){
            case PROFESSION_NEW:
                return page.setRecords(companyProfessionMapper.getNewProfessionPage(page,professionId, location, field,keyword));
            case PROFESSION_SALARY:
                return page.setRecords(companyProfessionMapper.getSalaryProfessionPage(page,professionId, location, field,keyword));
            case PROFESSION_DEFAULT:
                return page.setRecords(companyProfessionMapper.getDefaultProfessionPage(page,professionId, location, field,keyword));
            default:
                return page.setRecords(new ArrayList<HashMap<String,Object>>());
        }

    }


    @Autowired
    private CompanyProfessionMapper companyProfessionMapper;
}
