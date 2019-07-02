package com.example.worknet.common.persistence.template.modal;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 职业推荐课程表
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-06-04
 */
@TableName("sys_course_recommend")
public class CourseRecommend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 推荐id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 职业id
     */
    @TableField("profession_id")
    private Long professionId;
    /**
     * 科目分类id
     */
    @TableField("curriculum_id")
    private Long curriculumId;


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

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }

    @Override
    public String toString() {
        return "CourseRecommend{" +
        ", id=" + id +
        ", professionId=" + professionId +
        ", curriculumId=" + curriculumId +
        "}";
    }
}
