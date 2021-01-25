package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.rdmns24.chamiapps.rdmns24live.Holders.NewsfeedAdapter;
import com.rdmns24.chamiapps.rdmns24live.Holders.NewsfeedAdapter_horizontall;
import com.rdmns24.chamiapps.rdmns24live.Models.Newsfeed;
import com.rdmns24.chamiapps.rdmns24live.Models.NewsfeedRecent;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.rdmns24.chamiapps.rdmns24live.Services.API.Sync.Getrdmnsnewsrecentsync;
import com.rdmns24.chamiapps.rdmns24live.Services.API.Sync.Getrdmnsnewssync;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsfeedActvity extends AppCompatActivity implements Getrdmnsnewssync.getNewsfeedcallback, NewsfeedAdapter.Getpostion, NewsfeedAdapter_horizontall.mGetpostion, Getrdmnsnewsrecentsync.getNewsfeedrecentcallback {

    private Getrdmnsnewssync getrdmnsnewssync;
    private Getrdmnsnewsrecentsync getrdmnsnewsrecentsync;
    private List<Newsfeed.DataBean> dataBeanList;
    private List<NewsfeedRecent.DataBean> dataBeansrecent = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewhorizonlall;
    private NewsfeedAdapter newsfeedAdapter;
    private NewsfeedAdapter_horizontall adapterHorizontall;
    private ProgressBar progressBarvertcal, progressbarhori;
    private TextView textViewshowall;
    private ImageView imageViewthreedots;

    private List<Newsfeed.DataBean> data;

    Handler handler;

    private AdView madView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed_actvity);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);


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

        recyclerView = (RecyclerView) findViewById(R.id.recycleAll_news);
        recyclerViewhorizonlall = (RecyclerView) findViewById(R.id.recycleRecent_news);
        progressBarvertcal = (ProgressBar) findViewById(R.id.pbProgressbar);
        progressbarhori = (ProgressBar) findViewById(R.id.pbProgressbarhori);
        textViewshowall = findViewById(R.id.idshowall);
        imageViewthreedots = findViewById(R.id.idthreedots);



        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        madView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        madView.loadAd(adRequest);

        progressBarvertcal.setVisibility(View.VISIBLE);
        progressbarhori.setVisibility(View.VISIBLE);

        textViewshowall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsfeedActvity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        imageViewthreedots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(NewsfeedActvity.this, imageViewthreedots);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_threedotsnewsfeed);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_aboutus:


                                Intent intentNotification = new Intent(NewsfeedActvity.this, AboutusActivity.class);
                                startActivity(intentNotification);
//                                Toast.makeText(getApplicationContext(),"Aboutus clicked",Toast.LENGTH_LONG).show();

                                break;

                            case R.id.action_Ratenow:


                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("market://details?id=" + getPackageName()));
                                startActivity(i);
//                                Toast.makeText(getApplicationContext(),"Ratenow clicked",Toast.LENGTH_LONG).show();
                                break;

                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();
            }
        });


        loadJSON();
        initView();
        //runAnimation(recyclerView,0);
        initView_Horizoll();

        handler = new Handler();

        //  this.handler.postDelayed(m_Runnable,5000);


    }
//
//    private void runAnimation(RecyclerView recyclerView, int type) {
//
//        Context context = recyclerView.getContext();
//        LayoutAnimationController controller = null;
//
//        if (type==0){
//
//            controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_slide_right);
//
//            recyclerView.setLayoutAnimation(controller);
//            recyclerView.getAdapter().notifyDataSetChanged();
//            recyclerView.scheduleLayoutAnimation();
//        }
//    }

    public void initView() {


        int resId = R.anim.layout_animation_slide_right;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerView.setLayoutAnimation(animation);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


    }


    public void initView_Horizoll() {


        int resId = R.anim.layout_animation_slide_right;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerViewhorizonlall.setLayoutAnimation(animation);

        recyclerViewhorizonlall.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewhorizonlall.setLayoutManager(layoutManager);


    }


    //callretrofit-API
    public void loadJSON() {
        getrdmnsnewssync = new Getrdmnsnewssync(getApplicationContext(), dataBeanList, this);
        getrdmnsnewssync.newsRetrofit();

        getrdmnsnewsrecentsync = new Getrdmnsnewsrecentsync(getApplicationContext(), dataBeansrecent, this);
        getrdmnsnewsrecentsync.newsrecentRetrofit();


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

    }

//    private final Runnable m_Runnable = new Runnable() {
//
//        int count=0;
//
//        public void run() {
//
//
//
//
//            if(count<adapterHorizontall.getItemCount()){
//                // Toast.makeText(MainActivity.this, "in runnable", Toast.LENGTH_SHORT).show();
//                recyclerViewhorizonlall.smoothScrollToPosition(count);
//
//            }else {
//
//               Toast.makeText(getApplicationContext(),"ooops",Toast.LENGTH_LONG).show();
//            }
//            count++;
//
//            NewsfeedActvity.this.handler.postDelayed(m_Runnable, 5000);
//        }
//
//
//    };

    @Override
    public void onnewsfeedfound(boolean status, List<Newsfeed.DataBean> response) {


        if (response != null) {

            Log.e("API", response.get(0).getPostDescription());

            data = response;


            newsfeedAdapter = new NewsfeedAdapter(response, getApplicationContext(), this);
            recyclerView.setAdapter(newsfeedAdapter);


            initView();

            progressBarvertcal.setVisibility(View.GONE);

        } else {


            Toast.makeText(getApplicationContext(), "Can't Connect with API", Toast.LENGTH_LONG).show();
        }

//        recyclerViewhorizonlall.post(new Runnable() {
//            @Override
//            public void run() {
//                // Call smooth scroll
//                recyclerViewhorizonlall.smoothScrollToPosition(adapterHorizontall.getItemCount());
//            }
//        });

    }




    @Override
    public void getposition(int NewsItemposition) {


        Intent intent = new Intent(NewsfeedActvity.this, NewsfeeddetailsActvity.class);

        String newsTitle = data.get(NewsItemposition).getPostTitle();
        String newsDescription = data.get(NewsItemposition).getPostDescription();
        String newsImageUrl = data.get(NewsItemposition).getPostImage();
        String newsurl =data.get(NewsItemposition).getPostUrl();
//        Toast.makeText(getApplicationContext(),newsDescription,Toast.LENGTH_LONG).show();

        Bundle bundle = new Bundle();
        bundle.putString("TitleText", newsTitle);
        bundle.putString("DescriptionText", newsDescription);
        bundle.putString("ImageURL", newsImageUrl);
        bundle.putString("newsURL",newsurl);


        intent.putExtras(bundle);
        NewsfeedActvity.this.startActivity(intent);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_newsfeed, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_aboutus) {
//
//
//            return true;
//        }
//        if (id == R.id.action_Ratenow) {
//
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onnewsfeedrecentfound(boolean status, List<NewsfeedRecent.DataBean> response) {



        if (response != null) {
            dataBeansrecent=response;
            adapterHorizontall = new NewsfeedAdapter_horizontall(response, getApplicationContext(), this);
            recyclerViewhorizonlall.setAdapter(adapterHorizontall);
            initView_Horizoll();
            progressbarhori.setVisibility(View.GONE);

        } else {

            Toast.makeText(getApplicationContext(), "Can't Connect with API", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void getpositionhorizontall(int NewsItemposition) {

        final Dialog dialog = new Dialog(NewsfeedActvity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom_notificationdetails_horiz);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        TextView tvNote = dialog.findViewById(R.id.etNote);
        TextView tvTime = dialog.findViewById(R.id.etTime);
        TextView tvTimeAndDate = dialog.findViewById(R.id.idTimeandDate);

        ImageView imageViewclose = dialog.findViewById(R.id.btnclose);

        String texthoriv = dataBeansrecent.get(NewsItemposition).getNotificationDescription();
        String textTimeDate = dataBeansrecent.get(NewsItemposition).getCreatedDatetime();

//        textdetails = dataNotification.get(position).getNotificationDescription();
//        String header = dataNotification.get(position).getNotificationTitle();
//
//        textViewheader.setText(header);
//        textView.setText(textdetails);
        tvNote.setText(texthoriv);
        tvTimeAndDate.setText(textTimeDate);

        String giventime =dataBeansrecent.get(NewsItemposition).getCreatedDatetime();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date mdate =simpleDateFormat.parse(giventime);
            long timeinmillseconds = mdate.getTime();
            String textago = TimeAgo.using(timeinmillseconds);
            tvTime.setText(textago);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        imageViewclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }
}
