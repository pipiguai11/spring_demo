package com.lhw.bean.interfaces.impl;

import com.lhw.bean.interfaces.UserTestInterface;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author ：linhw
 * @date ：22.5.15 11:43
 * @description：测试实现类1
 * @modified By：
 */
public class UserTestImpl1 implements UserTestInterface, InitializingBean {
    @Override
    public String getMessage() {
        return "测试实现类1";
    }

    /**
     * 只有在IOC容器中创建的对象才会调用到这个方法
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserTestImpl1调用了InitializingBean#afterPropertiesSet 进行Bean的初始化");
    }
}
