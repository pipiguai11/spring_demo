package com.lhw.cglib;

import com.lhw.cglib.cglib.CGSubject;
import com.lhw.cglib.cglib.CGSubjectInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;

@SpringBootTest
class ProxyCglibApplicationTests {

    @Test
    void contextLoads() {
        /**
         * Enhancer是一个CGLIB中使用频率很高的一个类，他是一个字节码增强器，可以用来为无接口的类创建代理对象，它的功能和java自带的
         * Proxy类挺像的，会根据某个给定的类创建子类，并且所有非final的方法都带有回调钩子，对aop来说十分重要
         */
        //new一个增强器出来，将需要被代理的对象和拦截器对象绑定上去
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CGSubject.class);
        enhancer.setCallback(new CGSubjectInterceptor());
        CGSubject cgSubject = (CGSubject) enhancer.create();
        cgSubject.hello();

    }

}
