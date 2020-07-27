package com.example.vinhntph08047_lab4.net;

import com.example.vinhntph08047_lab4.api.APIService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static APIService apiService;
    public static OkHttpClient okHttpClient;

    public static APIService getInstance() {
        if (apiService == null) {
            apiService = new Retrofit.Builder()
                    .baseUrl(APIService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build().create(APIService.class);
        }
        return apiService;
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS)
                    .writeTimeout(10L, TimeUnit.SECONDS)
                    .readTimeout(10L, TimeUnit.SECONDS)
                    .readTimeout(10L, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();
        }
        return okHttpClient;
    }
}
