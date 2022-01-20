package com.lhw.generator.module;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ：linhw
 * @date ：21.5.24 14:18
 * @description：UID序列生成
 * @modified By：
 */
@Data
@Entity(name = "WORKER_NODE")
public class WorkerNode {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Column(name = "HOST_NAME")
    private String hostName;

    private String port;

    private int type;

    @Column(name = "LAUNCH_DATE")
    private Date launchDate;

    private Date modified = new Date();

    private Date created = new Date();

}
