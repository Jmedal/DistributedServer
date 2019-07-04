package com.example.worknet.common.persistence.affair.employment.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.example.worknet.common.constant.ProfessionConst;
import com.example.worknet.common.persistence.template.modal.CompanyProfession;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业发布岗位表 服务类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
public interface CompanyProfessionService extends IService<CompanyProfession> {

     Page<HashMap<String,Object>> getProfessionPage(Page<HashMap<String, Object>> page, ProfessionConst type, String professionId, String location, String field, String keyword);

     Page<HashMap<String,Object>> getEmployeeList(Page<HashMap<String, Object>> page, Long companyId);

     HashMap<String,Object> getJobInfo(Long employeeId);
}
