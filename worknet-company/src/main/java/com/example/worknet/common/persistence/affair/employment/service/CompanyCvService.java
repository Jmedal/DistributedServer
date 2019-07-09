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

    Long deliverCompanyCv(CompanyCv companyCv);

    HashMap<String, Object> getCompanyCvInfo(Long companyCvId, Long userId);

    String getCompanyCvAvatarPath(Long companyCvId);

    boolean setCompanyCvAvatarPath(Long companyCvId, String headPath);

    Page<HashMap<String,Object>> getCompanyCvPage(Page<HashMap<String, Object>> pager, Long userId, String keyword);
}
