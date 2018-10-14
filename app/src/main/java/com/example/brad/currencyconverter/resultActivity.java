package com.example.brad.currencyconverter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class resultActivity extends AppCompatActivity {

    private LineChart lineChart;

    public void makeGraph(String value1, String value2){
        Chart chart = new Chart(value1,value2);
        List<Float> rates = new ArrayList<>();
        List<Entry> yEntries = new ArrayList<>();
        LineDataSet set1;

        chart.setAxisLists();

        rates = chart.getRates();

        for(int i = 0; i < rates.size(); i++){
            yEntries.add(new Entry(i,rates.get(i)));
        }

        set1 = new LineDataSet(yEntries, "DataSet 1");
        set1.setDrawValues(false);
        set1.setColor(Color.GREEN);
        set1.setCircleColor(Color.GREEN);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(true);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);
        Legend legend = lineChart.getLegend();
        legend.setEnabled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);   // Hide the description
        lineChart.getAxisLeft().setDrawLabels(true);
        lineChart.getAxisRight().setDrawLabels(false);
        lineChart.getXAxis().setDrawLabels(false);

        lineChart.getLegend().setEnabled(false);
    }



    public double convertAmount(String value1, String value2, double amountEntered){
        Converter converter = new Converter(value1, value2);
        double rate = converter.getCurrentRate();

        return amountEntered * rate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        TextView country1 = findViewById(R.id.country1);
        TextView country2 = findViewById(R.id.country2);
        TextView originalAmount = findViewById(R.id.originalAmount);
        TextView finalAmount = findViewById(R.id.finalAmount);
        lineChart = findViewById(R.id.chart);

        String value1= getIntent().getStringExtra("country1");
        String value2= getIntent().getStringExtra("country2");
        double amountEntered = getIntent().getExtras().getDouble("amount");

        double rate = convertAmount(value1,value2,amountEntered);

        DecimalFormat formatter = new DecimalFormat("#0.00");
        country1.setText(value1 + ":");
        country2.setText(value2 + ":");
        originalAmount.setText(formatter.format(amountEntered));
        finalAmount.setText(formatter.format(rate));

        makeGraph(value1,value2);
        lineChart.invalidate();
    }
}
