package com.gemy.ahmed.baking.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtil {

    private static final String BASE_URL="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    private static WebServices webServices;
    public static WebServices getRetrofitClient() {
        if (webServices == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .callTimeout(1, TimeUnit.MINUTES)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            webServices = retrofit.create(WebServices.class);
        }
        return webServices;
    }
    public static boolean checkNetworkConnection(Activity activity) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
