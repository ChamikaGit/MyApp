package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rdmns24.chamiapps.rdmns24live.Ads.AdsUtils;
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
//    private AdView adView;
    private ImageView idbackarrow,idthreedots;
    private LinearLayout banner_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found_details_actvity);
        toobarname = findViewById(R.id.toobarname);
        recylerview_lostandfounddetails = findViewById(R.id.recycleviewLostandFoundDetails);

        idbackarrow = findViewById(R.id.idbackarrow);
        idthreedots = findViewById(R.id.idthreedots);
        banner_container = findViewById(R.id.banner_container);


        getDataFromIntent();
        updateUI();

        AdsUtils.adsShow(this,banner_container);
//        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//
//            }
//        });
//        adView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

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
        idthreedots.setVisibility(View.GONE);

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
            loastandfoundDetailsAdapter = new LostAndFoundDetailsAdapter(fetchFoundList, getApplicationContext(), this,this);
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
                loastandfoundDetailsAdapter = new LostAndFoundDetailsAdapter(fetchLostList, getApplicationContext(), this,this);
                loastandfoundDetailsAdapter.setState(state);
                recylerview_lostandfounddetails.setAdapter(loastandfoundDetailsAdapter);

            }
        }

    }

    @Override
    public void getposition(int NewsItemposition) {
    }

    @Override
    protected void onDestroy() {
        AdsUtils.adsDestroy();
        super.onDestroy();
    }
}
