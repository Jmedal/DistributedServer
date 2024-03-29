package com.example.worknet.common.persistence.affair.profession.serivce.serviceImpl;

import com.example.worknet.common.persistence.template.modal.Profession;
import com.example.worknet.common.persistence.affair.profession.dao.ProfessionMapper;
import com.example.worknet.common.persistence.affair.profession.serivce.ProfessionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 职业表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-25
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class ProfessionServiceImpl extends ServiceImpl<ProfessionMapper, Profession> implements ProfessionService {

}
