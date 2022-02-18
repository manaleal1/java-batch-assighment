package com.example.search.service;

import com.example.details.service.WeatherService;
import com.example.search.exception.CityIdNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService{
    private final WeatherService weatherService;

    public SearchServiceImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public Integer getCityIdByName(String city) {
        List<Integer> cityIdInfo = weatherService.findCityIdByName(city);
        if( cityIdInfo.isEmpty() )
            throw new CityIdNotFoundException(city);
        return cityIdInfo.get(0);
    }

    @Override
    public Map<String, Map> getWeatherDetailsById(int id) {
        return weatherService.findCityNameById(id);
    }
}