package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.rdmns24.chamiapps.rdmns24live.Holders.TraineLineAdpter;
import com.rdmns24.chamiapps.rdmns24live.Models.TrainLines;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.rdmns24.chamiapps.rdmns24live.Services.API.Sync.Getrdmnstrainlinesync;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity implements Getrdmnstrainlinesync.getTrainlinecallback, TraineLineAdpter.mGetpostion {

    private TraineLineAdpter traineLineAdpter;
    private Getrdmnstrainlinesync getrdmnstrainlinesync;
    private List<TrainLines.DataBean> dataBeans;
    private List<TrainLines.DataBean> dataLine = new ArrayList<>();
    private RecyclerView recyclerViewtrain;
    private ProgressBar progressBar;
    private AdView madView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//
//        //chage statusbar iconcolors
//        if (android.os.Build.VERSION.SDK_INT >= 21) {
//            Window window = this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
//
//            View decor = getWindow().getDecorView();
//            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//
//
//        }

        recyclerViewtrain = findViewById(R.id.recycleviewtrainList);
        progressBar = findViewById(R.id.pbProgressbarTrainline);

        progressBar.setVisibility(View.VISIBLE);


        MobileAds.initialize(getApplicationContext(),"ca-app-pub-8434077743160830~2037142306");
        madView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        madView.loadAd(adRequest);

        loadJSON();
        initView();
    }

    private void loadJSON() {


        getrdmnstrainlinesync = new Getrdmnstrainlinesync(getApplicationContext(), dataBeans, this);
        getrdmnstrainlinesync.LinesRetrofit();
    }

    public void initView() {


        recyclerViewtrain.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewtrain.setLayoutManager(layoutManager);


    }

    @Override
    public void ontrainelinesfeedfound(boolean status, List<TrainLines.DataBean> response) {
        if (response != null) {
            dataLine = response;
            Log.e("Line", response.get(0).getTrainLineSinhala());
            traineLineAdpter = new TraineLineAdpter(response, getApplicationContext(), this);
            recyclerViewtrain.setAdapter(traineLineAdpter);
            initView();
            progressBar.setVisibility(View.GONE);
        } else {
            Toast.makeText(getApplicationContext(), "Can't Connect with API", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void getposition(int NewsItemposition) {

        Intent intent = new Intent(NotificationActivity.this, NotificationDetailsActvity.class);

        String traineid = dataLine.get(NewsItemposition).getTrainLineId();
        String trainlinename = dataLine.get(NewsItemposition).getTrainLineSinhala();

        Bundle bundle = new Bundle();
        bundle.putString("traineid",traineid);
        bundle.putString("trainlinename",trainlinename);
        intent.putExtras(bundle);
        NotificationActivity.this.startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

    }
}
