package com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface;

import com.rdmns24.chamiapps.rdmns24live.Models.Newsfeed;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fidenz on 3/30/18.
 */

public interface getrdmnsNews {

    @GET("/admin/index.php/api/list_post")
    Call<Newsfeed> getNews();
}



