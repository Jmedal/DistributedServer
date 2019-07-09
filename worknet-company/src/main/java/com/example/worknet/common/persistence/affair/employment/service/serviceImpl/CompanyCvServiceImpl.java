package com.example.worknet.common.persistence.affair.employment.service.serviceImpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.common.persistence.affair.employment.dao.CompanyCvMapper;
import com.example.worknet.common.persistence.affair.employment.service.CompanyCvService;
import com.example.worknet.common.persistence.template.modal.CompanyCv;
import com.example.worknet.common.persistence.template.modal.CompanyInvitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.OverridesAttribute;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 * 简历表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-07-02
 */
@Service
public class CompanyCvServiceImpl extends ServiceImpl<CompanyCvMapper, CompanyCv> implements CompanyCvService {


    /**
     * 新增简历
     */
    @Override
    public long deliverResume(Long companyProfessionId,
                              Long userId,
                              String name,
                              Integer sex,
                              Date birth,
                              String nativePlace,
                              String identity,
                              String qualification,
                              String specialty,
                              String university,
                              String tel,
                              String experience,
                              String mailbox,
                              String introduction,
                              String diploma,
                              String currentLocation,
                              String inJobTime){
        CompanyCv companyCv=new CompanyCv();

        companyCv.setCompanyProfessionId(companyProfessionId);
        companyCv.setUserId(userId);
        companyCv.setName(name);
        companyCv.setSex(sex);
        companyCv.setBirth(birth);
        companyCv.setNativePlace(nativePlace);
        companyCv.setIdentity(identity);
        companyCv.setQualification(qualification);
        companyCv.setSpeciality(specialty);
        companyCv.setUniversity(university);
        companyCv.setTel(tel);
        companyCv.setExperience(experience);
        companyCv.setMailbox(mailbox);
        companyCv.setIntroduction(introduction);
        companyCv.setDiploma(diploma);
        companyCv.setCurrentLocation(currentLocation);
        companyCv.setInJobTime(inJobTime);
        companyCv.setStatus(0);
        companyCvMapper.deliverResume(companyCv);
        return companyCv.getId();
    }
    /**
     * 获取简历信息
     * @param resumeId
     * @return
     */
    @Override
    public HashMap<String, Object> getCvInfo(Long resumeId) {
        return companyCvMapper.getCvInfo(resumeId);
    }


    /**
     * 获取用户投放的简历
     */
    @Override
    public Page<HashMap<String,Object>> getMyResumePage(Page<HashMap<String, Object>> pager,
                                                        String userId,String searchText){
        if(userId == null || userId.equals("null") || userId.equals(""))
            userId = "[digit]*";
        if(searchText == null || searchText.equals("null") || searchText.equals(""))
            searchText = "[\\w]*";
        else
            searchText = searchText.trim().replaceAll("\\s+"," ").replace(" ","|");
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        return page.setRecords(companyCvMapper.getMyResume(page,userId,searchText));

    }

    @Override
    public boolean changeResumeStatus(Long resumeId, int status,Long userId) {
        CompanyCv companyCv=super.selectById(resumeId);
        if(companyCv != null && companyCv.getUserId().equals(userId)
                && companyCv.getStatus().equals(0)) {
            companyCv.setStatus(status);
            return super.updateAllColumnById(companyCv);
        }
        return false;
    }

    @Autowired
    CompanyCvMapper companyCvMapper;
}
