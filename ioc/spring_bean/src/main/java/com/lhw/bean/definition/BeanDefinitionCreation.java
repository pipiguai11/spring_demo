package com.lhw.bean.definition;

import com.lhw.container.domain.SuperUser;
import com.lhw.container.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author ：linhw
 * @date ：22.5.13 15:43
 * @description：通过BeanDefinition定义一个Bean
 * @modified By：
 */
public class BeanDefinitionCreation {

    /**
     * 通过Builder构建者模式的方式来创建Bean定义
     */
    private static void createByBuilder() {
        //创建一个根的BeanDefinition，它不允许有parent
//        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(User.class);

        //通过Builder对象拿到BeanDefinitionBuilder，genericBeanDefinition表示的是非根bean定义对象
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SuperUser.class);

        //添加属性
        beanDefinitionBuilder.addPropertyValue("id", 12);
        beanDefinitionBuilder.addPropertyValue("name", "lhw");

        //获取创建好的BeanDefinition对象，注意，这个对象并不是最终的状态，后面还可以继续修改属性状态
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //这里继续添加属性
        beanDefinition.getPropertyValues().add("address", "广州");
    }

    /**
     * 直接通过构造器的方式来创建
     *
     *      这里需要注意： 一般创建的都是GenericBeanDefinition
     *
     */
    private static void createByConstructor() {
        //创建一个根的bean定义对象，不允许有parent
//        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();

        //直接new一个GenericBeanDefinition对象，表示非根的bean定义对象
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();

        //设置bean定义绑定的Class类，也就是设置bean的类型
        beanDefinition.setBeanClass(SuperUser.class);

        //定义属性参数，通过MutablePropertyValues批量操作
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("id", 13);
        mutablePropertyValues.add("name", "lhw");
        mutablePropertyValues.add("address", "gz");
        beanDefinition.setPropertyValues(mutablePropertyValues);
    }

}
