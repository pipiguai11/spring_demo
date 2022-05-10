package com.lhw.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * @author ：linhw
 * @date ：22.5.10 11:19
 * @description：跨库事务测试服务
 * @modified By：
 */
@Service
public class StepOverDatasourceTransactionService {

    @Autowired
    @Qualifier("firstDataSource")
    private DataSource firstDatasource;

    @Autowired
    @Qualifier("secondDataSource")
    private DataSource secondDatasource;

    @Autowired
    @Qualifier("firstJdbcTemplate")
    private JdbcTemplate firstJdbcTemplate;

    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate secondJdbcTemplate;

    private static final Random random = new Random();

    /**
     * 添加事务
     *
     *      rollbackFor ： 设置遇到指定异常时就回滚
     *      transactionManager： 设置事务管理器，只允许配置单个事务管理器，配置了哪个事务管理器，哪个数据源的事务就生效
     *          比如这里配置了secondTransactionManager事务管理器，则secondDataSource这个数据源的事务就会生效，当方法抛出异常时
     *          first数据源事务失效，正常插入，second数据源事务生效，数据回滚，不会进行插入
     *
     *      注意：
     *          要使用JDBC进行数据库的操作才能使得事务生效，如果使用原生的DataSource则不会
     *
     *      结论：
     *          经过测试，在多数据源的情况下，多数据源的事务不应该放在同一个方法下，因为每一个事务只能配置一个事务管理器
     *          可以将其分开来，每个数据源对应一个事务处理
     *
     * @throws SQLException
     */
//    @Transactional(rollbackFor = Exception.class, transactionManager = "firstTransactionManager")
    @Transactional(rollbackFor = Exception.class, transactionManager = "secondTransactionManager")
    public void testTransaction() throws SQLException {
        //原生的DataSource进行CRUD操作事务失效
//        firstDatasourceInsert();
//        secondDatasourceInsert();

        //使用JDBC进行CRUD才能使事务生效
        firstJdbcTemplateInsert();
        secondJdbcTemplateInsert();

        System.out.println(1 / 0);
    }

    /**
     * 采用JDBC的方式进行插入操作
     *
     *      事务正常
     *
     */
    private void firstJdbcTemplateInsert() {
        String insertSql = "insert into test_transaction values(?,?,?)";
        firstJdbcTemplate.update(insertSql,new Object[]{random.nextInt(1000), "测试事务", "lhw"});
    }

    private void secondJdbcTemplateInsert() {
        String insertSql = "insert into test_transaction values(?,?,?)";
        secondJdbcTemplate.update(insertSql,new Object[]{random.nextInt(1000) + 1000, "测试事务", "lhw"});
    }

    /**
     * 直接采用原生的DataSource对象获取连接进行插入操作
     *
     *      测试结果，事务失效了
     *
     * @throws SQLException
     */
    private void firstDatasourceInsert() throws SQLException {
        String insertSql = "insert into test_transaction values(?,?,?)";

        Connection connection = firstDatasource.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertSql);
        statement.setInt(1,random.nextInt(1000));
        statement.setString(2,"测试事务");
        statement.setString(3,"lhw");

        boolean resultSet = statement.execute();
        System.out.println(resultSet);
    }

    private void secondDatasourceInsert() throws SQLException {
        String insertSql = "insert into test_transaction values(?,?,?)";

        Connection connection = secondDatasource.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertSql);
        statement.setInt(1,random.nextInt(1000) + 1000);
        statement.setString(2,"测试事务");
        statement.setString(3,"lhw");

        boolean resultSet = statement.execute();
        System.out.println(resultSet);
    }

}
