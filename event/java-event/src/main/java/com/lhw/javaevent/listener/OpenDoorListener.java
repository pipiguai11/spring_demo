package com.lhw.javaevent.listener;

import com.lhw.javaevent.enums.DoorStatusEnum;
import com.lhw.javaevent.event.DoorEvent;

/**
 * @author ：linhw
 * @date ：22.7.25 11:10
 * @description：开门事件
 * @modified By：
 */
public class OpenDoorListener implements DoorListener {
    @Override
    public void eventHandler(DoorEvent doorEvent) {
        if (doorEvent.getEventStatus() == DoorStatusEnum.OPEN) {
            System.out.println("the door is open");
        }
    }
}
