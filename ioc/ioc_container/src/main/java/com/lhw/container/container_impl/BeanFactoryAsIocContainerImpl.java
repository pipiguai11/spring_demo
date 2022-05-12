package com.lhw.container.container_impl;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;


/**
 * @author ：linhw
 * @date ：22.5.12 15:11
 * @description：BeanFactory作为IOC容器
 * @modified By：
 */
public class BeanFactoryAsIocContainerImpl {

    public static void main(String[] args) {
        //1、创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2、创建BeanDefinition读取器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        //3、配置XML配置文件
        String local = "classpath:/META-INF/dependency-injection-context.xml";

        //4、加载配置文件(将配置文件中配置好的Bean加载到BeanFactory中)
        int beanNum = reader.loadBeanDefinitions(local);
        System.out.println("总共加载了【" + beanNum + "】个Bean");

        //5、查看IOC容器中是否正常加载了配置文件中的Bean
        lookupCollectionAll(beanFactory);
    }

    private static void lookupCollectionAll(BeanFactory beanFactory) {
        if (beanFactory instanceof  ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            for (String beanDefinitionName : listableBeanFactory.getBeanDefinitionNames()) {
                System.out.println(beanDefinitionName);
            }
        }
    }

}
