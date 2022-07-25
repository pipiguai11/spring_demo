package com.lhw.springevent.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * @author ：linhw
 * @date ：22.7.25 13:19
 * @description：默认事件发布者
 * @modified By：
 */
@Component
public class DefaultEventPublisher implements ApplicationEventPublisherAware, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private ApplicationEventPublisher applicationEventPublisher;

    private static final Logger log = LoggerFactory.getLogger(DefaultEventPublisher.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publisherEvent(ApplicationEvent applicationEvent) {
        log.info("准备开始发布自定义事件");
        applicationEventPublisher.publishEvent(applicationEvent);
        log.info("发布自定义事件完毕");
    }

}
