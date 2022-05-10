package com.lhw.transaction;

import com.lhw.transaction.service.StepOverDatasourceTransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

/**
 * @author ：linhw
 * @date ：22.5.10 11:24
 * @description：跨库事务测试
 * @modified By：
 */
@SpringBootTest
public class StepOverDataSourceTransactionTest {

    @Autowired
    private StepOverDatasourceTransactionService service;

    @Test
    public void test() throws SQLException {
        service.testTransaction();
    }

}
