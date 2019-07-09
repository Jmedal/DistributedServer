package com.example.worknet.common.persistence.affair.user.serivce;

import com.baomidou.mybatisplus.service.IService;
import com.example.worknet.common.persistence.template.modal.LearnerInfo;

/**
 * <p>
 * 学习者信息表 服务类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
public interface LearnerInfoService extends IService<LearnerInfo> {

    Long getLearnerDefaultCvId(Long userId);

    boolean updateDefaultCvId(Long userId, Long cvId);

    LearnerInfo getLearnerInfoByUserId(Long userId);
}
