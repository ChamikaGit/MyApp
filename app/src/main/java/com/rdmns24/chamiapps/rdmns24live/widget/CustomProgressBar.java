package com.rdmns24.chamiapps.rdmns24live.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.rdmns24.chamiapps.rdmns24live.R;

import pl.droidsonroids.gif.GifImageView;


public class CustomProgressBar extends RelativeLayout {
//    private LottieAnimationView lottieAnimationView;
    public CustomProgressBar(Context context) {
        super(context);
        initView();
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = inflate(getContext(), R.layout.layout_custom_progress_view, this);
//        lottieAnimationView = view.findViewById(R.id.animation_view);
//        lottieAnimationView.setAnimation("data.json");
//        lottieAnimationView.playAnimation();
        GifImageView gifImageView = view.findViewById(R.id.gifImageView);
        gifImageView.setVisibility(View.VISIBLE);
    }

}
