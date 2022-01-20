package com.lhw.jdk.jdk;

import com.lhw.jdk.util.MonitorUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ：linhw
 * @date ：21.5.15 22:53
 * @description：代理对象
 * @modified By：
 */
public class SubjectProxy<T> implements InvocationHandler {

    private T subject;

    public SubjectProxy(T subject){
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("----------------------------proxy start---------------------------------");
        MonitorUtil.start();
        Object result = method.invoke(subject,args);
        MonitorUtil.finish(method.getName());
        System.out.println("----------------------------proxy end---------------------------------");
        return result;
    }
}
