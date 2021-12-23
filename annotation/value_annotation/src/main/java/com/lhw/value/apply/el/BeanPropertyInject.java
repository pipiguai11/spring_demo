package com.lhw.value.apply.el;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author ：linhw
 * @date ：21.12.23 17:03
 * @description：Bean对象注入
 * @modified By：
 */
@Configuration
@DependsOn({"listPropertyInject","simplePropertyInject"}) // 依赖ListPropertyInject对象，在它初始化完之后把它注入到这个类中
@Data
public class BeanPropertyInject {

    //注入listPropertyInject，这里直接填它的BeanName就行了
    @Value("#{listPropertyInject}")
    private ListPropertyInject listPropertyInject;

    //注入Bean对象的某个属性值（将simplePropertyInject这个Bean对象的userName属性值注入到这个类中的simpleUserName属性上）
    @Value("#{simplePropertyInject.userName}")
    private String simpleUserName;

    //注入Bean对象的某个方法的返回值（将simplePropertyInject的getDefaultValue方法的返回值注入到simpleDefaultValue属性中）
    @Value("#{simplePropertyInject.getDefaultValue()}")
    private String simpleDefaultValue;

    //使用EL表达式进行值的拼接，并把结果注入到joint上。
    @Value("#{simplePropertyInject.userName + '   ' + simplePropertyInject.getDefaultValue()}")
    private String joint;

}
