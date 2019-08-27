package com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface;

import com.rdmns24.chamiapps.rdmns24live.Models.NewsfeedRecent;
import com.rdmns24.chamiapps.rdmns24live.Models.NotificationPost;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface postrdmnsPushNotification {

    @POST("/admin/notification_update.php")
    Call<NotificationPost> postPushNotifications(@Body RequestBody body);
}
