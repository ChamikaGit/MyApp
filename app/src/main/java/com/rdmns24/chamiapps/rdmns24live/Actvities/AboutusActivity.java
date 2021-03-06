package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.rdmns24.chamiapps.rdmns24live.Ads.AdsUtils;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.rdmns24.chamiapps.rdmns24live.widget.CustomProgressDialog;

public class AboutusActivity extends AppCompatActivity {

//    ProgressBar mProgressBar;
    private CustomProgressDialog progressDialog;
    WebView mWebView;

    Context mContext;

//    private AdView madView;
    private LinearLayout banner_container;

    //SwipeRefreshLayout mswipeRefreshLayout;
   // private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
//        mProgressBar = findViewById(R.id.idprogessbar);
        mWebView = findViewById(R.id.idwebview);
        banner_container = findViewById(R.id.banner_container);
       //mswipeRefreshLayout = findViewById(R.id.swipetorefersh);

        mContext = getApplicationContext();
        progressDialog =  new CustomProgressDialog(this);

        AdsUtils.adsShow(this,banner_container);
//        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//
//            }
//        });
//        madView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        madView.loadAd(adRequest);


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
//                mProgressBar.setVisibility(View.VISIBLE);
                progressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // Do something when page loading finished

            }

        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                // Update the progress bar with page loading progress
//                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    // Hide the progressbar
//                    mProgressBar.setVisibility(View.GONE);
                    progressDialog.dismiss();
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

    @Override
    protected void onDestroy() {
        AdsUtils.adsDestroy();
        super.onDestroy();
    }
}
