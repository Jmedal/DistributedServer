package com.example.worknet.common.persistence.affair.user.serivce;

import com.baomidou.mybatisplus.service.IService;
import com.example.worknet.common.persistence.template.modal.LearnerCv;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 简历表 服务类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
public interface LearnerCvService extends IService<LearnerCv> {

    LearnerCv getLearnerCvInfo(Long userId);

    LearnerCv getLearnerCvInfo(Long cvId, Long userId);

    Resource getLearnerCvAvatar(Long cvId, String strDirPath);

    Long createLearnerCv(LearnerCv learnerCv, Long userId);

    List<HashMap<String,Object>> getLearnerCvList(Long userId);

    boolean deleteLearnerCv(Long cvId,Long userId);
}
