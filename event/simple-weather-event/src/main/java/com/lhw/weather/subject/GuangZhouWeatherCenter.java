package com.lhw.weather.subject;

import com.lhw.weather.observer.WeatherObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：linhw
 * @date ：22.7.25 10:17
 * @description：广州气象中心
 * @modified By：
 */
public class GuangZhouWeatherCenter implements WeatherCenter<WeatherObserver>{

    public List<WeatherObserver> weatherObservers = new ArrayList<>();

    @Override
    public void publishWeatherInfo() {
        for (WeatherObserver weatherObserver : weatherObservers) {
            weatherObserver.sendWeatherWarning();
        }
    }

    @Override
    public void observerRegister(WeatherObserver observer) {
        this.weatherObservers.add(observer);
    }

    @Override
    public void observerRemove(WeatherObserver observer) {
        this.weatherObservers.remove(observer);
    }
}
