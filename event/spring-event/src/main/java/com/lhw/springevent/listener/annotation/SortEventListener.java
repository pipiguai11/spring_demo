package com.lhw.springevent.listener.annotation;

import com.lhw.springevent.event.DefaultEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ：linhw
 * @date ：22.7.25 14:33
 * @description：事件监听器排序
 *
 *      应用在有强制要求的监听顺序的场景
 *
 * @modified By：
 */
@Component
public class SortEventListener {

    private static final Logger log = LoggerFactory.getLogger(SortEventListener.class);

    /**
     * 通过@Order注解设置监听器的顺序
     * 默认值为Integer.MAX_VALUE
     *
     * 数值越小，优先级越高
     *
     * @param applicationEvent
     */
    @Order(value = 100)
    @EventListener
    public void sortEventPublisher(DefaultEvent applicationEvent) {
        log.info("注解实现的监听器，这个监听器的顺序一定的最早的，优先级参数为100");
    }

}
