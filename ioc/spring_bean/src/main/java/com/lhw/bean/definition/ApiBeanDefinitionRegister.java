package com.lhw.bean.definition;

import com.lhw.container.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

/**
 * @author ：linhw
 * @date ：22.5.15 00:17
 * @description：API方式注册BeanDefinition
 * @modified By：
 */
public class ApiBeanDefinitionRegister {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前类为配置类
        applicationContext.register(ApiBeanDefinitionRegister.class);

        //启动应用上下文
        applicationContext.refresh();

        registerBeanDefinition(applicationContext, "myTestUser", User.class);
        registerBeanDefinition(applicationContext, User.class);

        //把Config类中注册的User输出出来（User 类型的所有Bean ： {myTestUser=User(id=12, name=lhw), com.lhw.container.domain.User#0=User(id=12, name=lhw)}）
        System.out.println("User 类型的所有Bean ： " + applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }

    /**
     * 向IOC容器注册Bean
     *      不提供beanName，使用容器自动生成的名称
     * @param registry
     * @param beanType
     */
    private static void registerBeanDefinition(BeanDefinitionRegistry registry, Class<?> beanType) {
        registerBeanDefinition(registry, null, beanType);
    }

    /**
     * 向IOC容器中注册Bean
     * @param registry
     * @param beanName
     * @param beanType
     */
    private static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanType) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanType);
        builder.addPropertyValue("id", 12)
                .addPropertyValue("name", "lhw");

        if (StringUtils.hasText(beanName)) {
            //以自定义的名称去命名Bean
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            //非自定义，容器自动生成BeanName
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }

}
