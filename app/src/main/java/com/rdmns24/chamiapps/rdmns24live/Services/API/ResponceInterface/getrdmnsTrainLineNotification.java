package com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface;

import com.rdmns24.chamiapps.rdmns24live.Models.TraineLinesNotifications;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fidenz on 4/10/18.
 */

public interface getrdmnsTrainLineNotification {


    @GET("admin/index.php/api/list_line_notifications")
    Call<TraineLinesNotifications> getNotification(
                                   @Query("train_line_id") String trainlineid);


}
