package com.example.worknet.common.persistence.affair.profession.serivce.serviceImpl;

import com.example.worknet.common.persistence.template.modal.ProfessionType;
import com.example.worknet.common.persistence.affair.profession.dao.ProfessionTypeMapper;
import com.example.worknet.common.persistence.affair.profession.serivce.ProfessionTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 职业分类 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Service
@Transactional
public class ProfessionTypeServiceImpl extends ServiceImpl<ProfessionTypeMapper, ProfessionType> implements ProfessionTypeService {

    /**
     * 获取职业分类
     * @return
     */
    @Override
    public List<HashMap<String, Object>> getProfessionType() {
        return professionTypeMapper.getProfessionType();
    }

    @Autowired
    private ProfessionTypeMapper  professionTypeMapper;
}
