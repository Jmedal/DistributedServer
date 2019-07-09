package com.example.worknet.common.persistence.affair.employment.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.worknet.common.persistence.template.modal.CompanyCv;
import org.apache.ibatis.annotations.*;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 简历表 Mapper 接口
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Component
public interface CompanyCvMapper extends BaseMapper<CompanyCv> {

    @Select("SELECT\n" +
            "id,\n" +
            "user_id,\n" +
            "name,\n" +
            "sex,\n" +
            "birth,\n" +
            "native_place,\n" +
            "identity,\n" +
            "qualification,\n" +
            "specialty,\n" +
            "university,\n" +
            "tel,\n" +
            "experience,\n" +
            "mailbox,\n" +
            "introduction,\n" +
            "diploma,\n" +
            "head_path\n" +
            "FROM\n" +
            "sys_company_cv\n" +
            "WHERE\n" +
            "id = #{companyCvId} and user_id = #{userId}")
    @Results(id = "companyCvInfoResultMap",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "learnerId",column = "user_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "birth",column = "birth"),
            @Result(property = "nativePlace",column = "native_place"),
            @Result(property = "identity",column = "identity"),
            @Result(property = "qualification",column = "qualification"),
            @Result(property = "speciality",column = "specialty"),
            @Result(property = "university",column = "university"),
            @Result(property = "tel",column = "tel"),
            @Result(property = "experience",column = "experience"),
            @Result(property = "mailbox",column = "mailbox"),
            @Result(property = "introduction",column = "introduction"),
            @Result(property = "diploma",column = "diploma"),
            @Result(property = "headPath",column = "head_path"),
    })
    HashMap<String,Object> getCompanyCvInfo(@Param("companyCvId") Long companyCvId, @Param("userId") Long userId);


    @Select("SELECT\n" +
            "\tsys_company_cv.id,\n" +
            "\tsys_company.`name`,\n" +
            "\tsys_company.id As company_id,\n" +
            "\tsys_company_profession.title,\n" +
            "\tsys_company_profession.id AS profession_id,\n" +
            "\tsys_company_cv.STATUS,\n" +
            "\tsys_company_cv.last_edit_time \n" +
            "FROM\n" +
            "\tsys_company_cv\n" +
            "\tJOIN sys_company_profession ON " +
            "sys_company_cv.company_profession_id = sys_company_profession.id\n" +
            "\tJOIN sys_company ON sys_company_profession.company_id = sys_company.id \n" +
            "WHERE\n" +
            "\tsys_company_cv.user_id = #{userId} and" +
            " (sys_company.name REGEXP #{keyword} or sys_company_profession.title  REGEXP #{keyword})")
    @Results(id = "companyCvResultMap",value = {
            @Result(property = "resumeId",column = "id"),
            @Result(property = "companyId",column = "company_id"),
            @Result(property = "companyName",column = "name"),
            @Result(property = "title",column = "title"),
            @Result(property = "employId",column = "profession_id"),
            @Result(property = "status",column = "status"),
            @Result(property = "lastEditTime",column = "last_edit_time", javaType = String.class),
    })
    List<HashMap<String,Object>> getCompanyCvPage(Pagination pagination, @Param("userId") Long userId, @Param("keyword") String keyword);
}
