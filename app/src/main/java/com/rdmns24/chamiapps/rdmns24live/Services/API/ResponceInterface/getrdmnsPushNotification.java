package com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface;

import com.rdmns24.chamiapps.rdmns24live.Models.NewsfeedRecent;
import com.rdmns24.chamiapps.rdmns24live.Models.Notificationstatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface getrdmnsPushNotification {

    @GET("/admin/notification_update.php?player_id=")
    Call<List<Notificationstatus>> getPushNotifications(@Query("player_id") String player_id);

}
