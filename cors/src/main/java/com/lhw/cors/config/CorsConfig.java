
package com.lhw.cors.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@ConditionalOnExpression("${lhw.cors.allow}")
public class CorsConfig {

    @Value("${lhw.cors.mapping}")
    private String mapping;

    @Value("${lhw.cors.origin}")
    private String origin;

    @Value("${lhw.cors.method}")
    private String method;


    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOrigin(this.origin);
        //是否发送Cookie信息
        config.setAllowCredentials(true);
        //放行哪些原始域(请求方式)
        config.addAllowedMethod(this.method);
        //放行哪些原始域(头部信息)
        config.addAllowedHeader(this.origin);
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        //config.addExposedHeader(HttpHeaders.SERVER);

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration(this.mapping, config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}

