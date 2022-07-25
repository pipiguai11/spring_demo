package com.lhw.springevent.listener.api;

import com.lhw.springevent.event.DefaultEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author ：linhw
 * @date ：22.7.25 14:37
 * @description：通过API的方式实现的监听器排序
 *
 *      * 默认值为Integer.MAX_VALUE
 *      *
 *      * 数值越小，优先级越高
 *
 * @modified By：
 */
@Component
public class ApiSortEventListener implements ApplicationListener<DefaultEvent>, Ordered {

    private static final Logger log = LoggerFactory.getLogger(ApiSortEventListener.class);

    @Override
    public void onApplicationEvent(DefaultEvent event) {
        log.info("api方式实现的排序监听器，优先级参数为110");
    }

    @Override
    public int getOrder() {
        return 110;
    }
}
