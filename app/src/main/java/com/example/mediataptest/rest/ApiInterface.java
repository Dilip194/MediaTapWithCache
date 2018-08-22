package com.example.mediataptest.rest;

import com.example.mediataptest.mediaModel.MediaModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpssearch=Sachin+T&gpslimit=10")
    public Call<MediaModel> getMediaResponse();
}
