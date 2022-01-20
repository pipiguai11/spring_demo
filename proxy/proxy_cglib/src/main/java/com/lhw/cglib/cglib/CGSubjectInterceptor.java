package com.lhw.cglib.cglib;

import com.lhw.cglib.util.MonitorUtil;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ：linhw
 * @date ：21.5.15 23:57
 * @description：cglib代理拦截器对象
 * @modified By：
 */
public class CGSubjectInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("--------------------------proxy start-----------------------------");
        MonitorUtil.start();
        Object result = methodProxy.invokeSuper(o,objects);
        MonitorUtil.finish(method.getName());
        System.out.println("--------------------------proxy end-----------------------------");
        return result;
    }
}
