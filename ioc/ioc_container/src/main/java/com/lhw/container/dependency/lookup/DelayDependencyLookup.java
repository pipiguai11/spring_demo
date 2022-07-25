package com.lhw.container.dependency.lookup;

import com.lhw.container.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author ：linhw
 * @date ：22.5.29 23:14
 * @description：延迟依赖查找
 *
 *      使用的是 ObjectFactory 或者  ObjectProvider对象
 *
 * @modified By：
 */
public class DelayDependencyLookup {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //这里给当前上下文注册一个user
        applicationContext.register(DelayDependencyLookup.class);
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);

        applicationContext.close();
    }

    @Bean
    public String hello() {
        return "Hello world";
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        //这时去获取Bean，如果发现不存在，则会抛出异常（ No qualifying bean of type 'com.lhw.container.domain.User' available）
//        System.out.println(beanProvider.getObject());
        //可以使用getIfAvailable进行一个预处理，当发现该Bean不存在时，则自己new一个
        System.out.println(beanProvider.getIfAvailable(User::new));
    }

    /**
     * 延迟加载，通过ObjectProvider对象获取Bean
     * @param applicationContext
     */
    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }

}
