package com.lhw.container.dependency.lookup;

import com.lhw.container.domain.SuperUser;
import com.lhw.container.domain.User;
import com.lhw.container.annotations.Super;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author ：linhw
 * @date ：22.5.11 11:30
 * @description：依赖查找
 * @modified By：
 */
public class DependencyLookup {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        lookupRealTimeByName(beanFactory);
        lookupRealTimeByType(beanFactory);
        lookupInLazy(beanFactory);
        lookupCollectionByType(beanFactory);
        lookupCollectionByAnnotation(beanFactory);
    }

    private static void lookupRealTimeByName(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时根据bean名称查找： " + user);
    }

    private static void lookupRealTimeByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(SuperUser.class);
        System.out.println("实时根据bean类型查找： " + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟从ObjectFactory中获取到Bean对象：" + user);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if ( beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String,User> map = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("根据类型查询所有的Bean对象：" + map);
        }
    }

    private static void lookupCollectionByAnnotation(BeanFactory beanFactory) {
        if ( beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> map = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查询所有带有 @Super 注解的Bean对象： " + map);
        }
    }

}
