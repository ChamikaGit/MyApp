package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.rdmns24.chamiapps.rdmns24live.Sharedprefernces.Sharedprefernce;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import hotchemi.android.rate.AppRate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private VideoView mvideoView;
    private ImageButton mImgbtnnewsfeed, mImgbtntimetable, mImgbtnNotification, mImgbtnsubmitnews, mImgbtnaboutus, mImgbtnlostandfound, mImgbtnrailwaysecurity, mImgbtncontactnumbers, mImgbtncomplain,mImgbtnRadar;
    View sbView;
    boolean misConnected;
    private Sharedprefernce sharedprefernce;
    private String shredval;
    private String shredvaldialog;
    private Context context;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//
//            }
//        });
//        AudienceNetworkAds.initialize(this);
        context = getApplicationContext();
        AppRate.with(this)
                .setInstallDays(1)
                .setLaunchTimes(3)
                .setRemindInterval(2)
                .monitor();
        AppRate.showRateDialogIfMeetsConditions(this);
        sharedprefernce = new Sharedprefernce();
        shredval = sharedprefernce.getval(getApplicationContext());
        shredvaldialog = sharedprefernce.getvaldialog(getApplicationContext());
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

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new NotificationOpenHandler())
                .init();

//        MediationTestSuite.launch(MainActivity.this);



        //                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)


        mvideoView = findViewById(R.id.id_mainimg);
        mImgbtnnewsfeed = findViewById(R.id.btnNewsfeed);
        mImgbtntimetable = findViewById(R.id.btnTimetable);
        mImgbtnNotification = findViewById(R.id.btnNotifcation);
        mImgbtnsubmitnews = findViewById(R.id.btnsubmitnews);
        mImgbtnaboutus = findViewById(R.id.btnaboutus);
        mImgbtnlostandfound = findViewById(R.id.btnlostandfound);
        mImgbtnrailwaysecurity = findViewById(R.id.btnrailwaysecurity);
        mImgbtncontactnumbers = findViewById(R.id.btncontactnumbers);
        mImgbtncomplain = findViewById(R.id.btncomplaints);
        mImgbtnRadar = findViewById(R.id.btnRadar);

        mImgbtnnewsfeed.setOnClickListener(this);
        mImgbtnNotification.setOnClickListener(this);
        mImgbtntimetable.setOnClickListener(this);
        mImgbtnsubmitnews.setOnClickListener(this);
        mImgbtnaboutus.setOnClickListener(this);
        mImgbtnlostandfound.setOnClickListener(this);
        mImgbtnrailwaysecurity.setOnClickListener(this);
        mImgbtncontactnumbers.setOnClickListener(this);
        mImgbtncomplain.setOnClickListener(this);
        mImgbtnRadar.setOnClickListener(this);


//        OneSignal.setSubscription(true);
        //videoplay();


    }

    public void videoplay() {


        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bannernew);

        mvideoView.setVideoURI(uri);
        mvideoView.start();
        mvideoView.setKeepScreenOn(true);

        mvideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);

            }
        });

    }

    @Override
    protected void onResume() {

        super.onResume();
        videoplay();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnNewsfeed:


                if (isOnline()) {
                    Intent intent = new Intent(MainActivity.this, NewsfeedActvity.class);
                    MainActivity.this.startActivity(intent);
//                Toast.makeText(getApplicationContext(), "News Feed clicked", Toast.LENGTH_LONG).show();
                } else {

                    snakbar();
                }
                break;

            case R.id.btnTimetable:

                Intent intenttimetable = new Intent(MainActivity.this, TimeTableActivity.class);
                MainActivity.this.startActivity(intenttimetable);

                break;

            case R.id.btnNotifcation:
                if (isOnline()) {
                    Intent intentNotification = new Intent(MainActivity.this, NotificationActivity.class);
                    MainActivity.this.startActivity(intentNotification);
                } else {
                    snakbar();
                }

                break;

            case R.id.btnsubmitnews:
                if (isOnline()) {


                    final Dialog dialog = new Dialog(MainActivity.this);
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

                } else {
                    snakbar();
                }

                break;


            case R.id.btnaboutus:
                if (isOnline()) {
                    Intent intentNotification = new Intent(MainActivity.this, AboutusActivity.class);
                    startActivity(intentNotification);
                } else {
                    snakbar();
                }

                break;

            case R.id.btnlostandfound:
                if (isOnline()) {
                    Intent intentNotification = new Intent(MainActivity.this, LostAndFoundActvity.class);
                    startActivity(intentNotification);
                } else {
                    snakbar();
                }

                break;

            case R.id.btnrailwaysecurity:

                temPopup();

                break;

            case R.id.btncontactnumbers:

                if (isOnline()) {
                    Intent intentContactNumber = new Intent(MainActivity.this, ContactNumberActvity.class);
                    startActivity(intentContactNumber);
                } else {
                    snakbar();
                }
                break;

            case R.id.btncomplaints:
                String url = "https://www.rdmns.lk/complaint";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.btnRadar:
                if (isOnline()) {
                    Intent intentTransTracking = new Intent(MainActivity.this, TrainTrackingActivity.class);
                    startActivity(intentTransTracking);
                } else {
                    snakbar();
                }
                break;
        }
    }


    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private void temPopup() {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom_railway_securtity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        TextView textView = dialog.findViewById(R.id.etNote);
        ImageView imageViewclose = dialog.findViewById(R.id.btnclose);
        Button btnVisitTimeTable = dialog.findViewById(R.id.btnVisitTimeTable);
        btnVisitTimeTable.setText("CALL NOW");

        imageViewclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });


        btnVisitTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "+94112336614"));
                startActivity(intent);
            }
        });


        dialog.show();

    }


    public void snakbar() {
        String message = "Sorry! Please Check Your internet Connection";
        int color = Color.RED;

        Snackbar snackbar = Snackbar.make(findViewById(R.id.fabb), message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_aboutus) {

            if (isOnline()) {
                Intent intentNotification = new Intent(MainActivity.this, AboutusActivity.class);
                startActivity(intentNotification);
            } else {
                snakbar();
            }

            return true;
        }
        if (id == R.id.action_Ratenow) {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("market://details?id=" + getPackageName()));
            startActivity(i);
            return true;
        }

        if (id == R.id.action_privacypolicy) {

            if (isOnline()) {
                String url = "http://rdmns.lk/privacy_policy_rdmns.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            } else {

                snakbar();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (shredval != null) {


        } else {

            Intent intent = new Intent(MainActivity.this, IntroActvity.class);
            startActivity(intent);

        }


    }

    @Override
    public void onBackPressed() {

        if (shredvaldialog == null) {


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("මෙම සේවාව වැඩිදියුණු කිරීම උදෙසා දුම්රියේ ගමන් කරන ඔබ සියලු මිතුරන් මෙම සමූහය වෙත එකතු කරගන්න")
                    .setCancelable(false)
                    .setPositiveButton("Join Us Now", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            String val = "1";

                            sharedprefernce.savedialog(getApplicationContext(), val);


                            Intent intent = null;
                            String Facebook = "https://www.facebook.com/groups/RDMNS/";
                            try {
                                getPackageManager().getPackageInfo("com.facebook.android", 0);
                                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Facebook));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            } catch (Exception e) {

                                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Facebook));
                            }
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Thank you for the response !", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            MainActivity.this.finish();

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        } else if (shredvaldialog != null) {

            MainActivity.this.finish();


        }
    }

    private class NotificationOpenHandler implements OneSignal.NotificationOpenedHandler {
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {


            if (result != null) {

//                String title = result.notification.payload.title;
//                String description = result.notification.payload.body;
//                String Imageurl = result.notification.payload.bigPicture;
//
//
//                dialog = new Dialog(MainActivity.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.dialog_custom_notification);
//                dialog.setCanceledOnTouchOutside(false);
//                dialog.setCancelable(false);
//                TextView textView = dialog.findViewById(R.id.etNote);
//                TextView notificationtext = dialog.findViewById(R.id.idnotificationtext);
//                ImageView imageViewclose = dialog.findViewById(R.id.btnclose);
//                ImageView imageViewnotiimage = dialog.findViewById(R.id.notificationimg);
//                Button button = dialog.findViewById(R.id.btndilogyes);
//
//                imageViewnotiimage.setVisibility(View.VISIBLE);
//
//                textView.setText(description);
//                notificationtext.setText(title);
//                if (Imageurl != null) {
//                    Picasso.with(MainActivity.this).load(Imageurl).placeholder(R.drawable.icon).into(imageViewnotiimage);
//                } else {
//
//                    imageViewnotiimage.setVisibility(View.GONE);
//                }
//
//
//                imageViewclose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
//                        startActivity(intent);
//                    }
//                });
//
//
//                dialog.show();

            }
        }
    }


//    class NotificationOpenHandler implements OneSignal.NotificationOpenedHandler {
//        // This fires when a notification is opened by tapping on it.
//        @Override
//        public void notificationOpened(OSNotificationOpenResult result) {
//            JSONObject data = result.notification.payload.additionalData;
//
//            String stationName = data.optString("stationName");
//            String timestamp = data.optString("timestamp");
//            String filename = data.optString("filename");
//            // String url = getString(R.string.callResourceUrl) + filename;
//
//            Log.d("APP", "Notification clicked");
//
//            Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
//            intent.putExtra("stationName", stationName);
//            intent.putExtra("time", timestamp);
//            //   intent.putExtra("url", url);
//            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
//    }


}
