package com.lhw.weather.subject;

/**
 * @author ：linhw
 * @date ：22.7.25 10:15
 * @description：气象中心（被观察者、事件发布者）
 * @modified By：
 */
public interface WeatherCenter<T> {

    /**
     * 发布气象消息
     */
    void publishWeatherInfo();

    /**
     * 注册观察者
     * @param observer
     */
    void observerRegister(T observer);

    /**
     * 删除观察者
     * @param observer
     */
    void observerRemove(T observer);

}
