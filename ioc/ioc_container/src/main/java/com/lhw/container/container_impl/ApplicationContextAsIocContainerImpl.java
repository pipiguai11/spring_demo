package com.lhw.container.container_impl;

import com.lhw.container.domain.SuperUser;
import com.lhw.container.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：linhw
 * @date ：22.5.12 15:24
 * @description：将ApplicationContext作为IOC容器
 * @modified By：
 */
@Configuration
public class ApplicationContextAsIocContainerImpl {

    public static void main(String[] args) {
        //1、生成应用上下文，就和BeanFactory一样，先创建容器
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        //2、指定配置类（这里将当前类作为配置类注册到上下文中，这个类配置了@Configuration注解）
        annotationConfigApplicationContext.register(ApplicationContextAsIocContainerImpl.class);

        //3、启动应用上下文（这一步是必须的）
        annotationConfigApplicationContext.refresh();

        //4、查看容器中注册的所有bean
        lookupCollectionAll(annotationConfigApplicationContext);

        //关闭应用上下文
        annotationConfigApplicationContext.close();

    }

    /**
     * 通过@Bean注解将这个对象注入到IOC容器中
     * @return
     */
    @Bean("testUserBean")
    public User user() {
        User user = new SuperUser();
        user.setId(1);
        user.setName("lhw");
        return user;
    }

    private static void lookupCollectionAll(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            for (String beanDefinitionName : listableBeanFactory.getBeanDefinitionNames()) {
                System.out.println(beanDefinitionName);
            }
        }
    }

}
