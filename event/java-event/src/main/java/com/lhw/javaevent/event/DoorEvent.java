package com.lhw.javaevent.event;

import com.lhw.javaevent.enums.DoorStatusEnum;

import java.util.EventObject;

/**
 * @author ：linhw
 * @date ：22.7.25 11:04
 * @description：开关门事件对象
 * @modified By：
 */
public class DoorEvent extends EventObject {

    /**
     * 状态
     */
    private DoorStatusEnum eventStatus;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DoorEvent(Object source) {
        super(source);
    }

    public DoorEvent(Object source, DoorStatusEnum eventStatus) {
        super(source);
        this.eventStatus = eventStatus;
    }

    public void setEventStatus(DoorStatusEnum eventStatus) {
        this.eventStatus = eventStatus;
    }

    public DoorStatusEnum getEventStatus() {
        return this.eventStatus;
    }

}
