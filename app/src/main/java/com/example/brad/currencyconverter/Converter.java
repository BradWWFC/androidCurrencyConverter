package com.example.brad.currencyconverter;

import java.util.concurrent.ExecutionException;

public class Converter {

    private String country1;
    private String country2;
    private static double rate;

    public Converter(String value1, String value2){
        country1 = value1;
        country2 = value2;
    }

    public static void setCurrentRate(double x){
        rate = x;
    }

    public double getCurrentRate() {
        String[] myTaskParams = {country1,country2};
        Scraper scraper = new Scraper();
        try {
            scraper.execute(myTaskParams).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return rate;
    }
}
