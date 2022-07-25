package com.lhw.springevent.listener.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author ：linhw
 * @date ：22.7.25 14:59
 * @description：本地事务事件监听器
 *
 *      需要额外引入依赖：
 *          <dependency>
 *             <groupId>org.springframework</groupId>
 *             <artifactId>spring-tx</artifactId>
 *             <version>5.3.14</version>
 *             <scope>compile</scope>
 *         </dependency>
 *
 *
 *      事务状态：
 *      AFTER_COMMIT - 事务成功提交；
 *      AFTER_ROLLBACK – 事务回滚后；
 *      AFTER_COMPLETION – 事务完成后（无论是提交还是回滚）；
 *      BEFORE_COMMIT - 事务提交前
 *
 * @modified By：
 */
@Component
public class LocalTransactionalEventListener {

    private static final Logger log = LoggerFactory.getLogger(LocalTransactionalEventListener.class);

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onTransactionChange(ApplicationEvent applicationEvent) {
        //这里可以设置一个事务事件，当监听到此事件的时候触发，范围可以不用ApplicationEvent这个全范围，
        //而只需要缩小到一个自定义的事务事件即可，如自定义的TransactionEvent
//        log.info("监听到事务提交的事件");
    }

}
