package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.onesignal.OneSignal;
import com.rdmns24.chamiapps.rdmns24live.Models.NotificationPost;
import com.rdmns24.chamiapps.rdmns24live.Models.Notificationstatus;
import com.rdmns24.chamiapps.rdmns24live.Models.TrainLines;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.rdmns24.chamiapps.rdmns24live.Services.API.Sync.Getrdmnspushnotificationsync;
import com.rdmns24.chamiapps.rdmns24live.Services.API.Sync.Postrdmnspushnotificationsync;
import com.rdmns24.chamiapps.rdmns24live.Sharedprefernces.Sharedprefernce;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class NotificationSettingsActivity extends AppCompatActivity implements View.OnClickListener, Getrdmnspushnotificationsync.getPushNotificationcallback, Postrdmnspushnotificationsync.getPostPushNotificationcallback {


    private TextView toobarname, tv1, tv2, tv3, tv4, tv5;
    private ImageView imgBackbtn;
    private SwitchCompat switch1, switch2, switch3, switch4, switch5;
    private Sharedprefernce sharedprefernce;
    private String ssw1, ssw2, ssw3, ssw4, ssw5;
    private AdView adView;
    private String oneSignalPlayerId;
    private Getrdmnspushnotificationsync getrdmnspushnotificationsync;
    private Postrdmnspushnotificationsync postrdmnspushnotificationsync;
    private List<TrainLines.DataBean> dataBeans;
    private TextView tvSubmit;
    private JSONObject tags;
    private JSONObject Posttags;
    private String line1 = "1", line2 = "1", line3 = "1", line4 = "1", line5 = "1";
    static AlertDialog alertDialog;

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
        tvSubmit = findViewById(R.id.tvSubmit);

        MobileAds.initialize(getApplicationContext());
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        switch4 = findViewById(R.id.switch4);
        switch5 = findViewById(R.id.switch5);

//        sharedprefernce = new Sharedprefernce();
//
//        ssw1 =sharedprefernce.getSwitch1(getApplicationContext());
//        ssw2 =sharedprefernce.getSwitch2(getApplicationContext());
//        ssw3 =sharedprefernce.getSwitch3(getApplicationContext());
//        ssw4 =sharedprefernce.getSwitch4(getApplicationContext());
//        ssw5 =sharedprefernce.getSwitch5(getApplicationContext());


        tv1.setSelected(true);
        tv2.setSelected(true);
        tv3.setSelected(true);
        tv4.setSelected(true);
        tv5.setSelected(true);
        imgBackbtn.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);

        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                Log.d("debug", "User:" + userId);
//                if (registrationId != null)
//                    Log.d("debug", "registrationId:" + registrationId);
                if (userId != null) {
                    oneSignalPlayerId = userId;
                } else {
                    oneSignalPlayerId = "";
                }

            }
        });

        checkNotification();

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    line1 = "1";
                } else {
                    line1 = "0";
                }

            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    line2 = "1";
                } else {
                    line2 = "0";
                }
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    line3 = "1";

                } else {
                    line3 = "0";
                }
            }
        });
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    line4 = "1";
                } else {
                    line4 = "0";
                }
            }
        });
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    line5 = "1";
                } else {
                    line5 = "0";
                }
            }
        });


    }

    private void checkNotification() {

        getrdmnspushnotificationsync = new Getrdmnspushnotificationsync(getApplicationContext(), dataBeans, oneSignalPlayerId, this);
        getrdmnspushnotificationsync.GetPushNotificationRetrofit();

//        if (sharedprefernce.getSwitch1(getApplicationContext())==null){
//            switch1.setChecked(false);
//        }else {
//            switch1.setChecked(true);
//        }
//        if (sharedprefernce.getSwitch2(getApplicationContext())==null){
//            switch2.setChecked(false);
//        }else {
//            switch2.setChecked(true);
//        }
//        if (sharedprefernce.getSwitch3(getApplicationContext())==null){
//            switch3.setChecked(false);
//        }else {
//            switch3.setChecked(true);
//        }
//        if (sharedprefernce.getSwitch4(getApplicationContext())==null){
//            switch4.setChecked(false);
//        }else {
//            switch4.setChecked(true);
//        }
//        if (sharedprefernce.getSwitch5(getApplicationContext())==null){
//            switch5.setChecked(false);
//        }else {
//            switch5.setChecked(true);
//        }

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

            case R.id.tvSubmit:
                sendTags();
                break;
        }
    }

    private void sendTags() {
        tags = new JSONObject();
        try {
            tags.put("one_line", line1);
            tags.put("two_line", line2);
            tags.put("three_line", line3);
            tags.put("four_line", line4);
            tags.put("five_line", line5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OneSignal.sendTags(tags);

        Posttags = new JSONObject();
        try {
            Posttags.put("player_id", oneSignalPlayerId);
            Posttags.put("one_line", line1);
            Posttags.put("two_line", line2);
            Posttags.put("three_line", line3);
            Posttags.put("four_line", line4);
            Posttags.put("five_line", line5);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        postrdmnspushnotificationsync = new Postrdmnspushnotificationsync(getApplicationContext(), Posttags, this);
        postrdmnspushnotificationsync.PostPushNotificationRetrofit();


    }

    @Override
    public void onPushNotification(boolean status, Notificationstatus response) {

        if (response != null) {
            String line_one = response.getOneLine();
            String line_two = response.getTwoLine();
            String line_three = response.getThreeLine();
            String line_four = response.getFourLine();
            String line_five = response.getFiveLine();

            if (line_one.equals("1")) {
                switch1.setChecked(true);
                line1 = "1";
            } else {
                switch1.setChecked(false);
                line1 = "0";
            }
            if (line_two.equals("1")) {
                switch2.setChecked(true);
                line2 = "1";
            } else {
                switch2.setChecked(false);
                line2 = "0";
            }
            if (line_three.equals("1")) {
                switch3.setChecked(true);
                line3 = "1";
            } else {
                switch3.setChecked(false);
                line3 = "0";
            }
            if (line_four.equals("1")) {
                switch4.setChecked(true);
                line4 = "1";
            } else {
                switch4.setChecked(false);
                line4 = "0";
            }
            if (line_five.equals("1")) {
                switch5.setChecked(true);
                line5 = "1";
            } else {
                switch5.setChecked(false);
                line5 = "0";
            }
        } else {
            sendTags();
        }


    }

    @Override
    public void onPostPushNotification(boolean status, NotificationPost response) {

        if (response.getMessage().equals("success_update")){
            showAlert(this,"");
        }

    }


    public void showAlert(Context context, String message) {
        if (context == null) {
            return;
        }
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context, android.R.style.Theme_DeviceDefault_Light_Dialog));
            LayoutInflater inflater = LayoutInflater.from(context);
            View alertView = inflater.inflate(R.layout.dilaog_sucess, null);
            alertDialogBuilder.setView(alertView);
            alertDialogBuilder.setCancelable(false);
            dismiss();
            alertDialog = alertDialogBuilder.create();
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
            lp.dimAmount = 0.5f;
            alertDialog.getWindow().setAttributes(lp);
            //TextView textViewTitle = alertView.findViewById(R.id.textViewTitle);
//            TextView textViewMessage = alertView.findViewById(R.id.textViewMessage);
//            textViewMessage.setText(message);
            TextView btnOk = alertView.findViewById(R.id.tvOK);
            ImageView imgClose = alertView.findViewById(R.id.btnclose);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    NotificationSettingsActivity.this.finish();
                }
            });
            btnOk.setVisibility(View.VISIBLE);
            imgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.show();
        } catch (Exception e) {
        }
    }

    public static void dismiss() {
        try {
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        } catch (Exception e) {
        }
    }

//    JSONObject tags = new JSONObject();
//	tags.put("key1", "value1");
//	tags.put("key2", "value2");
//	OneSignal.sendTags(tags);


}
