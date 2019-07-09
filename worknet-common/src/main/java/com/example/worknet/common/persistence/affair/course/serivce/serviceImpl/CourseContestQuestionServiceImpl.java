package com.example.worknet.common.persistence.affair.course.serivce.serviceImpl;

import com.example.worknet.common.persistence.template.modal.CourseContestQuestion;
import com.example.worknet.common.persistence.affair.course.dao.CourseContestQuestionMapper;
import com.example.worknet.common.persistence.affair.course.serivce.CourseContestQuestionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 问题表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-02
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CourseContestQuestionServiceImpl extends ServiceImpl<CourseContestQuestionMapper, CourseContestQuestion> implements CourseContestQuestionService {

}
