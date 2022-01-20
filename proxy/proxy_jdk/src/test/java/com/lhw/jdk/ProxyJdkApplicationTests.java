package com.lhw.jdk;

import com.lhw.jdk.jdk.Subject;
import com.lhw.jdk.jdk.SubjectImpl;
import com.lhw.jdk.jdk.SubjectProxy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@SpringBootTest
class ProxyJdkApplicationTests {

    @Test
    void contextLoads() {
        Subject subject = new SubjectImpl();
        InvocationHandler proxy = new SubjectProxy<>(subject);
        Subject proxyInstance = (Subject) Proxy.newProxyInstance(proxy.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),proxy);
        proxyInstance.hello();

    }

}
