package com.lhw.value.apply.el;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author ：linhw
 * @date ：21.12.23 16:18
 * @description：List集合类型注入
 * @modified By：
 */
@Configuration
@Data
public class ListPropertyInject {

    //正常注入值，但是如果没有配置的话会报错
    @Value("#{'${lhw.list.value}'.split(',')}")
    private List<String> list;

    //设置默认值，如果没有配置，则默认是null
    @Value("#{'${lhw.list.default:}'.empty ? null : '${lhw.list.default:}'.split(',')}")
    private List<String> defaultList;

}
