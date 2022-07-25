package com.lhw.springevent.listener.api;

import com.lhw.springevent.event.DefaultEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author ：linhw
 * @date ：22.7.25 13:30
 * @description：API的方式实现事件监听器
 *
 *      只需要实现了ApplicationListener接口，并将其注入到IOC容器即可生效
 *      会自动监听所有的DefaultEvent事件
 *
 *      这里需要注意的是，在spring中，事件机制是基于同步的，也就是说，
 *      回调onApplicationEvent方法的时候，会阻塞，直到所有的监听器都回调完成，才会结束
 *
 * @modified By：
 */
@Component
public class ApiEventListener implements ApplicationListener<DefaultEvent> {

    private static final Logger log = LoggerFactory.getLogger(ApiEventListener.class);

    @Override
    public void onApplicationEvent(DefaultEvent event) {
        log.info("api实现的监听器，通过回调的方式接收了一个自定义事件");
    }
}
