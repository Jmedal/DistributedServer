package com.example.worknet.common.persistence.affair.api.user.serivce.serviceImpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.persistence.affair.api.user.dao.UserMapper;
import com.example.worknet.common.persistence.affair.api.user.modal.LearnerInfo;
import com.example.worknet.common.persistence.affair.api.user.modal.TeacherInfo;
import com.example.worknet.common.persistence.affair.api.user.modal.User;
import com.example.worknet.common.persistence.affair.api.user.serivce.UserService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.transaction.Transactional;
import java.util.HashMap;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean verify(String account, String password) {
        return false;
    }

    @Override
    public boolean checkAccount(String account) {
        return false;
    }

    @Override
    public HashMap<String, Object> getUserInfo(User user) {
        return null;
    }

    @Override
    public String getNickname(Long id) {
        return null;
    }

    @Override
    public boolean userRegister(User user) {
        return false;
    }

    @Override
    public boolean insertTeacherInfo(TeacherInfo teacherInfo) {
        return false;
    }

    @Override
    public boolean insertLearnerInfo(LearnerInfo learnerInfo) {
        return false;
    }

    @Override
    public boolean updateUserInfo(User user) {
        return false;
    }

    @Override
    public boolean updateTeacherInfo(TeacherInfo teacherInfo) {
        return false;
    }

    @Override
    public boolean updateLearnerInfo(LearnerInfo learnerInfo) {
        return false;
    }

    @Override
    public Resource getAvatar(long userId, String strDirPath) {
        return null;
    }

    @Override
    public boolean updateAvatar(long userId, MultipartHttpServletRequest request) {
        return false;
    }

    @Override
    public Page<HashMap<String, Object>> getUserStudiedPage(Page<HashMap<String, Object>> page, long uid) {
        return null;
    }

    @Override
    public Page<HashMap<String, Object>> getUserContestPage(Page<HashMap<String, Object>> page, Long uid) {
        return null;
    }
}
