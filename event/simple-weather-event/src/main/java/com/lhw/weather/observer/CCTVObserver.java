package com.lhw.weather.observer;

/**
 * @author ：linhw
 * @date ：22.7.25 10:20
 * @description：中央气象观察者
 * @modified By：
 */
public class CCTVObserver implements WeatherObserver {
    @Override
    public void sendWeatherWarning() {
        System.out.println("中央电视台天气预报最新消息");
    }
}
