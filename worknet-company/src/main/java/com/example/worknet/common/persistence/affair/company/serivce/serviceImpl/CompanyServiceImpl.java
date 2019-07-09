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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
@Transactional
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    /**
     * 获取公司信息
     * @param companyId
     * @return
     */
    @Override
    public HashMap<String,Object> getCompanyInfo(long companyId){
        return companyMapper.getCompanyInfo(companyId);
    }

    @Override
    public Page<HashMap<String,Object>> getResumeList(Page<HashMap<String, Object>> page, String companyId, String searchText){
        if(companyId == null || companyId.equals("null") || companyId.equals(""))
            companyId = "[digit]*";
        if(searchText == null || searchText.equals("null") || searchText.equals(""))
            searchText = "[\\w]*";
        else
            searchText = searchText.trim().replaceAll("\\s+"," ").replace(" ","|");
        return page.setRecords(companyMapper.getResumeList(page,companyId,searchText));
    }

    @Override
    public boolean updateComInfo(Company company, Long userId) {
        if(userId==null)
            return false;
        Company temp=selectById(company.getId());
        if (temp.getUserId()==userId) {
            super.updateById(company);
            return true;
        }else
            return false;
    }

    @Override
    public List<HashMap<String, Object>> getComEmployList(long userId) {
        return companyMapper.getProfessionList(userId);
    }

    @Override
    public Page<HashMap<String, Object>> getEmployList(Page<HashMap<String, Object>> pager, String companyId, String searchText) {
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        if(searchText == null || searchText.equals("null") || searchText.equals(""))
            searchText = "[\\w]*";
        else
            searchText = searchText.trim().replaceAll("\\s+"," ").replace(" ","|");
        return page.setRecords(companyMapper.getEmployList(page,companyId,searchText));
    }

    @Override
    public boolean endEmploy(long id) {
        CompanyProfession companyProfession=companyProfessionService.selectById(id);
        if(companyProfession!=null){
            companyProfession.setState(0);
            return true;
        }else
            return false;
    }

    @Override
    public boolean changeEmployInfo(CompanyProfession companyProfession, long userId) {
        long comId = super.selectOne(new EntityWrapper<Company>().eq("user_id",userId)).getId();
        if(comId==companyProfession.getCompanyId()){
            companyProfessionService.updateById(companyProfession);
            return true;
        }else
            return false;
    }

    @Override
    public boolean createEmployInfo(CompanyProfession companyProfession, long userId) {
        long comId = super.selectOne(new EntityWrapper<Company>().eq("user_id",userId)).getId();
        if(comId==companyProfession.getCompanyId()) {
            companyProfession.setCompanyId(comId);
            companyProfessionService.insert(companyProfession);
            return true;
        }else
            return false;
    }


    @Override
    public Page<HashMap<String, Object>> getWelcomePage(Page<HashMap<String, Object>> pager, long userId, String searchText) {
        long companyId = super.selectOne(new EntityWrapper<Company>().eq("user_id",userId)).getId();
        if(searchText == null || searchText.equals("null") || searchText.equals(""))
            searchText = "[\\w]*";
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        return page.setRecords(companyMapper.getWelcomePage(page,companyId,searchText));

    }

    @Override
    public boolean changeResumeStatus(Long resumeId, int status,Long userId) {
        CompanyCv companyCv=companyCvService.selectById(resumeId);
        if(companyCv != null && companyCv.getUserId().equals(userId)
                && companyCv.getStatus().equals(0)) {
            companyCv.setStatus(status);
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
            Date date =new Date();
            String time=formatter.format(date);
            ParsePosition pos=new ParsePosition(0);
            Date d=formatter.parse(time,pos);
            companyCv.setLastEditTime(d);
            return companyCvService.updateAllColumnById(companyCv);
        }
        return false;
    }

    public boolean welcomeStudent(long userId, long studentId, long companyProfessionId){
        long companyId = super.selectOne(new EntityWrapper<Company>().eq("user_id",userId)).getId();
        CompanyInvitation companyInvitation=new CompanyInvitation();
        companyInvitation.setCompanyId(companyId);
        companyInvitation.setUserId(studentId);
        companyInvitation.setCompanyProfessionId(companyProfessionId);
        companyInvitation.setStatus(0);
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date date =new Date();
        String time=formatter.format(date);
        ParsePosition pos=new ParsePosition(0);
        Date d=formatter.parse(time,pos);
        companyInvitation.setInviteTime(d);
        companyInvitationService.insert(companyInvitation);
        return true;
    }

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
