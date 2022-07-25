package com.lhw.weather.observer;

/**
 * @author ：linhw
 * @date ：22.7.25 10:18
 * @description：气象观察者
 * @modified By：
 */
public interface WeatherObserver {

    /**
     * 气象预警
     */
    void sendWeatherWarning();

}
