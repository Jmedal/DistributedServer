package com.example.worknet.common.persistence.affair.company.serivce.serviceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.constant.UserConst;
import com.example.worknet.common.persistence.affair.company.dao.CompanyMapper;
import com.example.worknet.common.persistence.affair.company.serivce.CompanyService;
import com.example.worknet.common.persistence.affair.employment.service.CompanyCvService;
import com.example.worknet.common.persistence.affair.employment.service.CompanyInvitationService;
import com.example.worknet.common.persistence.affair.employment.service.CompanyProfessionService;
import com.example.worknet.common.persistence.template.modal.Company;
import com.example.worknet.common.persistence.template.modal.CompanyCv;
import com.example.worknet.common.persistence.template.modal.CompanyInvitation;
import com.example.worknet.common.persistence.template.modal.CompanyProfession;
import com.example.worknet.core.utils.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 公司信息表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-06
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    /**
     * 获取公司信息
     * @param companyId
     * @return
     */
    @Override
    public HashMap<String,Object> getCompanyInfo(Long companyId){
        return companyMapper.getCompanyInfo(companyId);
    }

    /**
     * 通过用户id，获取公司信息
     * @param userId
     * @return
     */
    @Override
    public HashMap<String, Object> getCompanyInfoByUserId(Long userId) {
        Long companyId = super.selectOne(new EntityWrapper<Company>().eq("user_id",userId)).getId();
        return getCompanyInfo(companyId);
    }

    /**
     * 获取公司简历信息列表
     * @param pager
     * @param companyId
     * @param keyword
     * @return
     */
    @Override
    public Page<HashMap<String,Object>> getResumePage(Page<HashMap<String, Object>> pager, String companyId, String keyword){
        if(companyId == null || companyId.equals("null") || companyId.equals(""))
            companyId = "[digit]*";
        if(keyword == null || keyword.equals("null") || keyword.equals(""))
            keyword = "[\\w]*";
        else
            keyword = keyword.trim().replaceAll("\\s+"," ").replace(" ","|");
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        return page.setRecords(companyMapper.getResumePage(page, companyId, keyword));
    }

    /**
     * 更新简历状态
     * @param resumeId
     * @param status
     * @param userId
     * @return
     */
    @Override
    public boolean changeResumeStatus(Long resumeId, Integer status, Long userId) {
        Long companyId = super.selectOne(new EntityWrapper<Company>().eq("user_id",userId)).getId();
        CompanyCv companyCv = companyCvService.selectById(resumeId);
        if(companyCv != null) {
            Long companyIdCP = companyProfessionService.selectById(companyCv.getCompanyProfessionId()).getCompanyId();
            if(companyIdCP.equals(companyId) && companyCv.getStatus().equals(0)){
                companyCv.setStatus(status);
                companyCv.setLastEditTime(DateUtil.getSqlNowDateTime());
                return companyCvService.updateById(companyCv);
            }
        }
        return false;
    }

    /**
     * 更新公司帐号信息
     * @param company
     * @return
     */
    @Override
    public boolean updateCompanyInfo(Company company) {
        if(company.getUserId() == null)
            return false;
        Company c = super.selectOne(new EntityWrapper<Company>().eq("user_id", company.getUserId()));
        if(c != null){
            company.setId(c.getId());
            return super.updateById(company);
        }
        return false;
    }

    /**
     * 获取招聘简略信息列表
     * @param userId
     * @return
     */
    @Override
    public List<HashMap<String, Object>> getComEmployList(Long userId) {
        return companyMapper.getProfessionList(userId);
    }

    /**
     * 获取公司招聘信息列表
     * @param pager
     * @param userId
     * @param keyword
     * @return
     */
    @Override
    public Page<HashMap<String, Object>> getCompanyEmployPage(Page<HashMap<String, Object>> pager, String userId, String keyword) {
        if(keyword == null || keyword.equals("null") || keyword.equals(""))
            keyword = "[\\w]*";
        else
            keyword = keyword.trim().replaceAll("\\s+"," ").replace(" ","|");
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        return page.setRecords(companyMapper.getEmployList(page, userId, keyword));
    }

    /**
     * 更新招聘状态为 [ 结束 = 0 ]
     * @param companyProfessionId
     * @return
     */
    @Override
    public boolean endEmploy(Long companyProfessionId) {
        CompanyProfession companyProfession = companyProfessionService.selectById(companyProfessionId);
        if(companyProfession != null){
            companyProfession.setState(0);
            return companyProfessionService.updateById(companyProfession);
        }
        return false;
    }

    /**
     * 更新招聘信息
     * @param companyProfession
     * @param userId
     * @return
     */
    @Override
    public boolean changeEmployInfo(CompanyProfession companyProfession, Long userId) {
        Long companyId = super.selectOne(new EntityWrapper<Company>().eq("user_id",userId)).getId();
        if(companyProfession.getCompanyId().equals(companyId))
            return companyProfessionService.updateById(companyProfession);
        return false;
    }

    /**
     * 添加公司招聘
     * @param companyProfession
     * @param userId
     * @return
     */
    @Override
    public boolean createEmployInfo(CompanyProfession companyProfession, Long userId) {
        Long companyId = super.selectOne(new EntityWrapper<Company>().eq("user_id",userId)).getId();
        if(companyId != null) {
            companyProfession.setId(userId);
            companyProfession.setCompanyId(companyId);
            return  companyProfessionService.insert(companyProfession);
        }
        return false;
    }

    /**
     * 获取邀请招聘列表
     * @param pager
     * @param userId
     * @param keyword
     * @return
     */
    @Override
    public Page<HashMap<String, Object>> getWelcomePage(Page<HashMap<String, Object>> pager, Long userId, String keyword) {
        Long companyId = super.selectOne(new EntityWrapper<Company>().eq("user_id",userId)).getId();
        if(keyword == null || keyword.equals("null") || keyword.equals(""))
            keyword = "[\\w]*";
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        return page.setRecords(companyMapper.getWelcomePage(page, companyId, keyword));
    }

    /**
     *  添加公司招聘邀请
     * @param userId
     * @param studentId
     * @param companyProfessionId
     * @return
     */
    @Override
    public boolean welcomeStudent(Long userId, Long studentId, Long companyProfessionId){
        Long companyId = super.selectOne(new EntityWrapper<Company>().eq("user_id",userId)).getId();
        CompanyInvitation companyInvitation=new CompanyInvitation();
        companyInvitation.setCompanyId(companyId);
        companyInvitation.setUserId(studentId);
        companyInvitation.setCompanyProfessionId(companyProfessionId);
        companyInvitation.setStatus(0);
        companyInvitation.setInviteTime(DateUtil.getSqlNowDateTime());
        return companyInvitationService.insert(companyInvitation);
    }

    /**
     * 获取学习者信息列表
     * @param pager
     * @param keyword
     * @return
     */
    @Override
    public Page<HashMap<String, Object>> getLearnerInfoPage(Page<HashMap<String, Object>> pager, String keyword) {
        if(keyword == null || keyword.equals("null") || keyword.equals(""))
            keyword = "[digit]*";
        else
            keyword = keyword.trim().replaceAll("\\s+","|");
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(), pager.getSize());
        return page.setRecords(companyMapper.getLearnerInfoPage(page, keyword));
    }

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyProfessionService companyProfessionService;

    @Autowired
    private CompanyCvService companyCvService;

    @Autowired
    private CompanyInvitationService companyInvitationService;
}
