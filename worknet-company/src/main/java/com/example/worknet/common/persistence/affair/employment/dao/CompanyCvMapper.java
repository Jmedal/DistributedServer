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


    @Insert("INSERT INTO sys_company_cv (\n" +
            "\tcompany_profession_id,\n" +
            "\tuser_id,\n" +
            "\t`name`,\n" +
            "\tsex,\n" +
            "\tbirth,\n" +
            "\tnative_place,\n" +
            "\tidentity,\n" +
            "\tqualification,\n" +
            "\tspecialty,\n" +
            "\tuniversity,\n" +
            "\ttel,\n" +
            "\texperience,\n" +
            "\tmailbox,\n" +
            "\tintroduction,\n" +
            "\tdiploma,\n" +
            "\tcurrent_location,\n" +
            "\tin_job_time,\n" +
            "\t`status`,\n" +
            "\tlast_edit_time \n" +
            ")\n" +
            "VALUES\n" +
            "\t(\n" +
            "\t\t#{companyProfessionId},\n" +
            "\t\t#{userId},\n" +
            "\t\t#{name},\n" +
            "\t\t#{sex},\n" +
            "\t\t#{birth},\n" +
            "\t\t#{nativePlace},\n" +
            "\t\t#{identity},\n" +
            "\t\t#{qualification},\n" +
            "\t\t#{speciality},\n" +
            "\t\t#{university},\n" +
            "\t\t#{tel},\n" +
            "\t\t#{experience},\n" +
            "\t\t#{mailbox},\n" +
            "\t\t#{introduction},\n" +
            "\t\t#{diploma},\n" +
            "\t\t#{currentLocation},\n" +
            "\t\t#{inJobTime},\n" +
            "\t\t#{status},\n" +
            "\tSYSDATE() \n" +
            "\t)")
    @Options(useGeneratedKeys = true, keyProperty = "id")

    long deliverResume(CompanyCv companyCv);


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
            "id=#{resumeId}")
    @Results(id = "cvInfoResultMap",value = {
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
    HashMap<String,Object> getCvInfo(@Param("resumeId") Long resumeId);


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
            "\tsys_company_cv.user_id REGEXP #{userId} and" +
            " (sys_company.name REGEXP #{searchText} or sys_company_profession.title  REGEXP #{searchText})")
    @Results(id = "MyResumeResultMap",value = {
            @Result(property = "resumeId",column = "id"),
            @Result(property = "companyId",column = "company_id"),
            @Result(property = "companyName",column = "name"),
            @Result(property = "title",column = "title"),
            @Result(property = "employId",column = "profession_id"),
            @Result(property = "status",column = "status"),
            @Result(property = "lastEditTime",column = "last_edit_time"),
    })
    public List<HashMap<String,Object>> getMyResume(Pagination pagination,
                                                    @Param("userId") String userId,
                                                    @Param("searchText") String searchText);
}
