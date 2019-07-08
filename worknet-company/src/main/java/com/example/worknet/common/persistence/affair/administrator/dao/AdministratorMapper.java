package com.example.worknet.common.persistence.affair.administrator.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.worknet.common.persistence.template.modal.Administrator;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Component
public interface AdministratorMapper extends BaseMapper<Administrator> {

    @Select({"SELECT\n" +
            "\tsys_user.id,\n" +
            "\tsys_user.account,\n" +
            "\tsys_user.`password`,\n" +
            "\tsys_user.activity \n" +
            "FROM\n" +
            "\tsys_user \n" +
            "WHERE\n" +
            "\tsys_user.role = #{role}\n" +
            "\tAND ( sys_user.id REGEXP #{keyword} OR sys_user.account LIKE CONCAT( '%', #{keyword}, '%' ) ) \n" +
            "ORDER BY\n" +
            "\tsys_user.id DESC"})
    @Results(id = "defaultCoursesInfoResultMap",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "activity",column = "activity"),
    })
    List<HashMap<String,Object>> getUserPage(Pagination pagination, @Param("role") Integer role, @Param("keyword") String keyword);

    @Select({"SELECT\n" +
            "\tsys_learner_info.id,\n" +
            "\tsys_learner_info.user_id,\n" +
            "\tsys_learner_info.nickname,\n" +
            "\tsys_learner_info.realname,\n" +
            "\tsys_learner_info.sex,\n" +
            "\tsys_learner_info.age,\n" +
            "\tsys_learner_info.signature,\n" +
            "\tsys_learner_info.vacation,\n" +
            "\tsys_learner_info.github,\n" +
            "\tsys_learner_info.email,\n" +
            "\tsys_learner_info.phone,\n" +
            "\tsys_learner_info.hobby,\n" +
            "\tsys_learner_info.professional \n" +
            "FROM\n" +
            "\tsys_learner_info \n" +
            "WHERE\n" +
            "\tsys_learner_info.id REGEXP #{keyword}\n" +
            "\tOR sys_learner_info.nickname REGEXP #{keyword}\n" +
            "\tOR sys_learner_info.realname REGEXP #{keyword}\n" +
            "\tOR sys_learner_info.phone REGEXP #{keyword}\n" +
            "ORDER BY\n" +
            "\tsys_learner_info.id DESC"})
    @Results(id = "defaultCoursesInfoResultMap",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "nickname",column = "nickname"),
            @Result(property = "realname",column = "realname"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "age",column = "age"),
            @Result(property = "signature",column = "signature"),
            @Result(property = "vacation",column = "vacation"),
            @Result(property = "github",column = "github"),
            @Result(property = "email",column = "email"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "hobby",column = "hobby"),
            @Result(property = "professional",column = "professional"),
    })
    List<HashMap<String,Object>> getLearnerInfoPage(Pagination pagination, @Param("keyword") String keyword);

    //未实现
    List<HashMap<String,Object>> getTeacherInfoPage(Pagination pagination, @Param("keyword") String keyword);

    @Select({"SELECT\n" +
            "\tsys_company.id,\n" +
            "\tsys_company.user_id,\n" +
            "\tsys_company.field,\n" +
            "\tsys_company.`name`,\n" +
            "\tsys_company.introduction,\n" +
            "\tsys_company.address,\n" +
            "\tsys_company.communication,\n" +
            "\tsys_company.website,\n" +
            "\tsys_company.register_time\n" +
            "FROM\n" +
            "\tsys_company \n" +
            "WHERE\n" +
            "\tsys_company.id REGEXP #{keyword}\n" +
            "\tOR sys_company.`name` REGEXP #{keyword}\n" +
            "ORDER BY\n" +
            "\tsys_company.id DESC"})
    @Results(id = "defaultCoursesInfoResultMap",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "field",column = "field"),
            @Result(property = "name",column = "name"),
            @Result(property = "introduction",column = "introduction"),
            @Result(property = "address",column = "address"),
            @Result(property = "communication",column = "communication"),
            @Result(property = "website",column = "website"),
            @Result(property = "registerTime",column = "register_time"),
    })
    List<HashMap<String,Object>> getCompanyInfoPage(Pagination pagination, @Param("keyword") String keyword);


    @Select({"SELECT\n" +
            "\tsys_company_cv.id,\n" +
            "\tsys_company_cv.company_profession_id,\n" +
            "\tsys_company_cv.user_id,\n" +
            "\tsys_learner_info.realname,\n" +
            "\tsys_company.`name`,\n" +
            "\tsys_company_profession.title,\n" +
            "\tsys_company_profession.company_id,\n" +
            "\tsys_company_cv.`status` \n" +
            "FROM\n" +
            "\tsys_company_cv\n" +
            "\tJOIN sys_learner_info ON sys_company_cv.user_id = sys_learner_info.user_id\n" +
            "\tJOIN sys_company_profession ON sys_company_cv.company_profession_id = sys_company_profession.id\n" +
            "\tJOIN sys_company ON sys_company_profession.company_id = sys_company.id \n" +
            "WHERE\n" +
            "\tsys_company_cv.user_id REGEXP #{keyword} \n" +
            "\tOR sys_company.id REGEXP #{keyword} \n" +
            "\tOR sys_learner_info.realname REGEXP #{keyword} \n" +
            "\tOR sys_company.`name` REGEXP #{keyword} \n" +
            "\tOR sys_company_profession.title REGEXP #{keyword} \n" +
            "ORDER BY\n" +
            "\tsys_company_cv.id DESC"})
    @Results(id = "defaultCoursesInfoResultMap",value = {
            @Result(property = "resumeId",column = "id"),
            @Result(property = "companyProfessionId",column = "company_profession_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "realname",column = "realname"),
            @Result(property = "companyName",column = "name"),
            @Result(property = "employTitle",column = "title"),
            @Result(property = "companyId",column = "company_id"),
            @Result(property = "status",column = "status"),
    })
    List<HashMap<String,Object>> getCompanyCvPage(Pagination pagination, @Param("keyword") String keyword);

    @Select({"SELECT\n" +
            "\tsys_company_invitation.company_profession_id,\n" +
            "\tsys_company_invitation.user_id,\n" +
            "\tsys_learner_info.realname,\n" +
            "\tsys_company.`name`,\n" +
            "\tsys_company_profession.title,\n" +
            "\tsys_company_invitation.company_id,\n" +
            "\tsys_company_invitation.`status`\n" +
            "FROM\n" +
            "\tsys_company_invitation\n" +
            "\tJOIN sys_learner_info ON sys_company_invitation.user_id = sys_learner_info.user_id\n" +
            "\tJOIN sys_company_profession ON sys_company_invitation.company_profession_id = sys_company_profession.id\n" +
            "\tJOIN sys_company ON sys_company_invitation.company_id = sys_company.id \n" +
            "WHERE\n" +
            "\tsys_company_invitation.user_id REGEXP #{keyword} \n" +
            "\tOR sys_company.id REGEXP #{keyword} \n" +
            "\tOR sys_learner_info.realname REGEXP #{keyword} \n" +
            "\tOR sys_company.`name` REGEXP #{keyword} \n" +
            "\tOR sys_company_profession.title REGEXP #{keyword} \n" +
            "ORDER BY\n" +
            "\tsys_company_invitation.id DESC"})
    @Results(id = "defaultCoursesInfoResultMap",value = {
            @Result(property = "companyProfessionId",column = "company_profession_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "realname",column = "realname"),
            @Result(property = "companyName",column = "name"),
            @Result(property = "employTitle",column = "title"),
            @Result(property = "companyId",column = "company_id"),
            @Result(property = "status",column = "status"),
    })
    List<HashMap<String,Object>> getCompanyInvitationPage(Pagination pagination, @Param("keyword") String keyword);
}
