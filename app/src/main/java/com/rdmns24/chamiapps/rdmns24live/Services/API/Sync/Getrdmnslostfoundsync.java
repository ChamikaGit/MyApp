package com.rdmns24.chamiapps.rdmns24live.Services.API.Sync;

import android.content.Context;
import android.widget.Toast;

import com.rdmns24.chamiapps.rdmns24live.Helpers.Consts;
import com.rdmns24.chamiapps.rdmns24live.Models.Lostfound;
import com.rdmns24.chamiapps.rdmns24live.Services.API.ResponceInterface.getrdmnsLostandFound;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Getrdmnslostfoundsync {

    private Context context;
    private List<Lostfound.Datum> dataBeans = new ArrayList<>();
    private getLostFoundCallback callback;

    public Getrdmnslostfoundsync(Context context, List<Lostfound.Datum> dataBeans, getLostFoundCallback callback) {
        this.context = context;
        this.dataBeans = dataBeans;
        this.callback = callback;
    }

    public void lostFoundRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final getrdmnsLostandFound getrdmnsLostandFoundAPI = retrofit.create(getrdmnsLostandFound.class);
        Call<Lostfound> newsfeedRecentCall = getrdmnsLostandFoundAPI.getLostFound();

        newsfeedRecentCall.enqueue(new Callback<Lostfound>() {
            @Override
            public void onResponse(Call<Lostfound> call, Response<Lostfound> response) {
                if (response.isSuccessful() && response.body() != null && response != null) {


                    callback.onLostFoundRecentFound(true, response.body().getData());

                } else {

                    callback.onLostFoundRecentFound(false, null);

                    Toast.makeText(context, "Can't Connect to the API", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Lostfound> call, Throwable t) {
                callback.onLostFoundRecentFound(false, null);

                Toast.makeText(context, "Can't Connect to the API", Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface getLostFoundCallback {
        void onLostFoundRecentFound(boolean status, List<Lostfound.Datum> response);
    }
}
