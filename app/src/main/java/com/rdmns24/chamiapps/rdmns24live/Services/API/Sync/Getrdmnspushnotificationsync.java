package com.rdmns24.chamiapps.rdmns24live.Services.API.Sync;

import android.content.Context;
import android.widget.Toast;

import com.rdmns24.chamiapps.rdmns24live.Helpers.Consts;
import com.rdmns24.chamiapps.rdmns24live.Models.Notificationstatus;
import com.rdmns24.chamiapps.rdmns24live.Models.TrainLines;
import com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface.getrdmnsPushNotification;
import com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface.getrdmnsTrainlines;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fidenz on 4/4/18.
 */

public class Getrdmnspushnotificationsync {

    private Context context;
    private List<TrainLines.DataBean> dataBeans = new ArrayList<>();
    private getPushNotificationcallback callback;
    private String playerID;

    public Getrdmnspushnotificationsync(Context context, List<TrainLines.DataBean> dataBeans,String playerID, getPushNotificationcallback callback) {
        this.context = context;
        this.dataBeans = dataBeans;
        this.callback = callback;
        this.playerID = playerID;
    }

    public void GetPushNotificationRetrofit(){

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

        final getrdmnsPushNotification getrdmnsPushNotificationAPI = retrofit.create(getrdmnsPushNotification.class);
        Call<List<Notificationstatus>> trainLinesCall = getrdmnsPushNotificationAPI.getPushNotifications(playerID);
        trainLinesCall.enqueue(new Callback<List<Notificationstatus>>() {
            @Override
            public void onResponse(Call<List<Notificationstatus>> call, Response<List<Notificationstatus>> response) {
                if (response.isSuccessful() && response.body()!=null && response !=null){
                    Notificationstatus notificationstatus = response.body().get(0);
                    callback.onPushNotification(true,notificationstatus);
                }
                else {
                    callback.onPushNotification(false,null);
                    Toast.makeText(context,"Can't Connect to the API",Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<List<Notificationstatus>> call, Throwable t) {
                callback.onPushNotification(false,null);
                Toast.makeText(context,"Can't Connect to the API",Toast.LENGTH_LONG).show();

            }
        });
    }

    public interface getPushNotificationcallback {

        void onPushNotification(boolean status, Notificationstatus response);


    }
}
