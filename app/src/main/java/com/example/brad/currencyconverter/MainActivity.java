package com.example.brad.currencyconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<countryItem> mCountryList;
    private countryAdapter mAdapter;
    private String countryName1;
    private String countryName2;
    private double amountEntered;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();
        Spinner spinnerCountries1 = findViewById(R.id.Countries1);
        Spinner spinnerCountries2 = findViewById(R.id.Countries2);

        mAdapter = new countryAdapter(this, mCountryList);
        spinnerCountries1.setAdapter(mAdapter);
        spinnerCountries2.setAdapter(mAdapter);

        spinnerCountries1.setOnItemSelectedListener(newListener(1));
        spinnerCountries2.setOnItemSelectedListener(newListener(2));


    }


    //The onClick method for the ImageButton
    public void convertButton(View v) {
        EditText edittext = findViewById(R.id.amountEditText);
        amountEntered = Double.parseDouble(edittext.getText().toString());
        Intent intent = new Intent(MainActivity.this, resultActivity.class);
        intent.putExtra("country1",countryName1);
        intent.putExtra("country2",countryName2);
        intent.putExtra("amount",amountEntered);
        startActivity(intent);
    }


    //BELOW IS CODE FOR THE SPINNER DROP DOWN MENUS.
    private AdapterView.OnItemSelectedListener newListener(final int number){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                countryItem clickedItem = (countryItem) adapterView.getItemAtPosition(i);
                String clickedCountryName = clickedItem.getCountryName();
                if(number==1){
                    countryName1=clickedCountryName;
                }else{
                    countryName2=clickedCountryName;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
    }

    private void initList(){
        mCountryList = new ArrayList<>();
        mCountryList.add(new countryItem("AUD", R.drawable.australia));
        mCountryList.add(new countryItem("CAD", R.drawable.canada));
        mCountryList.add(new countryItem("CNY", R.drawable.china));
        mCountryList.add(new countryItem("EUR", R.drawable.european_union));
        mCountryList.add(new countryItem("FRF", R.drawable.france));
        mCountryList.add(new countryItem("INR", R.drawable.india));
        mCountryList.add(new countryItem("JPY", R.drawable.japan));
        mCountryList.add(new countryItem("CHF", R.drawable.switzerland));
        mCountryList.add(new countryItem("GBP", R.drawable.united_kingdom));
        mCountryList.add(new countryItem("USD", R.drawable.united_states));
    }

}
