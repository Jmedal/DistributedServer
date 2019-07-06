package com.example.worknet.common.persistence.template.modal;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 面试邀约表
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-07
 */
@TableName("sys_company_invitation")
public class CompanyInvitation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邀约id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 公司id
     */
    @TableField("company_id")
    private Long companyId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 招聘id
     */
    @TableField("company_profession_id")
    private Long companyProfessionId;
    /**
     * 邀约状态
     */
    private Integer status;
    /**
     * 邀请时间
     */
    @TableField("invite_time")
    private Date inviteTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompanyProfessionId() {
        return companyProfessionId;
    }

    public void setCompanyProfessionId(Long companyProfessionId) {
        this.companyProfessionId = companyProfessionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getInviteTime() {
        return inviteTime;
    }

    public void setInviteTime(Date inviteTime) {
        this.inviteTime = inviteTime;
    }

    @Override
    public String toString() {
        return "CompanyInvitation{" +
        ", id=" + id +
        ", companyId=" + companyId +
        ", userId=" + userId +
        ", companyProfessionId=" + companyProfessionId +
        ", status=" + status +
        ", inviteTime=" + inviteTime +
        "}";
    }
}
