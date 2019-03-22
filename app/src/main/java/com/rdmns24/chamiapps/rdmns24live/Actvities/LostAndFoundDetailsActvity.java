package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rdmns24.chamiapps.rdmns24live.R;

public class LostAndFoundDetailsActvity extends AppCompatActivity {

    private TextView toobarname;
    private String state=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found_details_actvity);
        toobarname = findViewById(R.id.toobarname);

        state = "lost";
        updateUI();

    }

    private void updateUI() {
        if (state.equalsIgnoreCase("lost")){
            toobarname.setText("Lost Items");
        }else if (state.equalsIgnoreCase("found")){
            toobarname.setText("Found Items");
        }

    }
}
