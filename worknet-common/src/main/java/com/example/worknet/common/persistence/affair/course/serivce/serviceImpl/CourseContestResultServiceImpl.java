package com.example.worknet.common.persistence.affair.course.serivce.serviceImpl;

import com.example.worknet.common.persistence.template.modal.CourseContestResult;
import com.example.worknet.common.persistence.affair.course.dao.CourseContestResultMapper;
import com.example.worknet.common.persistence.affair.course.serivce.CourseContestResultService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 单元测试结果表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CourseContestResultServiceImpl extends ServiceImpl<CourseContestResultMapper, CourseContestResult> implements CourseContestResultService {

}
