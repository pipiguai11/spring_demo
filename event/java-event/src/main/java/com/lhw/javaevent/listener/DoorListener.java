package com.lhw.javaevent.listener;

import com.lhw.javaevent.event.DoorEvent;

import java.util.EventListener;

/**
 * @author ：linhw
 * @date ：22.7.25 11:09
 * @description：开关门事件监听
 * @modified By：
 */
public interface DoorListener extends EventListener {

    /**
     * 事件处理
     * @param doorEvent
     */
    void eventHandler(DoorEvent doorEvent);

}
