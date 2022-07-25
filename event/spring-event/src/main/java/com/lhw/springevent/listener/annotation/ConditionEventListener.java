package com.lhw.springevent.listener.annotation;

import com.lhw.springevent.event.DefaultEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author ：linhw
 * @date ：22.7.25 14:46
 * @description：条件判断事件监听器
 *
 *      当条件满足是，才会触发调用这个监听器
 *
 * @modified By：
 */
@Component
public class ConditionEventListener {

    private static final Logger log = LoggerFactory.getLogger(ConditionEventListener.class);

    /**
     * 通过事件的属性进行条件判断，当flag为true时，才会调用到这个监听器的方法
     * @param applicationEvent
     */
//    @EventListener(classes = {DefaultEvent.class}, condition = "#root.args[0].flag")
    @EventListener(classes = {DefaultEvent.class}, condition = "#root.event.flag")
    public void conditionEventPublisher(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof DefaultEvent){
            log.info("条件满足，触发此事件监听器");
        }
    }

}
