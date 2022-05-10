package com.lhw.transaction.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * @author ：linhw
 * @date ：22.5.10 10:29
 * @description：数据源配置
 * @modified By：
 */
@Configuration
public class DatasourceConfig {

    /**
     * 第一个数据源
     * @return
     */
    @Bean("firstDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("firstTransactionManager")
    public PlatformTransactionManager  firstTransactionManager(@Qualifier("firstDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean("firstTransactionTemplate")
    public TransactionTemplate firstTransactionTemplate(@Qualifier("firstTransactionManager") PlatformTransactionManager transactionManager) {
        TransactionTemplate template = new TransactionTemplate();
        template.setTransactionManager(transactionManager);
        template.setIsolationLevelName("ISOLATION_DEFAULT");
        template.setPropagationBehaviorName("PROPAGATION_REQUIRED");
        return template;
    }

    @Primary
    @Bean("firstJdbcTemplate")
    public JdbcTemplate firstJdbcTemplate(@Qualifier("firstDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 第二个数据源
     * @return
     */
    @Bean("secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager(@Qualifier("secondDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("secondTransactionTemplate")
    public TransactionTemplate secondTransactionTemplate(@Qualifier("secondTransactionManager") PlatformTransactionManager transactionManager) {
        TransactionTemplate template = new TransactionTemplate();
        template.setTransactionManager(transactionManager);
        template.setIsolationLevelName("ISOLATION_DEFAULT");
        template.setPropagationBehaviorName("PROPAGATION_REQUIRED");
        return template;
    }

    @Bean("secondJdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
