package com.example.worknet.common.persistence.template.modal;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 特殊账号注册审核记录表
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@TableName("sys_admin_check_company")
public class AdminCheckCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 特殊账号注册审核记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 申请帐号id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 审核管理员id
     */
    @TableField("administrator_id")
    private Long administratorId;
    /**
     * 申请时间
     */
    @TableField("apply_time")
    private Date applyTime;
    /**
     * 审核时间
     */
    @TableField("check_time")
    private Date checkTime;
    /**
     * 当前审核状态
     */
    private Integer status;
    /**
     * 审核结果
     */
    private Integer result;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Long administratorId) {
        this.administratorId = administratorId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AdminCheckCompany{" +
        ", id=" + id +
        ", userId=" + userId +
        ", administratorId=" + administratorId +
        ", applyTime=" + applyTime +
        ", checkTime=" + checkTime +
        ", status=" + status +
        ", result=" + result +
        "}";
    }
}
