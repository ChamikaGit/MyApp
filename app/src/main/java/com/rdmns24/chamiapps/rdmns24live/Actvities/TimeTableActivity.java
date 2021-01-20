package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rdmns24.chamiapps.rdmns24live.R;

import java.sql.Time;

public class TimeTableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);



        //overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        temPopup();
    }

    private void temPopup() {

        final Dialog dialog = new Dialog(TimeTableActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom_timetabletest);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        TextView textView = dialog.findViewById(R.id.etNote);
        ImageView imageViewclose = dialog.findViewById(R.id.btnclose);
        Button btnVisitTimeTable = dialog.findViewById(R.id.btnVisitTimeTable);

//        textdetails = dataNotification.get(position).getNotificationDescription();
//        String header = dataNotification.get(position).getNotificationTitle();
//
//        textViewheader.setText(header);
//        textView.setText(textdetails);
        String texthoriv ="අන්තර්ජාලය නොමැතිව ක්\u200Dරියාත්මක වන මෙම විශේෂ දුම්රිය කාලසටහන තවමත් පරීක්ෂණ අදියරේ පවතින අතර නොබෝ දිනකින් මෙම සේවාව ඔබවෙත ලබාගැනීමට හැකිවනු ඇත.\n" +
                "\n" +
                "Contact - admin@rdmns.lk";
        textView.setText(texthoriv);


        imageViewclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(TimeTableActivity.this,MainActivity.class);
                startActivity(intent);
                TimeTableActivity.this.finish();
            }
        });


        btnVisitTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://eservices.railway.gov.lk"));
                startActivity(browserIntent);
            }
        });


        dialog.show();

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

    }



}
