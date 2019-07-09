package com.example.worknet.common.persistence.affair.user.serivce;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.example.worknet.common.persistence.template.modal.Company;
import com.example.worknet.common.persistence.template.modal.CompanyProfession;

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

    HashMap<String,Object> getCompanyInfo(Long companyId);

    HashMap<String,Object> getCompanyInfoByUserId(Long userId);

    Page<HashMap<String,Object>> getResumePage(Page<HashMap<String, Object>> pager, Long userId, String keyword);

    boolean updateCompanyInfo(Company company);

    List<HashMap<String,Object>> getComEmployList(Long companyId);

    Page<HashMap<String,Object>> getCompanyEmployPage(Page<HashMap<String, Object>> pager, String userId, String keyword);

    boolean endEmploy(Long companyProfessionId);

    boolean changeEmployInfo(CompanyProfession companyProfession, Long userId);

    boolean createEmployInfo(CompanyProfession companyProfession, Long userId);

    Page<HashMap<String,Object>> getWelcomePage(Page<HashMap<String, Object>> pager, Long userId, String keyword);

    boolean changeResumeStatus(Long resumeId, Integer status, Long userId);

    boolean welcomeStudent(Long userId, Long studentId, Long companyProfessionId);

    Page<HashMap<String,Object>> getLearnerInfoPage(Page<HashMap<String, Object>> pager, String keyword);
}
