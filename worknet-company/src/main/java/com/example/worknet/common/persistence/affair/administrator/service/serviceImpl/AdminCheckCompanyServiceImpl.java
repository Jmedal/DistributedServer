package com.example.worknet.common.persistence.affair.administrator.service.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.persistence.affair.administrator.dao.AdminCheckCompanyMapper;
import com.example.worknet.common.persistence.affair.administrator.service.AdminCheckCompanyService;
import com.example.worknet.common.persistence.template.modal.AdminCheckCompany;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * <p>
 * 特殊账号注册审核记录表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Service
@Transactional
public class AdminCheckCompanyServiceImpl extends ServiceImpl<AdminCheckCompanyMapper, AdminCheckCompany> implements AdminCheckCompanyService {

}
