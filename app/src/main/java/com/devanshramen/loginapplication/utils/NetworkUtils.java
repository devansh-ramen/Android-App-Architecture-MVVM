package com.devanshramen.loginapplication.utils;

import com.devanshramen.loginapplication.repository.remote.APIService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.devanshramen.loginapplication.repository.remote.APIService.BASE_URL;

/**
 * Created by devanshramen on 11/19/17.
 */

public class NetworkUtils {

    public static APIService getAPIService() {
        Retrofit.Builder retrofit = new Retrofit.Builder();

        retrofit
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.build().create(APIService.class);
    }
}
