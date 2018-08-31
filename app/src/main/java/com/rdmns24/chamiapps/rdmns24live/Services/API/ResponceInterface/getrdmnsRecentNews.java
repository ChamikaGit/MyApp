package com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface;

import com.rdmns24.chamiapps.rdmns24live.Models.NewsfeedRecent;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fidenz on 5/2/18.
 */

public interface getrdmnsRecentNews {

    @GET("/admin/index.php/api/list_recent_notifications")
    Call<NewsfeedRecent> getRecentNotifications();
}
