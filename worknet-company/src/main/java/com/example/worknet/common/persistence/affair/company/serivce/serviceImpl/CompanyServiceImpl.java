package com.example.worknet.common.persistence.affair.company.serivce.serviceImpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.persistence.affair.company.dao.CompanyMapper;
import com.example.worknet.common.persistence.affair.company.serivce.CompanyService;
import com.example.worknet.common.persistence.template.modal.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 公司信息表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-06
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    /**
     * 获取公司信息
     * @param companyId
     * @return
     */
    @Override
    public HashMap<String,Object> getCompanyInfo(long companyId){
        return companyMapper.getCompanyInfo(companyId);
    }

    @Override
    public Page<HashMap<String,Object>> getResumeList(Page<HashMap<String, Object>> page, String companyId, String searchText){
        if(companyId == null || companyId.equals("null") || companyId.equals(""))
            companyId = "[digit]*";
        if(searchText == null || searchText.equals("null") || searchText.equals(""))
            searchText = "[\\w]*";
        else
            searchText = searchText.trim().replaceAll("\\s+"," ").replace(" ","|");
        return page.setRecords(companyMapper.getResumeList(page,companyId,searchText));
    }

    @Override
    public boolean updateComInfo(Company company, Long userId) {
        if(userId==null)
            return false;
        Company temp=selectById(company.getId());
        if (temp.getUserId()==userId) {
            super.updateById(company);
            return true;
        }else
            return false;
    }

    @Override
    public List<HashMap<String, Object>> getComEmployList(long userId) {
        return companyMapper.getProfessionList(userId);
    }

    @Autowired
    private CompanyMapper companyMapper;
}
