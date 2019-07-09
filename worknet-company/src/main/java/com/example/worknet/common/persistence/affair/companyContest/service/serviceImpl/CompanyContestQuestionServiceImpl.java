package com.example.worknet.common.persistence.affair.companyContest.service.serviceImpl;

import com.example.worknet.common.persistence.template.modal.CompanyContestQuestion;
import com.example.worknet.common.persistence.affair.companyContest.dao.CompanyContestQuestionMapper;
import com.example.worknet.common.persistence.affair.companyContest.service.CompanyContestQuestionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * <p>
 * 公司笔试问题表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-02
 */
@Service
@Transactional
public class CompanyContestQuestionServiceImpl extends ServiceImpl<CompanyContestQuestionMapper, CompanyContestQuestion> implements CompanyContestQuestionService {

}
