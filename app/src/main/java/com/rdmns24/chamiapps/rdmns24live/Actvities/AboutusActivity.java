package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.rdmns24.chamiapps.rdmns24live.R;

public class AboutusActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    WebView mWebView;

    Context mContext;

    private AdView madView;

    //SwipeRefreshLayout mswipeRefreshLayout;
   // private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        mProgressBar = findViewById(R.id.idprogessbar);
        mWebView = findViewById(R.id.idwebview);
       //mswipeRefreshLayout = findViewById(R.id.swipetorefersh);

        mContext = getApplicationContext();

        MobileAds.initialize(getApplicationContext(),"ca-app-pub-8434077743160830~2037142306");
        madView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        madView.loadAd(adRequest);


        renderWebPage("http://app.rdmns.lk/");

//        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                mWebView.reload();
//            }
//        });

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        mswipeRefreshLayout.getViewTreeObserver().addOnScrollChangedListener(mOnScrollChangedListener =
//                new ViewTreeObserver.OnScrollChangedListener() {
//                    @Override
//                    public void onScrollChanged() {
//                        if (mWebView.getScrollY() == 0)
//                            mswipeRefreshLayout.setEnabled(true);
//                        else
//                            mswipeRefreshLayout.setEnabled(false);
//
//                    }
//                });
//
//    }

    protected void renderWebPage(String urlToRender) {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Do something on page loading started
                // Visible the progressbar
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // Do something when page loading finished

            }

        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                // Update the progress bar with page loading progress
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    // Hide the progressbar
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        // Enable the javascript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // Render the web page
        mWebView.loadUrl(urlToRender);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }
}
