package com.lhw.cglib.util;

/**
 * @author ：linhw
 * @date ：21.5.15 23:44
 * @description：时间工具
 * @modified By：
 */
public class MonitorUtil {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void start(){
        threadLocal.set(System.currentTimeMillis());
    }

    public static void finish(String name){
        System.out.println("【" + name +"】 耗时" + (System.currentTimeMillis() - threadLocal.get()) + "ms");
    }

}
