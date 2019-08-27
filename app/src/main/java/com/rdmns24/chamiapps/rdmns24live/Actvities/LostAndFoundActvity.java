package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.rdmns24.chamiapps.rdmns24live.Holders.LoastandfoundAdapter;
import com.rdmns24.chamiapps.rdmns24live.Holders.NewsfeedAdapter_horizontall;
import com.rdmns24.chamiapps.rdmns24live.Models.Lostfound;
import com.rdmns24.chamiapps.rdmns24live.Models.LostfoundItem;
import com.rdmns24.chamiapps.rdmns24live.Models.NewsfeedRecent;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.rdmns24.chamiapps.rdmns24live.Services.API.Sync.Getrdmnslostfoundsync;
import com.rdmns24.chamiapps.rdmns24live.Services.API.Sync.Getrdmnsnewsrecentsync;
import com.rdmns24.chamiapps.rdmns24live.Services.API.Sync.Getrdmnsnewssync;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LostAndFoundActvity extends AppCompatActivity implements Getrdmnslostfoundsync.getLostFoundCallback,LoastandfoundAdapter.GetLoastItemPosition {

    private TextView toobarname;
    private Getrdmnslostfoundsync getrdmnslostfoundsync;
    private List<Lostfound.Datum> dataBeansrecent = new ArrayList<>();
    //private ArrayList<String> loastList =new ArrayList<>();


    private RecyclerView recylerview_lostfound;
    private LoastandfoundAdapter loastandfoundAdapter;
    private ArrayList<LostfoundItem> lostfoundItemArrayList;
    private ProgressBar pbLostFound;
    private ImageView idbackarrow,idthreedots,imgheader;
    private AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found_actvity);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        toobarname = findViewById(R.id.toobarname);
        recylerview_lostfound = findViewById(R.id.recylerview_lostfound);
        pbLostFound = findViewById(R.id.pbProgressbarNotification);
        idbackarrow = findViewById(R.id.idbackarrow);
        idthreedots = findViewById(R.id.idthreedots);
        imgheader = findViewById(R.id.imgheader);


        pbLostFound.setVisibility(View.VISIBLE);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(getApplicationContext(),"ca-app-pub-8434077743160830~2037142306");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        loadJSON();

        idbackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadJSON() {
        lostfoundItemArrayList = new ArrayList<>();
        lostfoundItemArrayList.add(new LostfoundItem(1,"LOST ITEMS","නැතිවූ භාණ්ඩ"));
        lostfoundItemArrayList.add(new LostfoundItem(2,"FOUND ITEMS","හමුවූ භාණ්ඩ"));
        lostfoundItemArrayList.add(new LostfoundItem(3,"SUBMIT LOST ITEMS","නැතිවූ/හමුවූ භාණ්ඩ පිලිබඳ තොරතුරු ලබාදීම"));
        lostfoundItemArrayList.add(new LostfoundItem(4,"INSTRUCTIONS","තොරතුරු ලබාදීමට උපදෙස්"));

        getrdmnslostfoundsync = new Getrdmnslostfoundsync(getApplicationContext(), dataBeansrecent, this);
        getrdmnslostfoundsync.lostFoundRetrofit();
        updateUI();
    }

    private void updateUI() {
        idthreedots.setVisibility(View.GONE);
        toobarname.setText("Lost And Found");
        String url ="http://rdmns.lk/adv_img/slider_1.png";
        Picasso.with(this).load(url).into(imgheader);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recylerview_lostfound.setLayoutManager(layoutManager);
        loastandfoundAdapter = new LoastandfoundAdapter(getApplicationContext(), lostfoundItemArrayList, this);
        recylerview_lostfound.setAdapter(loastandfoundAdapter);


    }

    @Override
    public void onLostFoundRecentFound(boolean status, List<Lostfound.Datum> response) {

        if (response != null) {
            dataBeansrecent=response;
//            adapterHorizontall = new NewsfeedAdapter_horizontall(response, getApplicationContext(), this);
//            recyclerViewhorizonlall.setAdapter(adapterHorizontall);
//            initView_Horizoll();
//            progressbarhori.setVisibility(View.GONE);

            pbLostFound.setVisibility(View.GONE);

            for (int i=0;i<dataBeansrecent.size();i++){

                if (dataBeansrecent.get(i).getItemType().equalsIgnoreCase("Lost")) {
                    Log.e("Tag lost", dataBeansrecent.get(i).getTitle());

                }else {
                    Log.e("Tag found", dataBeansrecent.get(i).getTitle());
                }
            }

        } else {
            Toast.makeText(getApplicationContext(), "Can't Connect with API", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

    }

    @Override
    public void getposition(int LostItemposition) {

        if (LostItemposition==0){
            Bundle bundle = new Bundle();
            Intent intent = new Intent(LostAndFoundActvity.this,LostAndFoundDetailsActvity.class);
            bundle.putSerializable("value", (Serializable) dataBeansrecent);
            bundle.putString("State","lost");
            intent.putExtras(bundle);
            //intent.putExtra("LostItems", (Serializable) dataBeansrecent);
            startActivity(intent);
        }
        if (LostItemposition==1){

            Bundle bundle = new Bundle();
            Intent intent = new Intent(LostAndFoundActvity.this,LostAndFoundDetailsActvity.class);
            bundle.putSerializable("value", (Serializable) dataBeansrecent);
            bundle.putString("State","found");
            intent.putExtras(bundle);
            //intent.putExtra("LostItems", (Serializable) dataBeansrecent);
            startActivity(intent);
        }
        if (LostItemposition==2){



            final Dialog dialog = new Dialog(LostAndFoundActvity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_custom_sendmail);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            Button dialogButtonyes = dialog.findViewById(R.id.btndilogyes);
            ImageView dilogButtonNo = dialog.findViewById(R.id.btnclose);
            final EditText editetextsubject = dialog.findViewById(R.id.etsubject);
            final EditText editextcompasemail = dialog.findViewById(R.id.etcomposeemail);


            dialogButtonyes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String textone = editetextsubject.getText().toString().trim();
                    String texttwo = editextcompasemail.getText().toString().trim();

                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "news@rdmns.lk", null));
                    intent.putExtra(Intent.EXTRA_SUBJECT, textone);
                    intent.putExtra(Intent.EXTRA_TEXT, texttwo);
                    startActivity(Intent.createChooser(intent, "Choose an Email client :"));


                }
            });

            dilogButtonNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();


                }
            });

            dialog.show();
        }
        else if (LostItemposition==3){

           // Toast.makeText(getApplicationContext(),"4",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LostAndFoundActvity.this,InstructionActvity.class);
            startActivity(intent);
        }



    }
}
