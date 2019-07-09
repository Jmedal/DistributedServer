package com.example.worknet.common.persistence.affair.user.serivce.serviceImpl;

import com.example.worknet.common.persistence.affair.user.serivce.TeacherInfoService;
import com.example.worknet.common.persistence.affair.user.dao.TeacherInfoMapper;
import com.example.worknet.common.persistence.template.modal.TeacherInfo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 讲师信息登记表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class TeacherInfoServiceImpl extends ServiceImpl<TeacherInfoMapper, TeacherInfo> implements TeacherInfoService {

}
