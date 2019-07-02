package com.example.worknet.common.persistence.affair.api.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.worknet.common.persistence.affair.api.user.modal.User;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author YunJieJiang123
 * @since 2019-04-27
 */
@Component
public interface UserMapper extends BaseMapper<User> {
}
