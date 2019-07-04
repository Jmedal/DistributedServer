package com.example.worknet.common.persistence.affair.employment.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.worknet.common.persistence.template.modal.CompanyProfession;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 企业发布岗位表 Mapper 接口
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Component
public interface CompanyProfessionMapper extends BaseMapper<CompanyProfession> {

    @Select("SELECT\n" +
            "\tsys_company_profession.id,\n" +
            "\tsys_company_profession.company_id,\n" +
            "\tsys_company_profession.title,\n" +
            "\tsys_company_profession.is_practice,\n" +
            "\tsys_company_profession.salary,\n" +
            "\tsys_company_profession.location,\n" +
            "\tsys_company.NAME \n" +
            "FROM\n" +
            "\tsys_company_profession\n" +
            "\tJOIN sys_company ON sys_company.id = sys_company_profession.company_id \n" +
            "WHERE\n" +
            "\tsys_company_profession.state=1\n" +
            "\tAND sys_company_profession.profession_type_id REGEXP #{professionId}\n" +
            "\tAND sys_company_profession.location LIKE CONCAT( '%', #{location}, '%' ) \n" +
            "\tAND sys_company.field LIKE CONCAT( '%', #{field}, '%' ) \n" +
            "\tAND  ( sys_company.NAME REGEXP #{keyword} OR sys_company_profession.title REGEXP #{keyword} ) \n" +
            "ORDER BY\n" +
            "\tsys_company_profession.start_time DESC")
    @Results(id = "companyProfessionResultMap1",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "companyId",column = "company_id"),
            @Result(property = "title",column = "title"),
            @Result(property = "isPractice",column = "is_practice", javaType = Boolean.class),
            @Result(property = "companyName",column = "NAME"),
            @Result(property = "salary",column = "salary"),
            @Result(property = "location",column = "location"),
    })
    List<HashMap<String,Object>> getNewProfessionPage(Pagination pagination,
                                                  @Param("professionId") String professionId,
                                                  @Param("location") String location,
                                                  @Param("field") String field,
                                                  @Param("keyword")String keyword);

    @Select("SELECT\n" +
            "\tsys_company_profession.id,\n" +
            "\tsys_company_profession.company_id,\n" +
            "\tsys_company_profession.title,\n" +
            "\tsys_company_profession.is_practice,\n" +
            "\tsys_company_profession.salary,\n" +
            "\tsys_company_profession.location,\n" +
            "\tsys_company.NAME \n" +
            "FROM\n" +
            "\tsys_company_profession\n" +
            "\tJOIN sys_company ON sys_company.id = sys_company_profession.company_id \n" +
            "WHERE\n" +
            "\tsys_company_profession.state=1\n" +
            "\tAND sys_company_profession.profession_type_id REGEXP #{professionId}\n" +
            "\tAND sys_company_profession.location LIKE CONCAT( '%', #{location}, '%' ) \n" +
            "\tAND sys_company.field LIKE CONCAT( '%', #{field}, '%' ) \n" +
            "\tAND  ( sys_company.NAME REGEXP #{keyword} OR sys_company_profession.title REGEXP #{keyword} ) \n" +
            "ORDER BY\n" +
            "\tsys_company_profession.salary DESC")
    @Results(id = "companyProfessionResultMap2",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "companyId",column = "company_id"),
            @Result(property = "title",column = "title"),
            @Result(property = "isPractice",column = "is_practice", javaType = Boolean.class),
            @Result(property = "companyName",column = "NAME"),
            @Result(property = "salary",column = "salary"),
            @Result(property = "location",column = "location"),
    })
    List<HashMap<String,Object>> getSalaryProfessionPage(Pagination pagination,
                                                  @Param("professionId") String professionId,
                                                  @Param("location") String location,
                                                  @Param("field") String field,
                                                  @Param("keyword")String keyword);

    @Select("SELECT\n" +
            "\tsys_company_profession.id,\n" +
            "\tsys_company_profession.company_id,\n" +
            "\tsys_company_profession.title,\n" +
            "\tsys_company_profession.is_practice,\n" +
            "\tsys_company_profession.salary,\n" +
            "\tsys_company_profession.location,\n" +
            "\tsys_company.NAME \n" +
            "FROM\n" +
            "\tsys_company_profession\n" +
            "\tJOIN sys_company ON sys_company.id = sys_company_profession.company_id \n" +
            "WHERE\n" +
            "\tsys_company_profession.state=1\n" +
            "\tAND sys_company_profession.profession_type_id REGEXP #{professionId}\n" +
            "\tAND sys_company_profession.location LIKE CONCAT( '%', #{location}, '%' ) \n" +
            "\tAND sys_company.field LIKE CONCAT( '%', #{field}, '%' ) \n" +
            "\tAND  ( sys_company.NAME REGEXP #{keyword} OR sys_company_profession.title REGEXP #{keyword} ) \n" +
            "ORDER BY\n" +
            "\tsys_company_profession.id DESC")
    @Results(id = "companyProfessionResultMap3",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "companyId",column = "company_id"),
            @Result(property = "title",column = "title"),
            @Result(property = "isPractice",column = "is_practice", javaType = Boolean.class),
            @Result(property = "companyName",column = "NAME"),
            @Result(property = "salary",column = "salary"),
            @Result(property = "location",column = "location"),
    })
    List<HashMap<String,Object>> getDefaultProfessionPage(Pagination pagination,
                                                     @Param("professionId") String professionId,
                                                     @Param("location") String location,
                                                     @Param("field") String field,
                                                     @Param("keyword")String keyword);

    @Select("SELECT\n" +
            "\tsys_company_profession.id,\n" +
            "\tsys_company_profession.company_id,\n" +
            "\tsys_company_profession.title,\n" +
            "\tsys_company.NAME,\n" +
            "\tsys_company_profession.salary,\n" +
            "\tsys_company_profession.is_practice,\n" +
            "\tsys_company_profession.location,\n" +
            "\tsys_company_profession.state\n" +
            "FROM\n" +
            "\tsys_company_profession\n" +
            "\tJOIN sys_company ON sys_company.id = sys_company_profession.company_id\n" +
            "WHERE\n" +
            "\tsys_company_profession.company_id= #{companyId}")
    @Results(id = "companyProfessionResultMap4",value = {
            @Result(property = "companyId",column = "company_id"),
            @Result(property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "isPractice",column = "is_practice", javaType = Boolean.class),
            @Result(property = "companyName",column = "NAME"),
            @Result(property = "salary",column = "salary"),
            @Result(property = "location",column = "location"),
            @Result(property = "state",column = "state"),
    })
    List<HashMap<String,Object>> getEmployList(Pagination pagination,@Param("companyId") Long companyId);

    @Select("SELECT\n" +
            "sys_company_profession.introduction,\n" +
            "sys_company_profession.requirement,\n" +
            "sys_company_profession.salary,\n" +
            "sys_company_profession.title,\n" +
            "sys_company_profession.location,\n" +
            "sys_company_profession.duration,\n" +
            "sys_company_profession.chance_to_formal,\n" +
            "sys_company_profession.is_practice,\n" +
            "sys_profession_type.type_name,\n" +
            "sys_company_profession.state\n" +
            "FROM\n" +
            "sys_company_profession JOIN sys_profession_type\n" +
            "ON\n" +
            "sys_company_profession.profession_type_id=sys_profession_type.id\n" +
            "where \n" +
            "sys_company_profession.id=#{employeeId}")
    @Results(id = "companyJobInfoResultMap",value = {
            @Result(property = "introduction",column = "introduction"),
            @Result(property = "requirement",column = "requirement"),
            @Result(property = "salary",column = "salary"),
            @Result(property = "title",column = "title"),
            @Result(property = "location",column = "location"),
            @Result(property = "duration",column = "duration"),
            @Result(property = "chanceToFormal",column = "chance_to_formal"),
            @Result(property = "isPractice",column = "is_practice", javaType = Boolean.class),
            @Result(property = "profession",column = "type_name"),
            @Result(property = "state",column = "state"),
    })
    HashMap<String,Object> getJobInfo(@Param("employeeId") Long employeeId);
}
