package com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface;

import com.rdmns24.chamiapps.rdmns24live.Models.Lostfound;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fidenz on 3/30/18.
 */

public interface getrdmnsLostandFound {

    @GET("/admin/index.php/api/list_found_lost_items")
    Call<Lostfound> getLostFound();
}



