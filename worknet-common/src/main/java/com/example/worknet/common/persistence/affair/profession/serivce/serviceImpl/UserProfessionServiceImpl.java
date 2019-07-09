package com.example.worknet.common.persistence.affair.profession.serivce.serviceImpl;

import com.example.worknet.common.persistence.template.modal.UserProfession;
import com.example.worknet.common.persistence.affair.profession.dao.UserProfessionMapper;
import com.example.worknet.common.persistence.affair.profession.serivce.UserProfessionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户选择职业表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-06-04
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class UserProfessionServiceImpl extends ServiceImpl<UserProfessionMapper, UserProfession> implements UserProfessionService {

}
