package com.lhw.bean.definition;

import com.lhw.bean.factory.UserFactoryBean;
import com.lhw.container.domain.User;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author ：linhw
 * @date ：22.5.15 10:48
 * @description：Bean实例化
 * @modified By：
 */
public class BeanInstantiation {

    /**
     * 手动启动应用上下文进行注册
     * @param args
     */
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean( "userFactory",UserFactoryBean.class);

        applicationContext.refresh();

        User user = applicationContext.getBean("userFactory", User.class);
        System.out.println(user);

        applicationContext.close();
    }

}
