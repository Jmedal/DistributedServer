package com.example.worknet.common.persistence.affair.company.serivce.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.persistence.affair.company.dao.CompanyMapper;
import com.example.worknet.common.persistence.affair.company.serivce.CompanyService;
import com.example.worknet.common.persistence.template.modal.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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

    @Autowired
    private CompanyMapper companyMapper;
}
