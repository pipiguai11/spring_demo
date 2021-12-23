package com.lhw.value.apply.simple;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：linhw
 * @date ：21.12.23 13:40
 * @description：数组类型注入
 * @modified By：
 */
@Configuration
@Data
public class ArrayPropertyInject {

    @Value("${lhw.array.value:1,2,3,4,5}")
    private int[] array;

}
