package com.lhw.aop.module;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ：linhw
 * @date ：21.12.21 11:26
 * @description：日志对象
 * @modified By：
 */
@Data
@Builder
@Entity
public class Log {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;

    /**
     * 描述
     */
    @Column
    private String description;

    /**
     * 日志时间
     */
    @Column
    private String logTime;

    /**
     * 谁触发的
     */
    @Column
    private String author;

    /**
     * 执行结果
     */
    @Column
    private String result;

    /**
     * 参数集合
     */
    @Column
    private String remark;

    /**
     * 异常信息
     */
    @Column
    private String exMessage;

    @Column
    private String ip;

    @Column
    private String url;

}
