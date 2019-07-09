package com.example.worknet.common.persistence.affair.curriculum.serivce.serviceImpl;

import com.example.worknet.common.persistence.template.modal.CurriculumCourse;
import com.example.worknet.common.persistence.affair.curriculum.dao.CurriculumCourseMapper;
import com.example.worknet.common.persistence.affair.curriculum.serivce.CurriculumCourseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 科目_课程关系表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CurriculumCourseServiceImpl extends ServiceImpl<CurriculumCourseMapper, CurriculumCourse> implements CurriculumCourseService {

}
