package com.example.worknet.common.persistence.affair.user.serivce.serviceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.worknet.common.constant.Const;
import com.example.worknet.common.persistence.affair.user.dao.LearnerCvMapper;
import com.example.worknet.common.persistence.affair.user.serivce.LearnerCvService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.persistence.affair.user.serivce.LearnerInfoService;
import com.example.worknet.common.persistence.template.modal.LearnerCv;
import com.example.worknet.common.persistence.template.modal.LearnerInfo;
import com.example.worknet.common.persistence.template.modal.User;
import com.example.worknet.core.utils.file.FileToolsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 简历表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Service
@Transactional
public class LearnerCvServiceImpl extends ServiceImpl<LearnerCvMapper, LearnerCv> implements LearnerCvService {

    /**
     * 获取默认的简历模板信息
     * @param userId
     * @return
     */
    @Override
    public LearnerCv getLearnerCvInfo(Long userId) {
        Long learnerDefaultCvId = learnerInfoService.getLearnerDefaultCvId(userId);
        if(learnerDefaultCvId!=null)
            return super.selectById(learnerDefaultCvId);
        return null;
    }

    /**
     * 获取简历模板信息
     * @param cvId
     * @param userId
     * @return
     */
    @Override
    public LearnerCv getLearnerCvInfo(Long cvId, Long userId) {
        LearnerCv learnerCv = super.selectById(cvId);
        if(learnerCv.getLearnerId().equals(userId))
            return learnerCv;
        return null;
    }

    /**
     * 获取简历模板头像
     * @param cvId
     * @param strDirPath
     * @return
     */
    @Override
    public Resource getLearnerCvAvatar(Long cvId, String strDirPath) {
        LearnerCv learnerCv = super.selectById(cvId);
        if(learnerCv==null)
            throw new RuntimeException();
        //绝对保存路径
        String filePath = Const.FILE_PATH + learnerCv.getHeadPath();
        strDirPath = strDirPath + "WEB-INF" + Const.FILE_SEPARATOR + "classes" + Const.FILE_SEPARATOR + "static" + Const.FILE_SEPARATOR + "upload";
        FileToolsUtil.fileToUpload(strDirPath, filePath);
        return resourceLoader.getResource("file:" + strDirPath + Const.FILE_SEPARATOR + filePath.substring(filePath.lastIndexOf(Const.FILE_SEPARATOR) + 1));
    }

    /**
     * 添加简历模版信息
     * @param learnerCv
     * @param userId
     * @return
     */
    @Override
    public Long createLearnerCv(LearnerCv learnerCv, Long userId) {
        if (super.selectList(new EntityWrapper<LearnerCv>().eq("user_id",userId)).size() < 5){
            if(super.insert(learnerCv)){
                LearnerInfo learnerInfo = learnerInfoService.getLearnerInfoByUserId(userId);
                if(learnerInfo.getLearnerCvId().equals((long)0) || learnerInfo.getLearnerCvId() == null){
                    learnerInfo.setLearnerCvId(learnerCv.getId());
                    return learnerCv.getId();
                }
            }
        }
        return null;
    }

    /**
     * 获取简历模版列表
     * @param userId
     * @return
     */
    @Override
    public List<HashMap<String, Object>> getLearnerCvList(Long userId) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        List<LearnerCv> learnerCvs = super.selectList(new EntityWrapper<LearnerCv>().eq("user_id",userId));
        Long defaultCvId = learnerInfoService.getLearnerDefaultCvId(userId);
        for (LearnerCv learnerCv : learnerCvs){
            HashMap<String, Object> map = new HashMap<>();
            map.put("resumeId", learnerCv.getId());
            map.put("resumeName", learnerCv.getResumeName());
            map.put("lastEditTime", learnerCv.getLastEditTime());
            map.put("isFavor",learnerCv.getId().equals(defaultCvId));
            int count = 0;
            for (Field f : learnerCv.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    if (f.get(learnerCv) != null || !f.get(learnerCv).toString().equals("")) {
                        count++;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            map.put("resumePercent",String.format("%.2f", (Double) (count-3/15.0)));
            list.add(map);
        }
        return list;
    }

    /**
     * 删除简历模版
     * @param cvId
     * @param userId
     * @return
     */
    @Override
    public boolean deleteLearnerCv(Long cvId, Long userId) {
        LearnerCv learnerCv = super.selectById(cvId);
        if (learnerCv.getLearnerId().equals(userId))
            return super.deleteById(cvId);
        return false;
    }

    @Autowired
    private LearnerInfoService learnerInfoService;

    @Autowired
    private final ResourceLoader resourceLoader;

    @Autowired
    public LearnerCvServiceImpl(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }
}