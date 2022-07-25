package com.lhw.springevent;

import com.lhw.springevent.event.DefaultEvent;
import com.lhw.springevent.publisher.DefaultEventPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringEventApplicationTests {

    @Autowired
    private DefaultEventPublisher eventPublisher;

    @Test
    void contextLoads() {

        //这里在新建自定义事件的时候，传进去当前的上下文对象即可，也可以传其他任意东西
        DefaultEvent defaultEvent = new DefaultEvent(eventPublisher.getApplicationContext());
        eventPublisher.publisherEvent(defaultEvent);

    }

    @Test
    void testCondition() {
        DefaultEvent defaultEvent = new DefaultEvent(eventPublisher.getApplicationContext());
        defaultEvent.setFlag(true);
        eventPublisher.publisherEvent(defaultEvent);
    }

}
