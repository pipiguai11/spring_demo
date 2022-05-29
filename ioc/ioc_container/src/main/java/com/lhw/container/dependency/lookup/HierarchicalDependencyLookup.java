package com.lhw.container.dependency.lookup;

import com.lhw.container.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author ：linhw
 * @date ：22.5.26 17:11
 * @description：层次性依赖查找
 *
 *      类似类的双亲委派，层层的往上找
 *
 *      不过这个要自己设置上下文的父级上下文
 *
 * @modified By：
 */
public class HierarchicalDependencyLookup {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //这里给当前上下文注册一个user
        applicationContext.register(HierarchicalDependencyLookup.class);
        applicationContext.refresh();

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        checkParentBeanFactory(beanFactory);

        //这个localUser是在当前上下文中注册的，而在父级上下文中没有
        checkBeanInBeanFactory(beanFactory, "localUser");
        //这个user是在父级上下文中注册的，在当前上下文中没有
        checkBeanInBeanFactory(beanFactory, "user");

        //这里通过层级依赖查找（也就是类似双亲委派），可以找到Bean
        System.out.println("层级依赖查找判断【localUser】：" + checkBeanInBeanFactoryHierarchical(beanFactory, "localUser"));
        System.out.println("层级依赖查找判断【user】：" + checkBeanInBeanFactoryHierarchical(beanFactory, "user"));

        applicationContext.close();
    }

    /**
     * 在本配置类中注册的Bean
     * @return
     */
    @Bean("localUser")
    public User user() {
        User user = new User();
        user.setId(333);
        user.setName("lhw333");
        return user;
    }

    /**
     * 层级判断当前上下文及其父级上下文是否包含某个Bean（根据BeanName）
     *      只要在其中一个上下文中找到就返回
     * @param beanFactory
     * @param beanName
     */
    private static boolean checkBeanInBeanFactoryHierarchical(HierarchicalBeanFactory beanFactory, String beanName) {
        //先往上委派
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (checkBeanInBeanFactoryHierarchical(hierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        //如果父级没找到就找自己
        return beanFactory.containsLocalBean(beanName);
    }

    /**
     * 检查当前上下文中是否包含指定的Bean（通过beanName）
     * @param beanFactory
     * @param beanName
     */
    private static void checkBeanInBeanFactory(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.println("当前上下文是否包含指定的Bean【" + beanName + "】：" + beanFactory.containsLocalBean(beanName));
        System.out.println("当前上下文的父级上下文是否包含指定的Bean【" + beanName + "】：" +
                ((HierarchicalBeanFactory)beanFactory.getParentBeanFactory()).containsLocalBean(beanName));
        System.out.println();
    }

    /**
     * 检查当前上下文的父级上下文
     * @param beanFactory
     */
    private static void checkParentBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        //因为还没有设置，所以这里的父级上下文获取到的是null
        System.out.println("当前上下文的父级上下文对象：" + beanFactory.getParentBeanFactory());

        //这里先设置了父级上下文之后再去获取就可以正常拿到了
        HierarchicalBeanFactory parentFactory = createParentFactory();
        beanFactory.setParentBeanFactory(parentFactory);
        System.out.println("当前上下文的父级上下文对象：" + beanFactory.getParentBeanFactory());
        System.out.println();
    }

    /**
     * 读取项目classpath的xml配置文件，并将其中的bean注入到此BeanFactory上下文中
     * @return
     */
    private static HierarchicalBeanFactory createParentFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        return beanFactory;
    }

}
