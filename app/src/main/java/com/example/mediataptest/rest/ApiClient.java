package com.example.mediataptest.rest;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl, Context context){

        if (retrofit==null) {
            File httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
            Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(chain -> {
                        try {
                            return chain.proceed(chain.request());
                        } catch (Exception e) {
                            Request offlineRequest = chain.request().newBuilder()
                                    .header("Cache-Control", "public, only-if-cached," +
                                            "max-stale=" + 60 * 60 * 24)
                                    .build();
                            return chain.proceed(offlineRequest);
                        }
                    })
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
