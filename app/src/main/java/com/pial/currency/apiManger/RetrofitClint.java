package com.pial.currency.apiManger;

import android.content.Context;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.pial.currency.BuildConfig.DEBUG;

public class RetrofitClint {
    private static final String BASE_URL = "https://jsonvat.com";


    private  OkHttpClient provideClient() {
        HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor();
        mLoggingInterceptor.setLevel(DEBUG ? HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder()
                .addInterceptor(mLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    private  Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiService getApiService() {
        return provideRetrofit().create(ApiService.class);
    }





}
