package com.example.worknet.common.persistence.affair.course.serivce.serviceImpl;

import com.example.worknet.common.persistence.template.modal.CourseContestOption;
import com.example.worknet.common.persistence.affair.course.dao.CourseContestOptionMapper;
import com.example.worknet.common.persistence.affair.course.serivce.CourseContestOptionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 选项表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-02
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CourseContestOptionServiceImpl extends ServiceImpl<CourseContestOptionMapper, CourseContestOption> implements CourseContestOptionService {

}
