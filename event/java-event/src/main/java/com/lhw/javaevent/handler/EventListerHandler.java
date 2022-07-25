package com.lhw.javaevent.handler;

import com.lhw.javaevent.event.DoorEvent;
import com.lhw.javaevent.listener.DoorListener;

/**
 * @author ：linhw
 * @date ：22.7.25 11:18
 * @description：监听事件处理者
 * @modified By：
 */
public interface EventListerHandler {

    void registerListener(DoorListener eventListener);

    void removeListener(DoorListener eventListener);

    void handlerEvent(DoorEvent eventObject);

}
