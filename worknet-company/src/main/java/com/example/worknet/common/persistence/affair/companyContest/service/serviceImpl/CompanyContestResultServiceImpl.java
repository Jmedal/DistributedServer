package com.example.worknet.common.persistence.affair.companyContest.service.serviceImpl;

import com.example.worknet.common.persistence.template.modal.CompanyContestResult;
import com.example.worknet.common.persistence.affair.companyContest.dao.CompanyContestResultMapper;
import com.example.worknet.common.persistence.affair.companyContest.service.CompanyContestResultService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 公司笔试回答表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CompanyContestResultServiceImpl extends ServiceImpl<CompanyContestResultMapper, CompanyContestResult> implements CompanyContestResultService {

}
