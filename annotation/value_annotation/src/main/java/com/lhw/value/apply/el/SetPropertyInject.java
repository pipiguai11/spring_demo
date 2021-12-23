package com.lhw.value.apply.el;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @author ：linhw
 * @date ：21.12.23 16:24
 * @description：Set集合类型注入
 *
 *      和List类型是一样的
 *
 * @modified By：
 */
@Configuration
@Data
public class SetPropertyInject {

    @Value("#{'${lhw.set.value}'.split(',')}")
    private Set<String> set;

    //设置默认值，如果没有配置，则默认是null
    @Value("#{'${lhw.set.default:}'.empty ? null : '${lhw.set.default:}'.split(',')}")
    private Set<String> defaultList;

}
