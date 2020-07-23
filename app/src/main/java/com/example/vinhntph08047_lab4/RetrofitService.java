package com.example.vinhntph08047_lab4;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static Retrofit retrofit;
    public static OkHttpClient okHttpClient;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(APIService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

//    public static OkHttpClient getOkHttpClient() {
//        if (okHttpClient == null) {
//            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            okHttpClient = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS)
//                    .writeTimeout(10L, TimeUnit.SECONDS)
//                    .readTimeout(10L, TimeUnit.SECONDS)
//                    .readTimeout(10L, TimeUnit.SECONDS)
//                    .addInterceptor(httpLoggingInterceptor)
//                    .build();
//        }
//        return okHttpClient;
//    }
}
