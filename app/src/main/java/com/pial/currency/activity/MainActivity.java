package com.pial.currency.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.pial.currency.R;
import com.pial.currency.apiManger.ApiService;
import com.pial.currency.apiManger.RetrofitClint;
import com.pial.currency.apiManger.pojo.CurrencyDetail;
import com.pial.currency.apiManger.pojo.Rate;
import com.pial.currency.apiManger.pojo.Rates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    Spinner spinner;
    private ArrayAdapter<String> countryDataAdapter;
    private ArrayList<Rate> rateArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        apiService = new RetrofitClint().getApiService();
        List<String> countryList = new ArrayList<String>();
        countryList.add("Select country");
        countryDataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, countryList) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;

            }
        };
        spinner.setAdapter(countryDataAdapter);


        Call<CurrencyDetail> currencyDetailCall = apiService.getCurrencyDetails();
        currencyDetailCall.enqueue(new Callback<CurrencyDetail>() {
            @Override
            public void onResponse(Call<CurrencyDetail> call, Response<CurrencyDetail> response) {
                if (response.isSuccessful()&& response.body()!=null && !response.body().getRates().isEmpty()) {
                    countryDataAdapter.clear();
                    ArrayList<String> stringArrayList = new ArrayList<>();
                    countryDataAdapter.add("Select country");
                    for (Rate rates: response.body().getRates()){
                        stringArrayList.add(rates.getName());
                        rateArrayList.add(rates);
                    }
                    Collections.sort(stringArrayList);
                    countryDataAdapter.addAll(stringArrayList);
                    countryDataAdapter.notifyDataSetChanged();



                } else {

                }
            }

            @Override
            public void onFailure(Call<CurrencyDetail> call, Throwable t) {
                Log.e("OkHttp",t.getMessage());
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    rateArrayList.get(position - 1).getPeriods().get(0);
                    Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                    ArrayList<String> arrayList = new ArrayList<>();
                }
                /*if (rateArrayList.get(position-1).getPeriods().get(0).getRates().getStandard()!=null) {

                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
