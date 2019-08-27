package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.rdmns24.chamiapps.rdmns24live.Holders.TraineLineNotificationAdapter;
import com.rdmns24.chamiapps.rdmns24live.Models.TraineLinesNotifications;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.rdmns24.chamiapps.rdmns24live.Services.API.Sync.Getrdmnsnotificationsync;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationDetailsActvity extends AppCompatActivity implements Getrdmnsnotificationsync.getTrainlinenoticallback, TraineLineNotificationAdapter.Getpostion {


    private TraineLineNotificationAdapter traineLineNotificationAdapter;
    private Getrdmnsnotificationsync getrdmnsnotificationsync;
    private List<TraineLinesNotifications.DataBean> dataNotification = new ArrayList<>();
    private RecyclerView recyclerViewNotificaion;
    private ProgressBar progressBar;
    public String steDate, month, year, SystemTime;
    CountDownTimer newtimer;
    TextView mtvTime, tv_date, tv_monthhandyear, tvyear;
    private Typeface typefacetime;
    private TextView textViewtoobarname;
    private ImageView imageViewarrow;

    private String textdetails;
    private AdView madView;
//    private InterstitialAd minterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details_actvity);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

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

        recyclerViewNotificaion = findViewById(R.id.recycleviewNotificationDetails);
        progressBar = findViewById(R.id.pbProgressbarNotification);
        mtvTime = findViewById(R.id.tvtime);
        tv_date = findViewById(R.id.tvdate);
        tv_monthhandyear = findViewById(R.id.tvmnth);
        tvyear = findViewById(R.id.tvyear);
        textViewtoobarname = findViewById(R.id.toobarname);
        imageViewarrow = findViewById(R.id.idbackarrow);


        //minterstitialAd = new InterstitialAd(this);
        // MobileAds.initialize(getApplicationContext(), "ca-app-pub-8434077743160830~2037142306");
        madView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        madView.loadAd(adRequest);

        Bundle bundle = getIntent().getExtras();
        String trainelineID = bundle.getString("traineid");
        String trainelinename = bundle.getString("trainlinename");

        textViewtoobarname.setText(trainelinename);

        imageViewarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(NotificationDetailsActvity.this, NotificationActivity.class);
//                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
            }
        });

        progressBar.setVisibility(View.VISIBLE);

        timeSet();
        //LoadJson
        loadJSON(trainelineID);
        initView();

        typefacetime = Typeface.createFromAsset(getAssets(), "fonts/lcdfont.ttf");
        mtvTime.setTypeface(typefacetime);
    }


    private void loadJSON(String traineid) {


        getrdmnsnotificationsync = new Getrdmnsnotificationsync(getApplicationContext(), dataNotification, this);
        getrdmnsnotificationsync.NotiifcationRetrofit(traineid);
    }

    public void initView() {


        int resId = R.anim.layout_animation_slide_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerViewNotificaion.setLayoutAnimation(animation);

        recyclerViewNotificaion.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewNotificaion.setLayoutManager(layoutManager);


    }

    private void timeSet() {


        final Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd ");
        SimpleDateFormat mdformat2 = new SimpleDateFormat("MMMM");
        SimpleDateFormat mdformate3 = new SimpleDateFormat("yyyy");
        final SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm a");
        steDate = mdformat.format(calendar.getTime());
        month = mdformat2.format(calendar.getTime());
        year = mdformate3.format(calendar.getTime());

        final int a = calendar.get(Calendar.AM_PM);

        newtimer = new CountDownTimer(1000000000, 1000) {

            public void onTick(long millisUntilFinished) {
                Calendar c = Calendar.getInstance();

                SystemTime = simpleDateFormat1.format(c.getTime());

                mtvTime.setText(SystemTime);
//                if (a == Calendar.AM) {
//                    mtvTime.setText(SystemTime + " " + "AM");
//                } else {
//
//                    mtvTime.setText(SystemTime + " " + "PM");
//                }
            }

            public void onFinish() {

            }
        };
        newtimer.start();

        tv_date.setText(steDate);
        tv_monthhandyear.setText(month);
        tvyear.setText(year);
    }

    @Override
    public void ontrainelinesnotifound(boolean status, List<TraineLinesNotifications.DataBean> response) {


        if (response != null) {


            dataNotification = response;

            Log.e("LineNotification", response.get(0).getNotificationDescription());
            traineLineNotificationAdapter = new TraineLineNotificationAdapter(response, getApplicationContext(), this);
            recyclerViewNotificaion.setAdapter(traineLineNotificationAdapter);
            initView();
            progressBar.setVisibility(View.GONE);


        } else {
            Toast.makeText(getApplicationContext(), "Can't Connect with API", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void getposition(int NewsItemposition) {

        String a = dataNotification.get(NewsItemposition).getNotificationUrl();

        if (!a.isEmpty()) {
            Uri uri = Uri.parse(a);

            if (!a.startsWith("http://") && !a.startsWith("https://")) {
                uri = Uri.parse("http://" + a);
            }


            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } else {

            Toast.makeText(getApplicationContext(), "No URL..!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void getpositionnewsdetails(int position) {

//
//        minterstitialAd.setAdUnitId("ca-app-pub-8434077743160830/7053187305");
//        final AdRequest adRequest = new AdRequest.Builder().build();
//        minterstitialAd.loadAd(adRequest);
//
//
//        minterstitialAd.setAdListener(new AdListener() {
//            public void onAdLoaded() {
//                showInstiial();
//            }
//        });


        final Dialog dialog = new Dialog(NotificationDetailsActvity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom_notificationdetails);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        TextView textView = dialog.findViewById(R.id.etNote);
        TextView textViewheader = dialog.findViewById(R.id.idnotificationtext);
        ImageView imageViewclose = dialog.findViewById(R.id.btnclose);


        textdetails = dataNotification.get(position).getNotificationDescription();
        String header = dataNotification.get(position).getNotificationTitle();

        textViewheader.setText(header);
        textView.setText(textdetails);

        imageViewclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }


//    private void showInstiial() {
//
//        if (minterstitialAd.isLoaded()) {
//
//            minterstitialAd.show();
//
//        }
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }
}
