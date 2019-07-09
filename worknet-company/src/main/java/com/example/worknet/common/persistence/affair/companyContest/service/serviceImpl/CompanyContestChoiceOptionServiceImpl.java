package com.example.worknet.common.persistence.affair.companyContest.service.serviceImpl;

import com.example.worknet.common.persistence.template.modal.CompanyContestChoiceOption;
import com.example.worknet.common.persistence.affair.companyContest.dao.CompanyContestChoiceOptionMapper;
import com.example.worknet.common.persistence.affair.companyContest.service.CompanyContestChoiceOptionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 公司笔试选项表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-02
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CompanyContestChoiceOptionServiceImpl extends ServiceImpl<CompanyContestChoiceOptionMapper, CompanyContestChoiceOption> implements CompanyContestChoiceOptionService {

}
