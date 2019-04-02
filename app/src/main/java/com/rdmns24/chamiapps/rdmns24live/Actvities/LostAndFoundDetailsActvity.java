package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.rdmns24.chamiapps.rdmns24live.Holders.LoastandfoundAdapter;
import com.rdmns24.chamiapps.rdmns24live.Holders.LostAndFoundDetailsAdapter;
import com.rdmns24.chamiapps.rdmns24live.Models.Lostfound;
import com.rdmns24.chamiapps.rdmns24live.R;

import java.util.ArrayList;
import java.util.List;

public class LostAndFoundDetailsActvity extends AppCompatActivity implements LostAndFoundDetailsAdapter.Getpostion {

    private TextView toobarname;
    private String state = null;
    private RecyclerView recylerview_lostandfounddetails;
    private LostAndFoundDetailsAdapter loastandfoundDetailsAdapter;
    List<Lostfound.Datum> fetchList = new ArrayList<>();
    List<Lostfound.Datum> fetchFoundList = new ArrayList<>();
    List<Lostfound.Datum> fetchLostList = new ArrayList<>();
    private AdView adView;
    private ImageView idbackarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found_details_actvity);
        toobarname = findViewById(R.id.toobarname);
        recylerview_lostandfounddetails = findViewById(R.id.recycleviewLostandFoundDetails);

        idbackarrow = findViewById(R.id.idbackarrow);

        getDataFromIntent();
        updateUI();

        MobileAds.initialize(getApplicationContext(),"ca-app-pub-8434077743160830~2037142306");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //fetchList=  getIntent().getExtras("LostItems");

        idbackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getDataFromIntent() {
        Intent intent = this.getIntent();
        if (intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            state = bundle.getString("State");
            fetchList = (List<Lostfound.Datum>) bundle.getSerializable("value");
        }
    }

    private void updateUI() {
        if (state.equalsIgnoreCase("lost")) {
            toobarname.setText("Lost Items");
            lostFoundResult(state);
        } else if (state.equalsIgnoreCase("found")) {
            toobarname.setText("Found Items");
            lostFoundResult(state);
        }

    }

    private void lostFoundResult(String state) {

        if (state.equalsIgnoreCase("found")) {
            for (int i = 0; i < fetchList.size(); i++) {
                if (fetchList.get(i).getItemType().equalsIgnoreCase("Found")) {
                    fetchFoundList.add(new Lostfound.Datum(fetchList.get(i).getRdItemId(),
                            fetchList.get(i).getTitle(), fetchList.get(i).getDescription(),
                            fetchList.get(i).getItemType(), fetchList.get(i).getItemDate(), fetchList.get(i).getDatetime()));
                }

            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recylerview_lostandfounddetails.setLayoutManager(layoutManager);
            loastandfoundDetailsAdapter = new LostAndFoundDetailsAdapter(fetchFoundList, getApplicationContext(), this);
            loastandfoundDetailsAdapter.setState(state);
            recylerview_lostandfounddetails.setAdapter(loastandfoundDetailsAdapter);
        } else {
            if (state.equalsIgnoreCase("lost")) {
                for (int i = 0; i < fetchList.size(); i++) {
                    if (fetchList.get(i).getItemType().equalsIgnoreCase("Lost")) {
                        fetchLostList.add(new Lostfound.Datum(fetchList.get(i).getRdItemId(),
                                fetchList.get(i).getTitle(), fetchList.get(i).getDescription(),
                                fetchList.get(i).getItemType(), fetchList.get(i).getItemDate(), fetchList.get(i).getDatetime()));
                    }

                }


                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                recylerview_lostandfounddetails.setLayoutManager(layoutManager);
                loastandfoundDetailsAdapter = new LostAndFoundDetailsAdapter(fetchLostList, getApplicationContext(), this);
                loastandfoundDetailsAdapter.setState(state);
                recylerview_lostandfounddetails.setAdapter(loastandfoundDetailsAdapter);

            }
        }

    }

    @Override
    public void getposition(int NewsItemposition) {

    }
}
