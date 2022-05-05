package com.lhw.import_annotation.selector;

import lombok.SneakyThrows;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.*;

/**
 * @author ：linhw
 * @date ：22.5.5 13:41
 * @description：Bean导入选择器
 *
 *      springboot的自动配置实现原理（就是通过这个ImportSelector实现的）
 *
 *      在项目启动时，动态的找到配置文件中的配置项，然后自动将类注册到IOC容器中。
 *
 * @modified By：
 */
public class MyImportSelector implements ImportSelector {

    private final String LOCAL_PROPERTY_PATH = "property/myAutoProperty";

    /**
     * 这里返回的是一个字符串数组
     *
     *      存放的是需要加载的类的全类名
     *
     *      在springboot中，是通过spi机制去发现META-INF/spring.factories文件中的内容，然后在这里返回需要加载的所有的类
     *
     * @param importingClassMetadata
     * @return
     */
    @SneakyThrows
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> result = new ArrayList<>();
        Enumeration<URL> urls = this.getClass().getClassLoader().getResources(LOCAL_PROPERTY_PATH);
        while ( urls.hasMoreElements() ) {
            URL url = urls.nextElement();
            UrlResource resource = new UrlResource(url);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            for (Map.Entry<?, ?> entry : properties.entrySet()) {
                result.add((String)entry.getValue());
            }
        }

        String[] str = new String[result.size()];
        return result.toArray(str);
    }
}
