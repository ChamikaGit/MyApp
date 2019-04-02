package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.onesignal.OneSignal;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.rdmns24.chamiapps.rdmns24live.Sharedprefernces.Sharedprefernce;

public class NotificationSettingsActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView toobarname, tv1, tv2, tv3, tv4, tv5;
    private ImageView imgBackbtn;
    private SwitchCompat switch1, switch2, switch3, switch4, switch5;
    private Sharedprefernce sharedprefernce;
    private String ssw1,ssw2,ssw3,ssw4,ssw5;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);

        toobarname = findViewById(R.id.toobarname);
        imgBackbtn = findViewById(R.id.idbackarrow);
        toobarname.setText("Notification Settings");
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);

        MobileAds.initialize(getApplicationContext(),"ca-app-pub-8434077743160830~2037142306");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        switch4 = findViewById(R.id.switch4);
        switch5 = findViewById(R.id.switch5);

        sharedprefernce = new Sharedprefernce();

        ssw1 =sharedprefernce.getSwitch1(getApplicationContext());
        ssw2 =sharedprefernce.getSwitch2(getApplicationContext());
        ssw3 =sharedprefernce.getSwitch3(getApplicationContext());
        ssw4 =sharedprefernce.getSwitch4(getApplicationContext());
        ssw5 =sharedprefernce.getSwitch5(getApplicationContext());


        tv1.setSelected(true);
        tv2.setSelected(true);
        tv3.setSelected(true);
        tv4.setSelected(true);
        tv5.setSelected(true);
        imgBackbtn.setOnClickListener(this);

        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                Log.d("debug", "User:" + userId);
//                if (registrationId != null)
//                    Log.d("debug", "registrationId:" + registrationId);

            }
        });

        checkNotification();

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                    sharedprefernce.notificationSwitch1(getApplicationContext(),"1");
                    OneSignal.sendTag("key1","yes");
                } else {

                    Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
                    sharedprefernce.notificationSwitch1(getApplicationContext(),null);
                    OneSignal.sendTag("key1","no");
                }

            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                    sharedprefernce.notificationSwitch2(getApplicationContext(),"2");
                } else {

                    Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
                    sharedprefernce.notificationSwitch2(getApplicationContext(),null);
                }
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                    sharedprefernce.notificationSwitch3(getApplicationContext(),"3");
                } else {

                    Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
                    sharedprefernce.notificationSwitch3(getApplicationContext(),null);
                }
            }
        });
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                    sharedprefernce.notificationSwitch4(getApplicationContext(),"4");
                } else {

                    Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
                    sharedprefernce.notificationSwitch4(getApplicationContext(),null);
                }
            }
        });
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                    sharedprefernce.notificationSwitch5(getApplicationContext(),"5");
                } else {
                    Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
                    sharedprefernce.notificationSwitch5(getApplicationContext(),null);
                }
            }
        });


    }

    private void checkNotification() {
        if (sharedprefernce.getSwitch1(getApplicationContext())==null){
            switch1.setChecked(false);
        }else {
            switch1.setChecked(true);
        }
        if (sharedprefernce.getSwitch2(getApplicationContext())==null){
            switch2.setChecked(false);
        }else {
            switch2.setChecked(true);
        }
        if (sharedprefernce.getSwitch3(getApplicationContext())==null){
            switch3.setChecked(false);
        }else {
            switch3.setChecked(true);
        }
        if (sharedprefernce.getSwitch4(getApplicationContext())==null){
            switch4.setChecked(false);
        }else {
            switch4.setChecked(true);
        }
        if (sharedprefernce.getSwitch5(getApplicationContext())==null){
            switch5.setChecked(false);
        }else {
            switch5.setChecked(true);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.idbackarrow:
                finish();
                break;
        }
    }

//    JSONObject tags = new JSONObject();
//	tags.put("key1", "value1");
//	tags.put("key2", "value2");
//	OneSignal.sendTags(tags);


}
