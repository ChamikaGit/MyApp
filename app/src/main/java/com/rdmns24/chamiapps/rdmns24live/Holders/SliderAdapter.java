package com.rdmns24.chamiapps.rdmns24live.Holders;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rdmns24.chamiapps.rdmns24live.R;

/**
 * Created by fidenz on 5/6/18.
 */

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;


    public SliderAdapter(Context context) {
        this.context = context;
    }


    public int[] sliderimages = {

            R.drawable.intro_imageone,
            R.drawable.intro_imagetwo,
            R.drawable.intro_imageone,
            R.drawable.intro_imagefour


    };

    public String[] sliderhead = {


            "RDMNS.LK ජංගම දුරකථන මෘදුකාංගය වෙත පිවිසි ඔබ සාදරයෙන් පිළිගන්නෙමු..!",
            "මෙම සංස්කරණයේ ඇති නව විශේෂාංගයක් ලෙස එක් එක් දුම්රිය මාර්ගයන් වලට අදාල දුම්රිය ප්\u200Dරමාදයන් ඇතුලු හදිසි දැනුම්දීම් \u2063වර්ගීකරණය කර ඇත.",
            "දුම්රිය මගීන්ගේ නිල Facebook සමූහය විසින් සිදු කෙරෙන මෙම සේවාව මහජන තොරතුරු බෙදා හදා ගැනීම මත ක්\u200Dරියාත්මක වන සමූහයක් බැවින්, ඔබටද ඔබගේ Facebook ගිණුම හරහා මෙම සමූහයේ  සාමාජිකත්වය ලබාගෙන තොරතුරු බෙදාගැනීමට දායක වීමටත්, අවශ්\u200Dය තොරතුරු යාවත්කාලීන කරගැනීමටත් හැකියාව ඇත.",
            "ස්තූතියි...!"

    };

    public String[] slider_description = {


            "Welcome to RDMNS.LK Mobile App..!",
            "All the live updates and notifications are categorized under each train line in this new update. ",
            "RDMNS.LK Facebook group runs on crowdsource verified information. You also can join with us and share information and get live updates.",
            "Thank You...!"


    };


    @Override
    public int getCount() {
        return sliderhead.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.viewpagerlayout, container, false);

        ImageView sliderimageView = view.findViewById(R.id.sliderimage);
        TextView sliderttitle = view.findViewById(R.id.slidertitile);
        TextView sliderdescription = view.findViewById(R.id.sliderdescription);

        sliderimageView.setImageResource(sliderimages[position]);
        sliderttitle.setText(sliderhead[position]);

        sliderdescription.setText(slider_description[position]);
        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
