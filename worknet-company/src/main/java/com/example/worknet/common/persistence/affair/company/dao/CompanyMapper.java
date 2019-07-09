package com.example.worknet.common.persistence.affair.company.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
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





    @Select("SELECT\n" +
            "\tsys_company_cv.id AS resumeId,\n" +
            "\tsys_company.id AS companyId,\n" +
            "\tsys_company_profession.id AS companyProfessionId,\n" +
            "\tsys_company_cv.user_id AS studentId,\n" +
            "\tsys_company_profession.title AS employTitle,\n" +
            "\tsys_company_cv.`name` AS realname,\n" +
            "\tsys_company_cv.`status`\n" +
            "\t\n" +
            "FROM\n" +
            "\tsys_company\n" +
            "\tJOIN sys_company_profession ON sys_company.id = sys_company_profession.company_id\n" +
            "\tJOIN sys_company_cv ON sys_company_profession.id = sys_company_cv.company_profession_id \n" +
            "WHERE\n" +
            "\tsys_company.id = #{companyId}\n" +
            "\tAND ( sys_company_cv.user_id  REGEXP #{searchText} OR sys_company_cv.NAME REGEXP #{searchText}")
    @Results(id = "ResumeListResultMap",value = {
            @Result(property = "resumeId", column = "resumeId"),
            @Result(property = "companyId", column = "companyId"),
            @Result(property = "companyProfessionId", column = "companyProfessionId"),
            @Result(property = "studentId", column = "studentId"),
            @Result(property = "realname", column = "realname"),
            @Result(property = "employTitle", column = "employTitle"),
            @Result(property = "status", column = "status"),
    }
    )

    public List<HashMap<String,Object>> getResumeList(Pagination pagination,
                                                    @Param("companyId") String companyId,
                                                    @Param("searchText") String searchText);

    @Select("SELECT\n" +
            "\tsys_company_profession.id AS companyProfessionId,\n" +
            "\tsys_company_profession.title AS employTitle\n" +
            "FROM\n" +
            "\tsys_company\n" +
            "\tJOIN sys_company_profession ON sys_company.id = sys_company_profession.company_id\n" +
            "\tJOIN sys_user ON sys_company.user_id = sys_user.id\n" +
            "WHERE\n" +
            "\tsys_user.id= #{userId}\n")
    @Results(id = "ProfessionListResultMap",value = {
            @Result(property = "companyProfessionId", column = "companyProfessionId"),
            @Result(property = "title", column = "employTitle"),
    }
    )

    public List<HashMap<String,Object>> getProfessionList(@Param("userId") Long userId);


    @Select("SELECT\n" +
            "\tsys_company_profession.id,\n" +
            "\tsys_company_profession.company_id,\n" +
            "\tsys_company_profession.profession_type_id,\n" +
            "\tsys_company_profession.state,\n" +
            "\tsys_profession_type.type_name,\n" +
            "\tsys_company_profession.location,\n" +
            "\tsys_company_profession.title,\n" +
            "\tsys_company_profession.salary,\n" +
            "\tsys_company_profession.introduction,\n" +
            "\tsys_company_profession.requirement,\n" +
            "\tsys_company_profession.is_practice,\n" +
            "\tsys_company_profession.duration,\n" +
            "\tsys_company_profession.chance_to_formal\n" +
            "FROM\n" +
            "\tsys_company\n" +
            "\tJOIN sys_company_profession ON sys_company.id = sys_company_profession.company_id\n" +
            "\tJOIN sys_user ON sys_company.user_id = sys_user.id \n" +
            "\tJOIN sys_profession_type ON sys_profession_type.id = sys_company_profession.profession_type_id " +
            "WHERE\n" +
            "\tsys_user.id = #{userId} \n" +
            "\tAND ( sys_company_profession.title REGEXP #{searchText} \n" +
            "\tOR sys_company_profession.introduction REGEXP #{searchText} \n" +
            "\tOR sys_company_profession.requirement REGEXP #{searchText} )")
    @Results(id = "EmployListResultMap",value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "professionTypeId", column = "profession_type_id"),
            @Result(property = "state", column = "state"),
            @Result(property = "professionType", column = "type_name"),
            @Result(property = "location", column = "location"),
            @Result(property = "title", column = "title"),
            @Result(property = "salary", column = "salary"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "requirement", column = "requirement"),
            @Result(property = "isPractice", column = "is_practice"),
            @Result(property = "duration", column = "duration"),
            @Result(property = "chanceToFormal", column = "chance_to_formal"),
    }
    )

    public List<HashMap<String,Object>> getEmployList(Pagination pagination,
                                                      @Param("userId") String userId,
                                                      @Param("searchText") String searchText);
}
