package com.rdmns24.chamiapps.rdmns24live.Services.API.Sync;

import android.content.Context;
import android.widget.Toast;

import com.rdmns24.chamiapps.rdmns24live.Helpers.Consts;
import com.rdmns24.chamiapps.rdmns24live.Models.TraineLinesNotifications;
import com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface.getrdmnsTrainLineNotification;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fidenz on 4/10/18.
 */

public class Getrdmnsnotificationsync {

    private Context context;
    private List<TraineLinesNotifications.DataBean> dataBeans = new ArrayList<>();
    private getTrainlinenoticallback callback;

    public Getrdmnsnotificationsync(Context context, List<TraineLinesNotifications.DataBean> dataBeans, getTrainlinenoticallback callback) {
        this.context = context;
        this.dataBeans = dataBeans;
        this.callback = callback;
    }

    public void NotiifcationRetrofit(String Trainelineid) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        final getrdmnsTrainLineNotification lineNotificationAPI =retrofit.create(getrdmnsTrainLineNotification.class);
        Call<TraineLinesNotifications> call =lineNotificationAPI.getNotification(Trainelineid);


        call.enqueue(new Callback<TraineLinesNotifications>() {
            @Override
            public void onResponse(Call<TraineLinesNotifications> call, Response<TraineLinesNotifications> response) {


                if (response.isSuccessful() && response.body()!=null && response !=null){


                    callback.ontrainelinesnotifound(true,response.body().getData());


                }
                else {

                    callback.ontrainelinesnotifound(false,null);

                    Toast.makeText(context,"Can't Connect to the API",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<TraineLinesNotifications> call, Throwable t) {

                callback.ontrainelinesnotifound(false,null);

                Toast.makeText(context,"Can't Connect to the API",Toast.LENGTH_LONG).show();

            }
        });
    }


    public interface getTrainlinenoticallback {


        void ontrainelinesnotifound(boolean status, List<TraineLinesNotifications.DataBean> response);


    }
}
