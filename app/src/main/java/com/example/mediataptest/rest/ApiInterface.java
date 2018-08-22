package com.example.mediataptest.rest;

import com.example.mediataptest.mediaModel.MediaModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("")
    public Call<MediaModel> getMediaResponse();
}
