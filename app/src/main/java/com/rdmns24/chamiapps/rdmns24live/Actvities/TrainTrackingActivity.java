package com.rdmns24.chamiapps.rdmns24live.Actvities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.rdmns24.chamiapps.rdmns24live.Ads.AdsUtils;
import com.rdmns24.chamiapps.rdmns24live.BuildConfig;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.rdmns24.chamiapps.rdmns24live.widget.CustomProgressDialog;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TrainTrackingActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private CustomProgressDialog progressDialog;
    private WebView mWebView;
    private Context mContext;
    private LinearLayout banner_container;
//    private AdView madView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_tracking);

        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        mProgressBar = findViewById(R.id.idprogessbar);
        mWebView = findViewById(R.id.idwebview);
        banner_container = findViewById(R.id.banner_container);
        mContext = getApplicationContext();
        progressDialog =  new CustomProgressDialog(this);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
        String currentDateTimeCode = sdf.format(new Date());
//        String currentDateTimeCodeFinal = StringUtils.substring(currentDateTimeCode, 0, currentDateTimeCode.length() - 1) + "RDMNS";
        if (BuildConfig.DEBUG) {
            Log.e("getDateTime ", "getDateTime2 :" + currentDateTimeCode + "RDMNS");
            Log.e("getDateTimeFinal ", "getDateTime :" + currentDateTimeCode);
        }

        try {
            String sha256 = getSHA256(currentDateTimeCode+"RDMNS");
            if (BuildConfig.DEBUG)
                Log.e("getSHA256 ", "getSHA256 :" + sha256);
            String finalWebUrl = "https://rdmns.hesn.xyz/app/radar.php?key=" + sha256;
            if (BuildConfig.DEBUG)
                Log.e("finalWebUrl ", "finalWebUrl :" + finalWebUrl);
            renderWebPage(finalWebUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String getSHA256(String clearText) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA256");
        digest.update(clearText.getBytes("UTF-8"));
        byte[] hashtext = digest.digest();
        return bytesToHex(hashtext);
    }

    private static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


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

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

                mWebView.setVisibility(View.GONE);
                progressDialog.dismiss();

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
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
    public void onResume() {
        super.onResume();
//        renderWebPage("http://rdmns.lk/stationinfo/");
    }

    @Override
    protected void onDestroy() {
        AdsUtils.adsDestroy();
        super.onDestroy();
    }
}