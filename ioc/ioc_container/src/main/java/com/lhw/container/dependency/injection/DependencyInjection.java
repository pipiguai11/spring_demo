package com.lhw.container.dependency.injection;

import com.lhw.container.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author ：linhw
 * @date ：22.5.11 11:30
 * @description：依赖注入
 *
 *      看完下面两个例子，问题就来了，那么到底BeanFactory和ApplicationContext谁才是IOC容器呢？
 *      其实说起来，两个都算
 *      ApplicationContext是BeanFactory的子接口，对BeanFactory做了很多的扩展，如下：
 *          • 面向切面（AOP）
 *          • 配置元信息（Configuration Metadata）
 *          • 资源管理（Resources）
 *          • 事件（Events）
 *          • 国际化（i18n）
 *          • 注解（Annotations）
 *          • Environment 抽象（Environment
 *
 * @modified By：
 */
public class DependencyInjection {

    public static void main(String[] args) {

        useBeanFactory();
        useApplicationContext();

    }

    /**
     * 先是使用BeanFactory作为IOC，从其中拿到我们想要的Bean对象
     *
     *      我们可以拿到自定义Bean
     *      也可以从对象中拿到依赖注入的bean
     *      当然也可以拿到容器内建的bean（如Environment、ApplicationContext等）
     *
     */
    private static void useBeanFactory() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");

        //自定义的依赖注入
        System.out.println("User集合对象：" + userRepository.getUsers());

        //依赖注入（内建依赖）（我本身没有定义它的，同样的还有Environment、ApplicationContext等）
        System.out.println("BeanFactory对象：" + userRepository.getBeanFactory());
        //依赖注入的BeanFactory和当前上下文中new的BeanFactory并不是同一个
        System.out.println("判断当前的BeanFactory和依赖注入的BeanFactory是否是同一个上下文：" + (beanFactory == userRepository.getBeanFactory()));

        //自定义的一个ObjectFactory对象，依赖注入
        System.out.println("ObjectFactory对象：" + userRepository.getUserObjectFactory());
        System.out.println("从ObjectFactory工厂中获取到的User对象：" + userRepository.getUserObjectFactory().getObject());

        System.out.println();
    }

    /**
     * 这里采用ApplicationContext作为IOC
     *
     *      同样可以获取到我们的自定义Bean和依赖注入的Bean以及所有容器内建的Bean
     *
     */
    private static void useApplicationContext() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);

        //这里判断到内建依赖注入的ApplicationContext和当前上下文中ApplicationContext是同一个，和上面的BeanFactory不同
        System.out.println(userRepository.getApplicationContextObjectFactory().getObject() == applicationContext);

        //获取容器内建的Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean：" + environment);
        System.out.println(environment == applicationContext.getEnvironment());
    }

}
