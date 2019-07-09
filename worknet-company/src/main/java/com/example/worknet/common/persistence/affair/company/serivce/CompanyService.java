package com.example.worknet.common.persistence.affair.company.serivce;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.example.worknet.common.persistence.template.modal.Company;
import com.example.worknet.common.persistence.template.modal.CompanyProfession;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 公司信息表 服务类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-06
 */
public interface CompanyService extends IService<Company> {

    HashMap<String,Object> getCompanyInfo(long companyId);
    Page<HashMap<String,Object>> getResumeList(Page<HashMap<String, Object>> page,String companyId,String searchText);
    boolean updateComInfo(Company company,Long userId);
    List<HashMap<String,Object>> getComEmployList(long companyId);
    Page<HashMap<String,Object>> getEmployList(Page<HashMap<String, Object>> page,String userId,String searchText);
    boolean endEmploy(long id);
    boolean changeEmployInfo(CompanyProfession companyProfession,long userId);
    boolean createEmployInfo(CompanyProfession companyProfession,long userId);
}
