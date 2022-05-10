package com.lhw.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

@SpringBootTest
class TransactionApplicationTests {

    @Autowired
    @Qualifier("firstDataSource")
    DataSource firstDataSource;

    @Autowired
    @Qualifier("secondDataSource")
    DataSource secondDataSource;

    private final static Random random = new Random();

    @Test
    void contextLoads() throws SQLException {
//        firstDatasourceTest();
        secondDatasourceTest();
    }

    private void firstDatasourceTest() throws SQLException {
        String insertSql = "insert into test_transaction values(?,?,?)";

        Connection connection = firstDataSource.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertSql);
        statement.setInt(1,random.nextInt(1000));
        statement.setString(2,"测试事务");
        statement.setString(3,"lhw");

        boolean resultSet = statement.execute();
        System.out.println(resultSet);
    }

    private void secondDatasourceTest() throws SQLException {
        String insertSql = "insert into test_transaction values(?,?,?)";

        Connection connection = secondDataSource.getConnection();

        PreparedStatement statement = connection.prepareStatement(insertSql);
        statement.setInt(1,random.nextInt(1000) + 1000);
        statement.setString(2,"测试事务");
        statement.setString(3,"lhw");

        boolean resultSet = statement.execute();
        System.out.println(resultSet);
    }

}
