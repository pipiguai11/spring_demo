package com.lhw.value.apply.el;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author ：linhw
 * @date ：21.12.23 16:37
 * @description：Map集合类型注入
 * @modified By：
 */
@Configuration
@Data
public class MapPropertyInject {

    @Value("#{${lhw.map.value}}")
    private Map<String,String> map;

}
