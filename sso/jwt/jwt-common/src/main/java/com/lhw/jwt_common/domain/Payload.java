package com.lhw.jwt_common.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author ：linhw
 * @date ：22.4.18 13:28
 * @description：
 * @modified By：
 */
@Data
public class Payload <T> {

    private String id;

    private T userInfo;

    private Date expiration;

}