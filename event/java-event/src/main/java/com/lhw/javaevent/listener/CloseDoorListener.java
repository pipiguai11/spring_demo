package com.lhw.javaevent.listener;

import com.lhw.javaevent.enums.DoorStatusEnum;
import com.lhw.javaevent.event.DoorEvent;

/**
 * @author ：linhw
 * @date ：22.7.25 11:14
 * @description：关门事件
 * @modified By：
 */
public class CloseDoorListener implements DoorListener{
    @Override
    public void eventHandler(DoorEvent doorEvent) {
        if (doorEvent.getEventStatus() == DoorStatusEnum.CLOSE) {
            System.out.println("the door is close");
        }
    }
}
