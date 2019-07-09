package com.example.worknet.common.persistence.affair.course.serivce.serviceImpl;

import com.example.worknet.common.persistence.template.modal.CourseIndex;
import com.example.worknet.common.persistence.affair.course.dao.CourseIndexMapper;
import com.example.worknet.common.persistence.affair.course.serivce.CourseIndexService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 课程目录表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CourseIndexServiceImpl extends ServiceImpl<CourseIndexMapper, CourseIndex> implements CourseIndexService {

}
