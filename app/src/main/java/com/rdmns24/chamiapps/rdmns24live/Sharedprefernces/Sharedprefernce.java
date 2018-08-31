package com.rdmns24.chamiapps.rdmns24live.Sharedprefernces;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by fidenz on 4/24/18.
 */

public class Sharedprefernce {

    public static final String PREFS_BUGET = "BUGET";
    public static final String PREFS_KEYBUGET = "BUGET_PREFS_String";

    public static final String PREFS_exitdialog = "DIALOG";
    public static final String PREFS_KEYexitdialog = "DIALOG_PREFS_String";

    public Sharedprefernce() {

        super();
    }

    public void save(Context context, String buget) {


        SharedPreferences mbuget;
        SharedPreferences.Editor editor;

        mbuget = context.getSharedPreferences(PREFS_BUGET, Context.MODE_PRIVATE);

        editor = mbuget.edit();
        editor.putString(PREFS_KEYBUGET,buget);

        editor.commit();


    }

    public void savedialog(Context context, String buget) {


        SharedPreferences mdialog;
        SharedPreferences.Editor editor;

        mdialog = context.getSharedPreferences(PREFS_exitdialog, Context.MODE_PRIVATE);

        editor = mdialog.edit();
        editor.putString(PREFS_KEYexitdialog,buget);

        editor.commit();


    }



    public String getval(Context context){

        SharedPreferences buget;
        String text;
        buget = context.getSharedPreferences(PREFS_BUGET,Context.MODE_PRIVATE);
        text = buget.getString(PREFS_KEYBUGET,null);
        return text;



    }

    public String getvaldialog(Context context){

        SharedPreferences buget;
        String text;
        buget = context.getSharedPreferences(PREFS_exitdialog,Context.MODE_PRIVATE);
        text = buget.getString(PREFS_KEYexitdialog,null);
        return text;



    }
}
