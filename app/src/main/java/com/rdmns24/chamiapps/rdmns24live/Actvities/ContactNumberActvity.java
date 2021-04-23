package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rdmns24.chamiapps.rdmns24live.Ads.AdsUtils;
import com.rdmns24.chamiapps.rdmns24live.R;

public class ContactNumberActvity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private WebView mWebView;
    private Context mContext;
    private LinearLayout banner_container;
//    private AdView madView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_number_actvity);

        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        mProgressBar = findViewById(R.id.idprogessbar);
        mWebView = findViewById(R.id.idwebview);
        banner_container = findViewById(R.id.banner_container);
        //mswipeRefreshLayout = findViewById(R.id.swipetorefersh);

        mContext = getApplicationContext();

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


        renderWebPage("http://rdmns.lk/stationinfo/");

    }


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

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

                mWebView.setVisibility(View.GONE);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse(url));
                    startActivity(intent);
                }
                if (url != null && url.startsWith("whatsapp://")) {
                    view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                }

                return super.shouldOverrideUrlLoading(view, url);
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


    @Override
    public void onResume() {
        super.onResume();
        renderWebPage("http://rdmns.lk/stationinfo/");
    }

    @Override
    protected void onDestroy() {
        AdsUtils.adsDestroy();
        super.onDestroy();
    }
}
