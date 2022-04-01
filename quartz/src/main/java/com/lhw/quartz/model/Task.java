package com.lhw.quartz.model;

import lombok.Data;

import java.util.Date;

/**
 * @author ：linhw
 * @date ：22.3.31 10:07
 * @description：任务
 * @modified By：
 */
@Data
public class Task {

    /**任务id*/
    private String id;

    /**起始时间*/
    private Date startTime;

    /**结束时间*/
    private Date endTime;

    /**任务名称*/
    private String taskName;

    /**任务状态*/
    private int status;

    /**任务类型*/
    private int taskType;

}
