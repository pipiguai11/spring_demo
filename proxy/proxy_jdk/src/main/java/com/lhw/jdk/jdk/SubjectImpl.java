package com.lhw.jdk.jdk;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

/**
 * @author ：linhw
 * @date ：21.5.15 22:52
 * @description：被代理对象实现类
 * @modified By：
 */
public class SubjectImpl implements Subject {
    @Override
    @CallerSensitive
    public void hello() {
        //可以直接输出调用这个方法的类对象，不过前提是必须在方法前加@CallerSensitive注解
        //同时因为这个方法是jdk内部的方法，jvm开发者认为不安全，不允许开发者随便调用，因此，加了这个注解还不能够直接输出
        //这个注解有点特殊，需要通过启动类的classLoader加载才能够识别，或者启动时配置jvm参数 -Xbootclasspath/a: path，如下
        //      eg： -Xbootclasspath/a:D:\ideaWorkSpace\myDemo\redis_demo\target\classes
        System.out.println(Reflection.getCallerClass());

        System.out.println("hello world");
        System.out.println("I am lhw");
    }
}
