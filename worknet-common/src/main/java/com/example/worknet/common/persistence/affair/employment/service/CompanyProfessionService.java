package com.example.worknet.common.persistence.affair.employment.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.constant.ProfessionConst;
import com.example.worknet.common.persistence.template.modal.CompanyProfession;
import com.baomidou.mybatisplus.service.IService;

import java.util.HashMap;


/**
 * <p>
 * 企业发布岗位表 服务类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
public interface CompanyProfessionService extends IService<CompanyProfession> {

    Page<HashMap<String, Object>> getProfessionPage(Page<HashMap<String, Object>> pager, ProfessionConst type, String professionId, String location, String field, String keyword);

    Page<HashMap<String, Object>> getEmployeeList(Page<HashMap<String, Object>> pager, Long companyId);

    HashMap<String,Object> getJobInfo(Long employeeId);
}
