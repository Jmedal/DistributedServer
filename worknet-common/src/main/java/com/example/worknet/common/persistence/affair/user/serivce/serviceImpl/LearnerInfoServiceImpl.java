package com.example.worknet.common.persistence.affair.user.serivce.serviceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.worknet.common.persistence.affair.user.serivce.LearnerInfoService;
import com.example.worknet.common.persistence.affair.user.dao.LearnerInfoMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.persistence.template.modal.LearnerInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学习者信息表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Service
public class LearnerInfoServiceImpl extends ServiceImpl<LearnerInfoMapper, LearnerInfo> implements LearnerInfoService {

    /**
     * 获取学习者默认简历id
     * @param userId
     * @return
     */
    @Override
    public Long getLearnerDefaultCvId(Long userId) {
        List<LearnerInfo> learnerInfos = super.selectList(new EntityWrapper<LearnerInfo>().eq("user_id",userId));
        if(learnerInfos!=null && learnerInfos.size() > 0 ){
            return learnerInfos.get(0).getLearnerCvId();
        }
        return null;
    }

    /**
     * 根据用户id获取学习者信息
     * @param userId
     * @return
     */
    @Override
    public LearnerInfo getLearnerInfoByUserId(Long userId) {
        List<LearnerInfo> learnerInfos = super.selectList(new EntityWrapper<LearnerInfo>().eq("user_id",userId));
        if(learnerInfos!=null && learnerInfos.size() > 0 ){
            return learnerInfos.get(0);
        }
        return null;
    }

}
