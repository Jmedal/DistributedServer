package com.example.worknet.common.persistence.affair.user.serivce.serviceImpl;

import com.example.worknet.common.persistence.affair.user.serivce.CompanyService;
import com.example.worknet.common.persistence.affair.user.dao.CompanyMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.persistence.template.modal.Company;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

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

    @Override
    public Resource getCompanyAvatar(long companyId, String strDirPath) {
        return null;
    }
}
