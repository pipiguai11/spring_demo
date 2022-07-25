package com.lhw.springevent.listener.annotation;

import com.lhw.springevent.event.DefaultEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author ：linhw
 * @date ：22.7.25 13:27
 * @description：注解方式的事件监听器
 *
 *      只需要使用@EventListener注解，并将此对象注入到IOC容器即可生效
 *      会自动监听所有的DefaultEvent事件
 *
 *      这里需要注意的是，在spring中，事件机制是基于同步的，也就是说，
 *      回调onApplicationEvent方法的时候，会阻塞，直到所有的监听器都回调完成，才会结束
 *
 * @modified By：
 */
@Component
public class AnnotationEventListener {

    private static final Logger log = LoggerFactory.getLogger(AnnotationEventListener.class);

    @EventListener
    public void onDefaultEventPublisher(DefaultEvent defaultEvent) {
        log.info("注解实现的监听器，通过回调的方式收到了自定义的defaultEvent事件消息，准备处理业务逻辑");
    }

}
