package com.example.worknet.common.persistence.template.modal;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 简历表
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-03
 */
@TableName("sys_learner_cv")
public class LearnerCv implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 简历id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 简历名称
     */
    @TableField("resume_name")
    private String resumeName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 出生年月
     */
    private Date birth;
    /**
     * 籍贯
     */
    @TableField("native_place")
    private String nativePlace;
    /**
     * 身份证号
     */
    private String identity;
    /**
     * 学历
     */
    private String qualification;
    /**
     * 专业
     */
    private String specialty;
    /**
     * 毕业院校
     */
    private String university;
    /**
     * 电话
     */
    private String tel;
    /**
     * 实习经历
     */
    private String experience;
    /**
     * 邮箱
     */
    private String mailbox;
    /**
     * 自我介绍
     */
    private String introduction;
    /**
     * 获奖情况
     */
    private String diploma;
    /**
     * 当前所在地
     */
    @TableField("current_location")
    private String currentLocation;
    /**
     * 最快入职时间
     */
    @TableField("in_job_time")
    private String inJobTime;
    /**
     * 证件照
     */
    @TableField("head_path")
    private String headPath;


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

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getInJobTime() {
        return inJobTime;
    }

    public void setInJobTime(String inJobTime) {
        this.inJobTime = inJobTime;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    @Override
    public String toString() {
        return "LearnerCv{" +
        ", id=" + id +
        ", userId=" + userId +
        ", resumeName=" + resumeName +
        ", name=" + name +
        ", sex=" + sex +
        ", birth=" + birth +
        ", nativePlace=" + nativePlace +
        ", identity=" + identity +
        ", qualification=" + qualification +
        ", specialty=" + specialty +
        ", university=" + university +
        ", tel=" + tel +
        ", experience=" + experience +
        ", mailbox=" + mailbox +
        ", introduction=" + introduction +
        ", diploma=" + diploma +
        ", currentLocation=" + currentLocation +
        ", inJobTime=" + inJobTime +
        ", headPath=" + headPath +
        "}";
    }
}
