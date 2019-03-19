package com.pial.currency.apiManger;

import com.pial.currency.apiManger.pojo.CurrencyDetail;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {
    @GET("/")
    Call<CurrencyDetail> getCurrencyDetails();
}
