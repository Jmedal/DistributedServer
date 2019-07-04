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
    @Select("select " +
            "id," +
            "type_name " +
            "from sys_profession_type ")
    @Results(id = "professionTypeResultMap",value = {
            @Result(property = "professionId",column = "id"),
            @Result(property = "profession",column = "type_name"),
    })
    List<HashMap<String,Object>> getProfessionType();

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
            @Result(property = "isPractice",column = "is_practice"),
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
            @Result(property = "isPractice",column = "is_practice"),
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
            @Result(property = "isPractice",column = "is_practice"),
            @Result(property = "companyName",column = "NAME"),
            @Result(property = "salary",column = "salary"),
            @Result(property = "location",column = "location"),
    })
    List<HashMap<String,Object>> getDefaultProfessionPage(Pagination pagination,
                                                     @Param("professionId") String professionId,
                                                     @Param("location") String location,
                                                     @Param("field") String field,
                                                     @Param("keyword")String keyword);
}
