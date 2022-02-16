package com.lhw.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author ：linhw
 * @date ：22.2.14 10:09
 * @description：编程式事务
 * @modified By：
 */
@Service
public class TransactionTemplateService {

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void update(){
        transactionTemplate.execute((status -> {
            //TODO 做一些事务操作
            return Boolean.TRUE;
        }));
    }

}
