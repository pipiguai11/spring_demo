package com.lhw.springevent.listener.annotation;

import com.lhw.springevent.event.DefaultEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @author ：linhw
 * @date ：22.7.25 14:03
 * @description：异步方式的事件监听器
 *
 *      通过注解指定某个监听器通过异步的方式去回调，不用阻塞主线程。
 *
 *      注意：这里说的异步指的是整个回调方法的异步，不是方法内部的异步
 *          即如下asyncOnDefaultEventPublisher方法，或者ApplicationListener的onApplicationEvent方法
 *
 * @modified By：
 */
@Component
@EnableAsync
public class AsyncAnnotationEventListener {

    private static final Logger log = LoggerFactory.getLogger(AsyncAnnotationEventListener.class);

    @Async("default_thread_pool")
    @EventListener
    public void asyncOnDefaultEventPublisher(DefaultEvent defaultEvent) {
        log.info("异步的方式，通过注解接收到一个事件");
    }

}
