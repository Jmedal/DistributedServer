package com.example.worknet.common.persistence.affair.company.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.worknet.common.persistence.template.modal.Company;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 公司信息表 Mapper 接口
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-06
 */
@Component
public interface CompanyMapper extends BaseMapper<Company> {
    @Select("select\n" +
            "name,\n" +
            "introduction,\n" +
            "address,\n" +
            "communication,\n" +
            "website,\n" +
            "field\n" +
            "FROM\n" +
            "sys_company\n" +
            "WHERE \n" +
            "sys_company.id=#{companyId}")
    @Results(id = "companyInfoResultMap",value = {
            @Result(property = "name",column = "name"),
            @Result(property = "introduction",column = "introduction"),
            @Result(property = "address",column = "address"),
            @Result(property = "communication",column = "communication"),
            @Result(property = "website",column = "website"),
            @Result(property = "field",column = "field"),
    })
    HashMap<String,Object> getCompanyInfo( @Param("companyId") Long companyId);
}
