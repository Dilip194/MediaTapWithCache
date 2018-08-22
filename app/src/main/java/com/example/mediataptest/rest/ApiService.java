package com.example.mediataptest.rest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.mediataptest.MainActivity;
import com.example.mediataptest.constant.Constants;
import com.example.mediataptest.mediaModel.MediaModel;
import com.example.mediataptest.mediaPresenterListener.ServiceCompleteListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiService implements Callback<MediaModel>{

    ServiceCompleteListener mServiceCompleteListener;
    AppCompatActivity mContext;

    public ApiService(ServiceCompleteListener mServiceCompleteListener, AppCompatActivity mContext) {

        this.mServiceCompleteListener = mServiceCompleteListener;
        this.mContext = mContext;
        callAPI();
    }

    public void callAPI(){

        ApiInterface apiInterface = ApiClient.getClient(mContext, Constants.BASE_RUL).create(ApiInterface.class);

        Call<MediaModel> call = apiInterface.getMediaResponse();

        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<MediaModel> call, Response<MediaModel> response) {

        if (response.code() == 200){
            MediaModel mediaModel = response.body();
            mServiceCompleteListener.onTaskCompleteListener(mediaModel);
        }
    }

    @Override
    public void onFailure(Call<MediaModel> call, Throwable t) {

    }
}
