package com.example.worknet.common.persistence.template.modal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 企业发布岗位表
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@TableName("sys_company_profession")
public class CompanyProfession implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 招聘id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 公司id
     */
    @TableField("company_id")
    private Long companyId;
    /**
     * 职业id
     */
    @TableField("profession_id")
    private Long professionId;
    /**
     * 标题
     */
    private String title;
    /**
     * 职位描述
     */
    private String introduction;
    /**
     * 职位要求
     */
    private String requirement;
    /**
     * 薪水
     */
    private String salary;
    /**
     * 招聘开始时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 招聘结束时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 招聘状态
     */
    private Integer state;
    /**
     * 工作地
     */
    private String location;


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

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CompanyProfession{" +
        ", id=" + id +
        ", companyId=" + companyId +
        ", professionId=" + professionId +
        ", title=" + title +
        ", introduction=" + introduction +
        ", requirement=" + requirement +
        ", salary=" + salary +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", state=" + state +
        ", location=" + location +
        "}";
    }
}
