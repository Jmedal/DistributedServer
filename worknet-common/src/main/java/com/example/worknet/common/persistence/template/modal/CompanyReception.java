package com.example.worknet.common.persistence.template.modal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 岗位简历记录表
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@TableName("sys_company_reception")
public class CompanyReception implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 投递id
     */
    private Long id;
    /**
     * 岗位id
     */
    @TableField("profession_id")
    private Long professionId;
    /**
     * 简历id
     */
    @TableField("cv_id")
    private Long cvId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public Long getCvId() {
        return cvId;
    }

    public void setCvId(Long cvId) {
        this.cvId = cvId;
    }

    @Override
    public String toString() {
        return "CompanyReception{" +
        ", id=" + id +
        ", professionId=" + professionId +
        ", cvId=" + cvId +
        "}";
    }
}
