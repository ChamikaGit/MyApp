package com.rdmns24.chamiapps.rdmns24live.Services.API.Sync;

import android.content.Context;
import android.widget.Toast;

import com.rdmns24.chamiapps.rdmns24live.Helpers.Consts;
import com.rdmns24.chamiapps.rdmns24live.Models.NotificationPost;
import com.rdmns24.chamiapps.rdmns24live.Models.Notificationstatus;
import com.rdmns24.chamiapps.rdmns24live.Models.TrainLines;
import com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface.getrdmnsPushNotification;
import com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface.postrdmnsPushNotification;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fidenz on 4/4/18.
 */

public class Postrdmnspushnotificationsync {

    private Context context;
    private getPostPushNotificationcallback callback;
    private JSONObject tags;

    public Postrdmnspushnotificationsync(Context context, JSONObject tags, getPostPushNotificationcallback callback) {
        this.context = context;
        this.tags = tags;
        this.callback = callback;
    }

    public void PostPushNotificationRetrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final postrdmnsPushNotification postrdmnsPushNotification = retrofit.create(postrdmnsPushNotification.class);
        RequestBody bodyRequest = RequestBody.create(MediaType.parse("application/json"), tags.toString());
        Call<NotificationPost> notificationPostCall = postrdmnsPushNotification.postPushNotifications(bodyRequest);
        notificationPostCall.enqueue(new Callback<NotificationPost>() {
            @Override
            public void onResponse(Call<NotificationPost> call, Response<NotificationPost> response) {
                if (response.isSuccessful() && response.body()!=null && response !=null){
                    callback.onPostPushNotification(true,response.body());
                }else {
                    callback.onPostPushNotification(false,response.body());
                    Toast.makeText(context,"Can't Connect to the API",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NotificationPost> call, Throwable t) {
                callback.onPostPushNotification(false,null);
                Toast.makeText(context,"Can't Connect to the API",Toast.LENGTH_LONG).show();
            }
        });

    }

    public interface getPostPushNotificationcallback {

        void onPostPushNotification(boolean status, NotificationPost response);


    }
}
