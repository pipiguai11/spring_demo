package com.lhw.springevent.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author ：linhw
 * @date ：22.7.25 13:18
 * @description：默认测试事件
 * @modified By：
 */
public class DefaultEvent extends ApplicationEvent {

    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public DefaultEvent(Object source) {
        super(source);
        this.flag = false;
    }
}
