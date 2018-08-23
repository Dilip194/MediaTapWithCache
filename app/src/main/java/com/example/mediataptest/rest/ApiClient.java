package com.example.mediataptest.rest;

import android.content.Context;

import com.example.mediataptest.utils.NetworkService;
import com.ncornette.cache.OkCacheControl;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.MINUTES;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context, String baseUrl) {

        if (retrofit == null) {
            File httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
            Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            OkCacheControl.NetworkMonitor networkMonitor = (OkCacheControl.NetworkMonitor) context;

            OkHttpClient httpClient = OkCacheControl.on(new OkHttpClient.Builder())
                    .overrideServerCachePolicy(30, MINUTES)
                    .forceCacheWhenOffline(networkMonitor)
                    .apply() // return to the OkHttpClient.Builder instance
                    .cache(cache)
                    .build();

            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .baseUrl(baseUrl)
                    .build();
        }
        return retrofit;
    }
}
