package com.rdmns24.chamiapps.rdmns24live.Services.API.Sync;

import android.content.Context;
import android.widget.Toast;

import com.rdmns24.chamiapps.rdmns24live.Helpers.Consts;
import com.rdmns24.chamiapps.rdmns24live.Models.Newsfeed;
import com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface.getrdmnsNews;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fidenz on 3/30/18.
 */

public class Getrdmnsnewssync {

    private Context context;
    private List<Newsfeed.DataBean> dataBeans = new ArrayList<>();
    private getNewsfeedcallback callback;

    public Getrdmnsnewssync(Context context, List<Newsfeed.DataBean> dataBeans, getNewsfeedcallback callback) {
        this.context = context;
        this.dataBeans = dataBeans;
        this.callback = callback;
    }



    public void newsRetrofit(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final getrdmnsNews getrdmnsNewsAPI = retrofit.create(getrdmnsNews.class);
        Call<Newsfeed> newsfeedCall = getrdmnsNewsAPI.getNews();

        newsfeedCall.enqueue(new Callback<Newsfeed>() {
            @Override
            public void onResponse(Call<Newsfeed> call, Response<Newsfeed> response) {



                if (response.isSuccessful() && response.body()!=null && response !=null){


                    callback.onnewsfeedfound(true,response.body().getData());

                }
                else {

                    callback.onnewsfeedfound(false,null);

                    Toast.makeText(context,"Can't Connect to the API",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Newsfeed> call, Throwable t) {

                callback.onnewsfeedfound(false,null);

                Toast.makeText(context,"Can't Connect to the API",Toast.LENGTH_LONG).show();

            }
        });



    }

    public interface getNewsfeedcallback {

        void onnewsfeedfound(boolean status, List<Newsfeed.DataBean> response);


    }
}
