package com.lhw.weather.observer;

/**
 * @author ：linhw
 * @date ：22.7.25 10:19
 * @description：广州气象观察者
 * @modified By：
 */
public class GuangZhouWeatherObserver implements WeatherObserver {
    @Override
    public void sendWeatherWarning() {
        System.out.println("广州卫视天气预报最新通知");
    }
}
