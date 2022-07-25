package com.lhw.weather.test;

import com.lhw.weather.observer.CCTVObserver;
import com.lhw.weather.observer.GuangZhouWeatherObserver;
import com.lhw.weather.subject.GuangZhouWeatherCenter;
import com.lhw.weather.subject.WeatherCenter;

/**
 * @author ：linhw
 * @date ：22.7.25 10:27
 * @description：简单天气预报事件测试
 * @modified By：
 */
public class SimpleWeatherEventTest {

    public static void main(String[] args) {
        WeatherCenter weatherCenter = init();
        weatherCenter.publishWeatherInfo();
    }

    private static WeatherCenter init() {
        GuangZhouWeatherCenter weatherCenter = new GuangZhouWeatherCenter();
        weatherCenter.observerRegister(new GuangZhouWeatherObserver());
        weatherCenter.observerRegister(new CCTVObserver());
        return weatherCenter;
    }

}
