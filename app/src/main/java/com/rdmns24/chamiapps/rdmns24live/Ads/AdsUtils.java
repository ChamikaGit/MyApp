package com.rdmns24.chamiapps.rdmns24live.Ads;

import android.content.Context;
import android.widget.LinearLayout;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

public class AdsUtils {
    private static AdView adView;

    public static void adsShow(Context context,LinearLayout linearLayout){
        adView = new AdView(context, "471518170544244_473141860381875", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = linearLayout;
        adContainer.addView(adView);
        adView.loadAd();
    }

    public static void adsDestroy(){
        if (adView != null) {
            adView.destroy();
        }
    }
}
