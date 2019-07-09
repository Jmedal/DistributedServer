package com.example.worknet.common.persistence.affair.api.profession;

import com.baomidou.mybatisplus.service.IService;
import com.example.worknet.common.persistence.template.modal.ProfessionType;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 职业分类 服务类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
public interface ProfessionTypeService extends IService<ProfessionType> {

    List<HashMap<String,Object>> getProfessionType();
}
