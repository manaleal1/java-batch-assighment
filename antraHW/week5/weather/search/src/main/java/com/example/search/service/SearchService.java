package com.example.search.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SearchService{
    Integer getCityIdByName(String city);
    Map<String, Map> getWeatherDetailsById(int id);
}