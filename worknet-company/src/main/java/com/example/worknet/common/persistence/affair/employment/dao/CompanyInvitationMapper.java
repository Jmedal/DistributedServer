package com.example.worknet.common.persistence.affair.employment.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.worknet.common.persistence.template.modal.CompanyInvitation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 面试邀约表 Mapper 接口
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Component
public interface CompanyInvitationMapper extends BaseMapper<CompanyInvitation> {

    @Select({"SELECT\n" +
            "\tsys_company_invitation.id,\n" +
            "\tsys_company_invitation.company_id,\n" +
            "\tsys_company.`name`,\n" +
            "\tsys_company_invitation.company_profession_id,\n" +
            "\tsys_company_profession.title,\n" +
            "\tsys_company_invitation.`status`,\n" +
            "\tsys_company_invitation.invite_time \n" +
            "FROM\n" +
            "\tsys_company_invitation\n" +
            "\tJOIN sys_company ON sys_company_invitation.company_id = sys_company.id\n" +
            "\tJOIN sys_company_profession ON sys_company_invitation.company_profession_id = sys_company_profession.id \n" +
            "WHERE\n" +
            "\tsys_company_invitation.user_id = #{id} \n" +
            "\tAND sys_company_profession.title LIKE CONCAT( '%', #{keyword}, '%' ) \n" +
            "ORDER BY\n" +
            "\tsys_company_invitation.invite_time DESC"})
    @Results(id = "defaultCoursesInfoResultMap",value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyName", column = "name"),
            @Result(property = "employId", column = "company_profession_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "status", column = "status"),
            @Result(property = "inviteTime", column = "invite_time", javaType = String.class),
    })
    List<HashMap<String,Object>> getCompanyInvitationPage(Pagination pagination, @Param("id") Long id, @Param("keyword") String keyword);

}
