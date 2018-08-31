package com.rdmns24.chamiapps.rdmns24live.Services.API.Sync;

import android.content.Context;
import android.widget.Toast;

import com.rdmns24.chamiapps.rdmns24live.Helpers.Consts;
import com.rdmns24.chamiapps.rdmns24live.Models.TrainLines;
import com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface.getrdmnsTrainlines;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fidenz on 4/4/18.
 */

public class Getrdmnstrainlinesync {

    private Context context;
    private List<TrainLines.DataBean> dataBeans = new ArrayList<>();
    private getTrainlinecallback callback;

    public Getrdmnstrainlinesync(Context context, List<TrainLines.DataBean> dataBeans, getTrainlinecallback callback) {
        this.context = context;
        this.dataBeans = dataBeans;
        this.callback = callback;
    }

    public void LinesRetrofit(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final getrdmnsTrainlines getrdmnsTrainlinesAPI = retrofit.create(getrdmnsTrainlines.class);

        Call<TrainLines> trainLinesCall = getrdmnsTrainlinesAPI.getLines();

        trainLinesCall.enqueue(new Callback<TrainLines>() {
            @Override
            public void onResponse(Call<TrainLines> call, Response<TrainLines> response) {


                if (response.isSuccessful() && response.body()!=null && response !=null){


                    callback.ontrainelinesfeedfound(true,response.body().getData());

                }
                else {

                    callback.ontrainelinesfeedfound(false,null);

                    Toast.makeText(context,"Can't Connect to the API",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<TrainLines> call, Throwable t) {


                callback.ontrainelinesfeedfound(false,null);

                Toast.makeText(context,"Can't Connect to the API",Toast.LENGTH_LONG).show();

            }
        });




    }

    public interface getTrainlinecallback {

        void ontrainelinesfeedfound(boolean status, List<TrainLines.DataBean> response);


    }
}
