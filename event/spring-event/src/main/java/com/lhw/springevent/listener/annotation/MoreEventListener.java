package com.lhw.springevent.listener.annotation;

import com.lhw.springevent.event.DefaultEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author ：linhw
 * @date ：22.7.25 14:25
 * @description：多事件监听器
 * @modified By：
 */
@Component
public class MoreEventListener {

    private static final Logger log = LoggerFactory.getLogger(MoreEventListener.class);

    @EventListener(classes = {DefaultEvent.class, ContextRefreshedEvent.class, ContextClosedEvent.class})
    public void moreEventPublisher(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof DefaultEvent) {
            log.info("监听到defaultEvent类型事件");
        }else if (applicationEvent instanceof ContextRefreshedEvent) {
            log.info("监听到ContextRefreshedEvent类型事件");
        }else if (applicationEvent instanceof ContextClosedEvent) {
            log.info("监听到ContextClosedEvent类型事件");
        }
    }

}
