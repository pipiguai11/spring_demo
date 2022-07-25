package com.lhw.springevent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：linhw
 * @date ：22.7.25 14:30
 * @description：线程池配置
 * @modified By：
 */
@Configuration
public class ThreadPoolConfig {

    //CPU核心数
    private static final int CPU_CORES = Runtime.getRuntime().availableProcessors();

    @Bean(name = "default_thread_pool")
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(CPU_CORES * 2,
                CPU_CORES * 4,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
    }

}
