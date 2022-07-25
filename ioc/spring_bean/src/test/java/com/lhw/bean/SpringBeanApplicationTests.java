package com.lhw.bean;

import com.lhw.bean.factory.UserFactoryBean;
import com.lhw.bean.utils.ApplicationContextUtil;
import com.lhw.container.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBeanApplicationTests {

    /**
     * springboot自动启动应用上下文之后通过ApplicationContext进行注册
     * @throws Exception
     */
    @Test
    void contextLoads() throws Exception {
        //将FactoryBean对象注册到容器中，并通过它获取对应的Bean实例
        ApplicationContextUtil.registerBean("userFactoryBean", UserFactoryBean.class);
        System.out.println(ApplicationContextUtil.getBean("userFactoryBean", User.class));
    }

}
