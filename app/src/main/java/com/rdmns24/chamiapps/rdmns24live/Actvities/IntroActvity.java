package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.rdmns24.chamiapps.rdmns24live.Holders.SliderAdapter;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.rdmns24.chamiapps.rdmns24live.Sharedprefernces.Sharedprefernce;

public class IntroActvity extends AppCompatActivity {

    private LinearLayout linearLayoutdot;
    private ViewPager viewPagerslider;


    private TextView[] mdots;
    private SliderAdapter sliderAdapter;
    private Button back, next;

    private int currentpage;
    private Sharedprefernce sharedprefernce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_actvity);


        //chage statusbar iconcolors
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        }

        sharedprefernce = new Sharedprefernce();

        linearLayoutdot = findViewById(R.id.dotslayout);
        viewPagerslider = findViewById(R.id.sliderviewpager);
        sliderAdapter = new SliderAdapter(this);

        back = findViewById(R.id.btnrevious);
        next = findViewById(R.id.brnnext);

        viewPagerslider.setAdapter(sliderAdapter);


        adddotindicator(0);
        viewPagerslider.addOnPageChangeListener(viewlistner);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPagerslider.setCurrentItem(currentpage+1);


                if (currentpage==3){

                    String setval="1";


                    sharedprefernce.save(getApplicationContext(),setval);
                    Toast.makeText(getApplicationContext(),"Welcome To RDMNS.LK MobileAPP!",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(IntroActvity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    IntroActvity.this.finish();

                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPagerslider.setCurrentItem(currentpage-1);
            }
        });
    }

    private void adddotindicator(int position) {


        mdots = new TextView[4];
        linearLayoutdot.removeAllViews();

        for (int i = 0; i < mdots.length; i++) {

            mdots[i] = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.colortransparentwhite));


            linearLayoutdot.addView(mdots[i]);

        }
        if (mdots.length > 0) {

            mdots[position].setTextColor(getResources().getColor(R.color.colorwhite));
        }
    }

    ViewPager.OnPageChangeListener viewlistner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            adddotindicator(position);

            currentpage =position;

            if (position==0){


                next.setEnabled(true);
                back.setEnabled(false);
                back.setVisibility(View.INVISIBLE);

                next.setText("Next");
                back.setText("");


            }else if (position==mdots.length-1){



                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);

                next.setText("Finish");
                back.setText("Back");


            }else {


                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);

                next.setText("Next");
                back.setText("Back");


            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
