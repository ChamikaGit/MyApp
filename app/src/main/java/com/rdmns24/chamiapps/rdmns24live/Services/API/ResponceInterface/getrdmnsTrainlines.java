package com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface;

import com.rdmns24.chamiapps.rdmns24live.Models.TrainLines;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fidenz on 4/4/18.
 */

public interface getrdmnsTrainlines {


        @GET("/admin/index.php/api/list_train_lines")
        Call<TrainLines> getLines();



}
