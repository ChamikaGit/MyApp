package com.rdmns24.chamiapps.rdmns24live.Services.API.Sync;

import android.content.Context;
import android.widget.Toast;

import com.rdmns24.chamiapps.rdmns24live.Helpers.Consts;
import com.rdmns24.chamiapps.rdmns24live.Models.NewsfeedRecent;
import com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface.getrdmnsRecentNews;

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

public class Getrdmnsnewsrecentsync {

    private Context context;
    private List<NewsfeedRecent.DataBean> dataBeans = new ArrayList<>();
    private getNewsfeedrecentcallback callback;

    public Getrdmnsnewsrecentsync(Context context, List<NewsfeedRecent.DataBean> dataBeans, getNewsfeedrecentcallback callback) {
        this.context = context;
        this.dataBeans = dataBeans;
        this.callback = callback;
    }

    public void newsrecentRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final getrdmnsRecentNews getrdmnsRecentNewsAPI = retrofit.create(getrdmnsRecentNews.class);
        Call<NewsfeedRecent> newsfeedRecentCall = getrdmnsRecentNewsAPI.getRecentNotifications();

        newsfeedRecentCall.enqueue(new Callback<NewsfeedRecent>() {
            @Override
            public void onResponse(Call<NewsfeedRecent> call, Response<NewsfeedRecent> response) {
                {

                    if (response.isSuccessful() && response.body()!=null && response !=null){

                        callback.onnewsfeedrecentfound(true,response.body().getData());
                    }
                    else {

                        callback.onnewsfeedrecentfound(false,null);

                        Toast.makeText(context,"Network Connection Problem Detected!",Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<NewsfeedRecent> call, Throwable t) {


                callback.onnewsfeedrecentfound(false,null);

                Toast.makeText(context,"Network Connection Problem Detected!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface getNewsfeedrecentcallback {

        void onnewsfeedrecentfound(boolean status, List<NewsfeedRecent.DataBean> response);


    }
}
