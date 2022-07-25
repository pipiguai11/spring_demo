package com.lhw.bean.definition;

import com.lhw.container.domain.SuperUser;
import com.lhw.container.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author ：linhw
 * @date ：22.5.14 23:54
 * @description：使用不同的方式定义Bean
 * @modified By：
 */
@Import(AnnotationBeanDefinitionRegister.Config.class)
public class AnnotationBeanDefinitionRegister {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前类为配置类
        applicationContext.register(AnnotationBeanDefinitionRegister.class);

        //启动应用上下文
        applicationContext.refresh();

        //因为Config类同时使用了@Import和@Component的方式注册，这里输出用于查看是否会创建两个不同的Config，答案是不会，只有一个Config
        System.out.println("Config 类型的所有Bean ： " + applicationContext.getBeansOfType(Config.class));
        //把Config类中注册的User输出出来
        System.out.println("User 类型的所有Bean ： " + applicationContext.getBeansOfType(User.class));

        applicationContext.close();

    }


    //使用Component注册和上面的@Import是同样的效果，这里是重复注册了
    @Component
    public static class Config {
        /**
         * 通过@Bean注解将这个对象注入到IOC容器中
         * @return
         */
        @Bean("testUserBean")
        public SuperUser user() {
            SuperUser user = new SuperUser();
            user.setId(1);
            user.setName("lhw");
            return user;
        }
    }

}
