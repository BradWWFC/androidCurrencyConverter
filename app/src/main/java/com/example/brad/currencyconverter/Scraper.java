package com.example.brad.currencyconverter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Scraper extends AsyncTask<String,Void, Void> {

    @Override
    protected Void doInBackground(String... strings) {
        String country1, country2;
        country1=strings[0];
        country2=strings[1];

        Document doc;
        try {
            String url = generateURL(country1,country2);
            List<String> listConverter = new ArrayList<>();
            List<String> dates = new ArrayList<>();
            List<Float> rates = new ArrayList<>();
            List<String> percent = new ArrayList<>();

            doc = Jsoup.connect(url).get();
            Element table = doc.select("table").get(2);
            for(Element tds : table.select("td")) {
                listConverter.add(tds.text());
            }
            for(int i=3;i < 120;i+=3) {
                dates.add(listConverter.get(i));
            }
            for(int i=4;i < 120;i+=3) {
                rates.add(Float.valueOf(listConverter.get(i)));
            }
            for(int i=5;i < 120;i+=3) {
                percent.add(listConverter.get(i));
            }

            Converter.setCurrentRate(Double.parseDouble(listConverter.get(4)));
            Chart.setArrays(dates,rates,percent);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @SuppressLint("DefaultLocale")
    private String getDate(String request) {
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String localDate = sdf.format(date);

        switch (request) {
            case "year":
                String yyyy = localDate.substring(0, 4);
                return yyyy;
            case "month":
                int mm = Integer.parseInt(localDate.substring(6, 7));
                String stringMM = (mm < 10 ? "0" : "") + mm;
                return stringMM;
            case "previousMonth":
                int lm = Integer.parseInt(localDate.substring(6, 7))-3;
                String stringLM = (lm < 10 ? "0" : "") + lm;
                return stringLM;
            case "day":
                int dd = Integer.parseInt(localDate.substring(9, 10));
                String stringDD = (dd < 10 ? "0" : "") + dd;
                return stringDD;
            default:
                return null;
        }
    }

    @NonNull
    private String generateURL(String country1, String country2){
        //"http://fxtop.com/en/historical-exchange-rates.php?A=1&C1=USD&C2=EUR&DD1=27&MM1=06&YYYY1=2018&B=1&P=&I=1&DD2=27&MM2=07&YYYY2=2018&btnOK=Go%21";
        //http://fxtop.com/en/historical-exchange-rates.php?MA=0&YA=0&C1=AUD&C2=USD&A=1&DD1=30&MM1=06&YYYY1=2018&DD2=30&MM2=07&YYYY2=2018&LARGE=1&LANG=en&MM1Y=0&PRINT=1&CJ=0
        String yyyy = getDate("year");
        String mm = getDate("month");
        String lm = getDate("previousMonth");
        String dd = getDate("day");

        return "http://fxtop.com/en/historical-exchange-rates.php?MA=0&YA=0&C1="+ country1 +"&C2="+ country2 +"&A=1&DD1="+dd+"&MM1="+lm+"&YYYY1="+yyyy+"&DD2="+dd+"&MM2="+mm+"&YYYY2="+yyyy+"&LARGE=1&LANG=en&MM1Y=0&PRINT=1&CJ=0";
    }

}


