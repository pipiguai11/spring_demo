package com.lhw.bean.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author ：linhw
 * @date ：22.5.15 10:55
 * @description：
 * @modified By：
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static String registerBean(String beanName, Class<?> beanType) {
        GenericApplicationContext context = (GenericApplicationContext) applicationContext;
        context.registerBean(beanName, beanType);
        return beanName;
    }

    public static <T> Object getBean(Class<T> beanType) {
        return applicationContext.getBean(beanType);
    }

    public static Object getBean(String beanName, Class<?> beanType) {
        return applicationContext.getBean(beanName, beanType);
    }
}
