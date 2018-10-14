package com.example.brad.currencyconverter;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class Chart {

    private String country1;
    private String country2;
    private static List<String> dates = new ArrayList<>();
    private static List<Float> rates = new ArrayList<>();
    private static List<String> percent = new ArrayList<>();
    private List<Entry> yEntries = new ArrayList<>();
    private LineDataSet set1;

    public Chart(String value1, String value2){
        country1 = value1;
        country2 = value2;
    }

    public static void setArrays(List<String> x, List<Float> y, List<String> z){
        dates = x;
        rates = y;
        percent = z;
    }

    public List<String> getDates(){
        return dates;
    }

    public List<Float> getRates(){
        return rates;
    }

    //Get the information into x and y axis charts
    public void setAxisLists(){
        String[] myTaskParams = {country1,country2};
        Scraper scraper = new Scraper();
        try {
            scraper.execute(myTaskParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
