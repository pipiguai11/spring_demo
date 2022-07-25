package com.lhw.bean.factory;

import com.lhw.container.domain.SuperUser;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author ：linhw
 * @date ：22.5.15 10:44
 * @description：FactoryBean自定义对象的实例化
 * @modified By：
 */
public class UserFactoryBean implements FactoryBean {

    /**
     * 这里直接返回SuperUser的实例
     * @return
     * @throws Exception
     */
    @Override
    public Object getObject() throws Exception {
        SuperUser user = new SuperUser();
        user.setId(14);
        user.setName("lhw14");
        user.setAddress("gz14");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return SuperUser.class;
    }
}
