package com.lhw.javaevent.handler;

import com.lhw.javaevent.event.DoorEvent;
import com.lhw.javaevent.listener.DoorListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：linhw
 * @date ：22.7.25 11:20
 * @description：默认事件监听处理者对象
 * @modified By：
 */
public class DefaultEventListenerHandler implements EventListerHandler {

    private final List<DoorListener> eventListeners = new ArrayList<>();

    @Override
    public void registerListener(DoorListener eventListener) {
        this.eventListeners.add(eventListener);
    }

    @Override
    public void removeListener(DoorListener eventListener) {
        this.eventListeners.remove(eventListener);
    }

    @Override
    public void handlerEvent(DoorEvent eventObject) {
        for (DoorListener eventListener : this.eventListeners) {
            eventListener.eventHandler(eventObject);
        }
    }
}
