package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rdmns24.chamiapps.rdmns24live.Holders.NewsfeedAdapter_horizontall;
import com.rdmns24.chamiapps.rdmns24live.Models.Lostfound;
import com.rdmns24.chamiapps.rdmns24live.Models.NewsfeedRecent;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.rdmns24.chamiapps.rdmns24live.Services.API.Sync.Getrdmnslostfoundsync;
import com.rdmns24.chamiapps.rdmns24live.Services.API.Sync.Getrdmnsnewsrecentsync;
import com.rdmns24.chamiapps.rdmns24live.Services.API.Sync.Getrdmnsnewssync;

import java.util.ArrayList;
import java.util.List;

public class LostAndFoundActvity extends AppCompatActivity implements Getrdmnslostfoundsync.getLostFoundCallback {

    private TextView toobarname;
    private Getrdmnslostfoundsync getrdmnslostfoundsync;
    private List<Lostfound.Datum> dataBeansrecent = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found_actvity);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        toobarname = findViewById(R.id.toobarname);

        updateUI();
        loadJSON();
    }

    private void loadJSON() {
        getrdmnslostfoundsync = new Getrdmnslostfoundsync(getApplicationContext(), dataBeansrecent, this);
        getrdmnslostfoundsync.lostFoundRetrofit();
    }

    private void updateUI() {
        toobarname.setText("Lost And Found");
    }

    @Override
    public void onLostFoundRecentFound(boolean status, List<Lostfound.Datum> response) {

        if (response != null) {
            dataBeansrecent=response;
//            adapterHorizontall = new NewsfeedAdapter_horizontall(response, getApplicationContext(), this);
//            recyclerViewhorizonlall.setAdapter(adapterHorizontall);
//            initView_Horizoll();
//            progressbarhori.setVisibility(View.GONE);

        } else {

            Toast.makeText(getApplicationContext(), "Can't Connect with API", Toast.LENGTH_LONG).show();

        }

    }
}
