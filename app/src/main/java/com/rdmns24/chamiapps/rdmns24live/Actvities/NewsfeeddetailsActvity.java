package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.rdmns24.chamiapps.rdmns24live.Helpers.Consts;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.squareup.picasso.Picasso;

public class NewsfeeddetailsActvity extends AppCompatActivity {

    private TextView tvTitle, tvDescription;
    private ImageView imgPoster;
    private TextView textViewtoolbarname;
    ImageView imageViewbackarrow;


    private AdView madView;
    Bundle bundle;

    private InterstitialAd minterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeeddetails_actvity);

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
        bundle = getIntent().getExtras();

        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

        tvTitle = findViewById(R.id.idnewstitle);
        tvDescription = findViewById(R.id.idnewdescription);
        imgPoster = findViewById(R.id.idNewsImage);
        textViewtoolbarname = findViewById(R.id.toobnewsdetailsname);
        imageViewbackarrow = findViewById(R.id.idnewsdetailsbackarrow);

//        minterstitialAd = new InterstitialAd(this);
//        minterstitialAd.setAdUnitId("ca-app-pub-8434077743160830/7053187305");

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8434077743160830~2037142306");
        madView = findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().build();
        madView.loadAd(adRequest);
        //minterstitialAd.loadAd(adRequest);

//        minterstitialAd.setAdListener(new AdListener() {
//            public void onAdLoaded() {
//                showInstiial();
//            }
//        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newsurl = bundle.getString("newsURL");
//
//                if (newsurl!=null) {
//
//
//                    Uri uri = Uri.parse(newsurl);
//
//
//                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
//                }else {
//
//                    Toast.makeText(getApplicationContext(),"Something Wrong !",Toast.LENGTH_LONG).show();
//                }


                if (newsurl != null) {

                    //Toast.makeText(Detailspost.this,"shareclick",Toast.LENGTH_LONG).show();

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, newsurl + "\n" + "www.rdmns.lk\n" +
                            "\n" +
                            "- Sri Lanka's Largest and No 01 on Trending Railway Community Network -");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);


                } else {

                    Toast.makeText(NewsfeeddetailsActvity.this, "Something Wrong !", Toast.LENGTH_LONG).show();


                }


            }
        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageViewbackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsfeeddetailsActvity.this, NewsfeedActvity.class);
                startActivity(intent);
            }
        });


        setLayout();


    }

//    private void showInstiial() {
//
//        if (minterstitialAd.isLoaded()){
//
//            minterstitialAd.show();
//
//        }
//    }

    public void setLayout() {


        String textTitle = bundle.getString("TitleText");
        String textDescription = bundle.getString("DescriptionText");
        String imageURL = bundle.getString("ImageURL");


        tvTitle.setText(textTitle);
        tvDescription.setText(textDescription);
        textViewtoolbarname.setText(textTitle);
        Picasso.with(getApplicationContext()).load(Consts.IMAGE_PATH + imageURL).placeholder(R.drawable.icon).into(imgPoster);

//        Toast.makeText(getApplicationContext(),textTitle,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

    }

}
