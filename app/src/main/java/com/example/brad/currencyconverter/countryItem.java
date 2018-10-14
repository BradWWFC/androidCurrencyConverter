package com.example.brad.currencyconverter;

public class countryItem {
    private String mCountryName;
    private int mFlagImage;

    public countryItem(String countryName, int flagImage){
        mCountryName= countryName;
        mFlagImage = flagImage;
    }

    public String getCountryName(){
        return mCountryName;
    }

    public int getFlagImage(){
        return mFlagImage;
    }
}
