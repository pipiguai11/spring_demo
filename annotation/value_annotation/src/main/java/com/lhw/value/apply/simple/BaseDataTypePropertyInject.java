package com.lhw.value.apply.simple;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：linhw
 * @date ：21.12.23 13:32
 * @description：基本数据类型属性注入
 * @modified By：
 */
@Configuration
@Data
public class BaseDataTypePropertyInject {

    @Value("${lhw.base.a:1}")
    private byte a;

    @Value("${lhw.base.b:100}")
    private short b;

    @Value("${lhw.base.c:3000}")
    private int c;

    @Value("${lhw.base.d:4000000}")
    private long d;

    @Value("${lhw.base.e:5.2}")
    private float e;

    @Value("${lhw.base.f:6.1}")
    private double f;

    @Value("${lhw.base.g:false}")
    private boolean g;

    @Value("${lhw.base.h:h}")
    private char h;

    @Value("${lhw.base.a:1}")
    private Byte a1;

    @Value("${lhw.base.b:100}")
    private Short b1;

    @Value("${lhw.base.c:3000}")
    private Integer c1;

    @Value("${lhw.base.d:4000000}")
    private Long d1;

    @Value("${lhw.base.e:5.2}")
    private Float e1;

    @Value("${lhw.base.f:6.1}")
    private Double f1;

    @Value("${lhw.base.g:false}")
    private Boolean g1;

    @Value("${lhw.base.h:h}")
    private Character h1;

}
