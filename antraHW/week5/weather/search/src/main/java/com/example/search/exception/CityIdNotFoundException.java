package com.example.search.exception;

public class CityIdNotFoundException extends RuntimeException{
    public CityIdNotFoundException(String msg) {
        super(msg);
    }
}
