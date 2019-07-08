package com.example.worknet.common.persistence.affair.employment.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.example.worknet.common.persistence.template.modal.CompanyCv;

import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 * 简历表 服务类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
public interface CompanyCvService extends IService<CompanyCv> {

    long deliverResume(Long companyProfessionId,
                       Long userId,
                       String name,
                       Integer sex,
                       Date birth,
                       String nativePlace,
                       String identity,
                       String qualification,
                       String specialty,
                       String university,
                       String tel,
                       String experience,
                       String mailbox,
                       String introduction,
                       String diploma,
                       String currentLocation,
                       String inJobTime,
                       Integer status);

    HashMap<String, Object> getCvInfo(Long resumeId);

    Page<HashMap<String,Object>> getMyResumePage(Page<HashMap<String, Object>> pager, String userId, String searchText);
}
