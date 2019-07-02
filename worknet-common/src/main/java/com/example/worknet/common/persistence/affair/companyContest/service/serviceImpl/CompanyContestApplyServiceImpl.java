package com.example.worknet.common.persistence.affair.companyContest.service.serviceImpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.persistence.affair.companyContest.service.CompanyContestApplyService;
import com.example.worknet.common.persistence.template.modal.CompanyContestApply;
import com.example.worknet.common.persistence.affair.companyContest.dao.CompanyContestApplyMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 公司笔试报名表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-25
 */
@Service
public class CompanyContestApplyServiceImpl extends ServiceImpl<CompanyContestApplyMapper, CompanyContestApply> implements CompanyContestApplyService {

    @Override
    public Page<HashMap<String, Object>> getUserContestPage(Page<HashMap<String, Object>> page, Long uid) {
        return null;
    }

    @Override
    public boolean insertContestApply(Long uid, Long eid) {
        return false;
    }

}
