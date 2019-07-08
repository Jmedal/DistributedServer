package com.example.worknet.common.persistence.affair.administrator.service.serviceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.constant.UserConst;
import com.example.worknet.common.persistence.affair.administrator.dao.AdministratorMapper;
import com.example.worknet.common.persistence.affair.administrator.service.AdministratorService;
import com.example.worknet.common.persistence.template.modal.LearnerInfo;
import com.example.worknet.common.persistence.template.modal.User;
import com.example.worknet.common.persistence.affair.api.user.UserService;
import com.example.worknet.common.persistence.template.modal.Administrator;
import com.example.worknet.common.persistence.template.modal.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {

    /**
     * 验证管理员帐号密码
     * @param account
     * @param password
     * @return
     */
    @Override
    public boolean verify(String account,String password){
        List<Administrator> administratorList = super.selectList(new EntityWrapper<Administrator>().eq("account",account));
        if(administratorList.size() != 0 &&
                administratorList.get(0).getPassword().equals(password))
            return true;
        return false;
    }

    /**
     * 添加公司帐号
     * @param account
     * @param password
     * @param role
     * @return
     */
    @Override
    public boolean userRegister(String account, String password, UserConst role) {
        if(!userService.checkAccount(account)){
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setActivity(1);
            user.setRole(role.getState());
            if(userService.userRegister(user))
                return true;
            else
                return false;
        }else
            return false;
    }

    /**
     * 更新帐号密码
     * @param userId
     * @param password
     * @return
     */
    @Override
    public boolean changeUserPassword(Long userId, String password) {
        User user = userService.selectById(userId);
        user.setPassword(password);
        return userService.updateUserInfo(user);
    }

    /**
     * 改变帐号状态
     * @param userId
     * @param activity
     * @return
     */
    @Override
    public boolean changeUserActivity(Long userId, Integer activity) {
            User user = userService.selectById(userId);
            user.setActivity(activity);
            return userService.updateUserInfo(user);
    }

    /**
     * 获取帐号列表
     * @param pager
     * @param role
     * @param keyword
     * @return
     */
    @Override
    public Page<HashMap<String, Object>> getUserPage(Page<HashMap<String, Object>> pager, UserConst role, String keyword) {
        if(keyword == null || keyword.equals(""))
            keyword = "[digit]*";
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        return page.setRecords(administratorMapper.getUserPage(page, role.getState(), keyword));
    }

    /**
     * 获取帐号信息列表
     * @param pager
     * @param role
     * @param keyword
     * @return
     */
    @Override
    public Page<HashMap<String, Object>> getUserInfoPage(Page<HashMap<String, Object>> pager, UserConst role, String keyword) {
        if(keyword == null || keyword.equals("null") || keyword.equals(""))
            keyword = "[digit]*";
        else
            keyword = keyword.trim().replaceAll("\\s+","|");
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        switch (role){
            case COMPANY:
                return page.setRecords(administratorMapper.getCompanyInfoPage(page, keyword));
            case TEACHER:
                return page.setRecords(administratorMapper.getTeacherInfoPage(page, keyword));
            case STUDENT:
                return page.setRecords(administratorMapper.getLearnerInfoPage(page, keyword));
            default:
                return null;
        }
    }

    /**
     * 更新学习者信息
     * @param learnerInfo
     * @return
     */
    @Override
    public boolean changeLearnerInfo(LearnerInfo learnerInfo) {
        return userService.updateLearnerInfo(learnerInfo);
    }

    /**
     * 更新公司信息
     * @param company
     * @return
     */
    @Override
    public boolean changeCompanyInfo(Company company) {
        return userService.updateCompanyInfo(company);
    }

    /**
     * 获取学生投递简历列表
     * @param pager
     * @param keyword
     * @return
     */
    @Override
    public Page<HashMap<String, Object>> getCompanyCvPage(Page<HashMap<String, Object>> pager, String keyword) {
        if(keyword == null || keyword.equals("null") || keyword.equals(""))
            keyword = "[digit]*";
        else
            keyword = keyword.trim().replaceAll("\\s+","|");
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        return page.setRecords(administratorMapper.getCompanyCvPage(page, keyword));
    }

    /**
     * 获取公司招聘邀请列表
     * @param pager
     * @param keyword
     * @return
     */
    @Override
    public Page<HashMap<String, Object>> getCompanyInvitationPage(Page<HashMap<String, Object>> pager, String keyword) {
        if(keyword == null || keyword.equals("null") || keyword.equals(""))
            keyword = "[digit]*";
        else
            keyword = keyword.trim().replaceAll("\\s+","|");
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        return page.setRecords(administratorMapper.getCompanyInvitationPage(page, keyword));
    }

    @Autowired
    private UserService userService;

    @Autowired
    private AdministratorMapper administratorMapper;
}
