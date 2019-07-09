package com.example.worknet.common.persistence.affair.companyContest.service.serviceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.worknet.common.persistence.template.modal.CompanyContestApply;
import com.example.worknet.common.persistence.affair.companyContest.dao.CompanyContestApplyMapper;
import com.example.worknet.common.persistence.affair.companyContest.service.CompanyContestApplyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.worknet.core.utils.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 公司笔试报名表 服务实现类
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-05-25
 */
@Service
@Transactional
public class CompanyContestApplyServiceImpl extends ServiceImpl<CompanyContestApplyMapper, CompanyContestApply> implements CompanyContestApplyService {

    /**
     * 获取用户笔试报名记录
     * @param pager
     * @param uid
     * @return
     */
    @Override
    public Page<HashMap<String, Object>> getUserContestPage(Page<HashMap<String, Object>> pager, Long uid) {
        Page<HashMap<String, Object>> page = new Page<>(pager.getCurrent(),pager.getSize());
        return page.setRecords(companyContestApplyMapper.getUserCompanyContestPage(page, uid));
    }

    /**
     * 添加笔试报名记录
     * @param uid
     * @param eid
     * @return
     */
    @Override
    public boolean insertContestApply(Long uid, Long eid) {
        List<CompanyContestApply> list = super.selectList(new EntityWrapper<CompanyContestApply>().eq("user_id",uid).eq("contest_id",eid));
        if(list == null || list.size() < 1){
            CompanyContestApply companyContestApply = new CompanyContestApply();
            companyContestApply.setUserId(uid);
            companyContestApply.setPermission(1);
            companyContestApply.setSignUpTime(DateUtil.getSqlNowDateTime());
            companyContestApply.setContestId(eid);
            return super.insert(companyContestApply);
        }
        return false;
    }

    @Autowired
    private CompanyContestApplyMapper companyContestApplyMapper;
}
