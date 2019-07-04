package com.example.worknet.common.persistence.affair.profession.dao;

import com.example.worknet.common.persistence.template.modal.ProfessionType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 职业分类 Mapper 接口
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Component
public interface ProfessionTypeMapper extends BaseMapper<ProfessionType> {

    @Select("select " +
            "id," +
            "type_name " +
            "from sys_profession_type ")
    @Results(id = "professionTypeResultMap",value = {
            @Result(property = "professionId",column = "id"),
            @Result(property = "profession",column = "type_name"),
    })
    List<HashMap<String,Object>> getProfessionType();
}
