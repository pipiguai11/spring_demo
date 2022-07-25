package com.lhw.springevent.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.concurrent.ExecutorService;

/**
 * @author ：linhw
 * @date ：22.7.25 14:12
 * @description：应用上下文事件多播器配置
 *
 *      注意：如果这里配置了，那么就是全局生效的
 *
 * @modified By：
 */
//@Configuration
public class ApplicationEventMulticasterConfig {

    /**
     * 自定义一个上下文事件多播器，然后覆盖掉原先的那个，
     * 设置同一个名字（APPLICATION_EVENT_MULTICASTER_BEAN_NAME）即可
     *
     *      如果这里覆盖了原先的事件多播器，那么就是全局的，所有的事件都会通过这个线程池去回调的，不再是原先的同步方式
     *
     * @param executorService
     * @return
     */
    @Bean(name = AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
    public ApplicationEventMulticaster applicationEventMulticaster(@Qualifier("default_thread_pool") ExecutorService executorService) {
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
        simpleApplicationEventMulticaster.setTaskExecutor(executorService);
        return simpleApplicationEventMulticaster;
    }

}
