package com.lhw.container.dependency.injection;

import com.lhw.container.annotations.MyInjectUser;
import com.lhw.container.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author ：linhw
 * @date ：22.6.5 23:19
 * @description：注解依赖注入
 * @modified By：
 */
public class AnnotationAutowiredDependencyInjection {

    @Autowired
    private User user;

    @MyInjectUser
    private User injectUser;

    /**
     * 通过自定义注解Bean前置处理器，实现和Autowired同样的效果，将Bean通过自定义注解的方式注入到类中
     *
     *      这里需要注意的是，要将其变为静态的方法，使其不依赖本配置类的加载顺序，让其能够优先加载生效，才能够全局生效
     *
     * @return
     */
    @Bean
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        Set<Class<? extends Annotation>> set = new LinkedHashSet<>(Arrays.asList(Autowired.class, MyInjectUser.class));
        beanPostProcessor.setAutowiredAnnotationTypes(set);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册本配置类
        applicationContext.register(AnnotationAutowiredDependencyInjection.class);

        //读取并加载xml配置文件的Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        applicationContext.refresh();

        AnnotationAutowiredDependencyInjection bean = applicationContext.getBean(AnnotationAutowiredDependencyInjection.class);

        System.out.println("autowired user : " + bean.user);
        System.out.println("myInjectUser user : " + bean.user);


        applicationContext.close();
    }

}
