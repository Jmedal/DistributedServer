package com.example.worknet.common.persistence.affair.companyContest.service.serviceImpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.constant.ContestConst;
import com.example.worknet.common.persistence.affair.companyContest.service.CompanyContestService;
import com.example.worknet.common.persistence.template.modal.CompanyContest;
import com.example.worknet.common.persistence.affair.companyContest.dao.CompanyContestMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;



/**
 * <p>
 * 公司笔试表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-25
 */
@Service
public class CompanyContestServiceImpl extends ServiceImpl<CompanyContestMapper, CompanyContest> implements CompanyContestService {

    @Override
    public Page<HashMap<String,Object>> getContestPage(Page<HashMap<String, Object>> page, ContestConst type, String keyword){
        return null;
    }

    @Override
    public HashMap<String,Object> getContestInfo(Long eid, Long uid){
        return null;
    }

}
