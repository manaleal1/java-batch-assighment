package com.example.search.controller;

import com.example.search.exception.CityIdNotFoundException;
import com.example.search.response.ErrorResponse;
import com.example.search.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@ComponentScan(basePackages = {"com.example.details", "com.example.search"} )
@EnableEurekaClient
@RestController
public class SearchController {

    Logger logger = LoggerFactory.getLogger(SearchController.class);

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/weather/search")
    public List<Map<String, Map>> getDetails(@RequestParam String cities) {
        logger.info("Retrieving responses for each city name");
        List<Integer> cityIds = new ArrayList<Integer>();
        String[] multipleCityNames = cities.split(",");
        for (String city : multipleCityNames) {
            logger.info("Retrieving " + city + " id");
            cityIds.add( searchService.getCityIdByName(city) );
        }

        logger.info("Combining all responses into one response");
        List<Map<String, Map>> allWeather = new ArrayList<>();
        for (Integer id: cityIds) {
            allWeather.add(searchService.getWeatherDetailsById(id));
        }
        logger.info("Sending combined response back to user");
        return allWeather;
    }

    @ExceptionHandler(CityIdNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(Exception e){
        logger.error("City id for " + e.getMessage() + " was not found.");
        return new ResponseEntity<>(
                new ErrorResponse("The city id for " + e.getMessage() + " was not found", "404"),
                HttpStatus.NOT_FOUND
        );
    }

}
