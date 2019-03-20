package com.pial.currency.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pial.currency.R;
import com.pial.currency.adapter.TaxAdapter;
import com.pial.currency.apiManger.ApiService;
import com.pial.currency.apiManger.RetrofitClint;
import com.pial.currency.apiManger.pojo.CurrencyDetail;
import com.pial.currency.apiManger.pojo.Rate;
import com.pial.currency.apiManger.pojo.Rates;
import com.pial.currency.apiManger.pojo.TaxPojo;
import com.pial.currency.listener.TaxListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TaxListener {

    private ApiService apiService;
    Spinner spinner;
    private ArrayAdapter<String> countryDataAdapter;
    private RecyclerView recyclerView;
    private TextView finalAmount;
    private EditText input_amount;
    private ArrayList<Rate> rateArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        recyclerView = findViewById(R.id.recyclerView);
        finalAmount = findViewById(R.id.finalAmount);
        input_amount = findViewById(R.id.input_amount);
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
                    ArrayList<TaxPojo> taxPojos = new ArrayList<>();
                    for(Map.Entry m:rateArrayList.get(position - 1).getPeriods().get(0).getRates().entrySet()){
                        taxPojos.add(new TaxPojo(m.getKey().toString(),Double.parseDouble(m.getValue().toString())));
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    TaxAdapter taxAdapter = new TaxAdapter(taxPojos,MainActivity.this);
                    recyclerView.setAdapter(taxAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onTaxItemClick(double value) {
        if (!input_amount.getText().toString().isEmpty()) {
            double input = Double.parseDouble(input_amount.getText().toString());
            finalAmount.setText(String.valueOf(input * value));
        } else {
            Toast.makeText(this, "Please Enter your amount", Toast.LENGTH_SHORT).show();
        }
    }
}
