package com.example.worknet.config.hessian;

import com.example.worknet.common.constant.ServiceURL;
import com.example.worknet.common.persistence.affair.api.user.UserService;
import com.example.worknet.config.hessian.utils.HessianProxyFactoryUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: YunJieJiang
 * @Date: Created in 20:27 2019/6/28 0028
 */
@Configuration
public class HessianClientBeanConfig {

    @Bean
    public UserService userService() {
        try {
            return HessianProxyFactoryUtil.getHessianClientBean(UserService.class, ServiceURL.UserService);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
