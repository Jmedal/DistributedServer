package com.example.worknet.common.persistence.affair.course.serivce.serviceImpl;

import com.example.worknet.common.persistence.template.modal.CourseClick;
import com.example.worknet.common.persistence.affair.course.dao.CourseClickMapper;
import com.example.worknet.common.persistence.affair.course.serivce.CourseClickService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * <p>
 * 课程点击记录表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Service
@Transactional
public class CourseClickServiceImpl extends ServiceImpl<CourseClickMapper, CourseClick> implements CourseClickService {

}
