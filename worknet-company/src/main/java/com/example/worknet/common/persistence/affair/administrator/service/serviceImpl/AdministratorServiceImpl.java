package com.example.worknet.common.persistence.affair.administrator.service.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.persistence.affair.administrator.dao.AdministratorMapper;
import com.example.worknet.common.persistence.affair.administrator.service.AdministratorService;
import com.example.worknet.common.persistence.template.modal.Administrator;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {

    @Override
    public Administrator getAdministratorInfo(String account){
        return null;
    }

    @Override
    public boolean verify(String account,String password){
        return false;
    }

    @Override
    public boolean checkAccount(String account){
        return false;
    }
}
