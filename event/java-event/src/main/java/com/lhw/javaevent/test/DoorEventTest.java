package com.lhw.javaevent.test;

import com.lhw.javaevent.enums.DoorStatusEnum;
import com.lhw.javaevent.event.DoorEvent;
import com.lhw.javaevent.handler.DefaultEventListenerHandler;
import com.lhw.javaevent.handler.EventListerHandler;
import com.lhw.javaevent.listener.CloseDoorListener;
import com.lhw.javaevent.listener.OpenDoorListener;

/**
 * @author ：linhw
 * @date ：22.7.25 11:15
 * @description：开关门事件测试
 * @modified By：
 */
public class DoorEventTest {

    public static void main(String[] args) {
        EventListerHandler eventListerHandler = init();
        DoorEvent doorEvent = new DoorEvent("open");
        doorEvent.setEventStatus(DoorStatusEnum.OPEN);
        eventListerHandler.handlerEvent(doorEvent);

        doorEvent.setEventStatus(DoorStatusEnum.CLOSE);
        eventListerHandler.handlerEvent(doorEvent);
    }

    private static EventListerHandler init() {
        DefaultEventListenerHandler eventListenerHandler = new DefaultEventListenerHandler();
        eventListenerHandler.registerListener(new OpenDoorListener());
        eventListenerHandler.registerListener(new CloseDoorListener());
        return eventListenerHandler;
    }

}
