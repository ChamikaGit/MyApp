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

    public static final String PREFS_switch1 = "DIALOG1";
    public static final String PREFS_KEYswitch1 = "DIALOG1_PREFS_String";

    public static final String PREFS_switch2 = "DIALOG2";
    public static final String PREFS_KEYswitch2 = "DIALOG2_PREFS_String";

    public static final String PREFS_switch3 = "DIALOG3";
    public static final String PREFS_KEYswitch3 = "DIALOG3_PREFS_String";

    public static final String PREFS_switch4 = "DIALOG4";
    public static final String PREFS_KEYswitch4 = "DIALOG4_PREFS_String";

    public static final String PREFS_switch5 = "DIALOG5";
    public static final String PREFS_KEYswitch5 = "DIALOG5_PREFS_String";

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


    ////////////////////////////////

    public void notificationSwitch1(Context context, String buget) {

        SharedPreferences mbuget;
        SharedPreferences.Editor editor;
        mbuget = context.getSharedPreferences(PREFS_switch1, Context.MODE_PRIVATE);

        editor = mbuget.edit();
        editor.putString(PREFS_KEYswitch1,buget);

        editor.commit();
    }
    public void notificationSwitch2(Context context, String buget) {

        SharedPreferences mbuget;
        SharedPreferences.Editor editor;
        mbuget = context.getSharedPreferences(PREFS_switch2, Context.MODE_PRIVATE);

        editor = mbuget.edit();
        editor.putString(PREFS_KEYswitch2,buget);

        editor.commit();
    }
    public void notificationSwitch3(Context context, String buget) {

        SharedPreferences mbuget;
        SharedPreferences.Editor editor;
        mbuget = context.getSharedPreferences(PREFS_switch3, Context.MODE_PRIVATE);

        editor = mbuget.edit();
        editor.putString(PREFS_KEYswitch3,buget);

        editor.commit();
    }
    public void notificationSwitch4(Context context, String buget) {

        SharedPreferences mbuget;
        SharedPreferences.Editor editor;
        mbuget = context.getSharedPreferences(PREFS_switch4, Context.MODE_PRIVATE);

        editor = mbuget.edit();
        editor.putString(PREFS_KEYswitch4,buget);

        editor.commit();
    }
    public void notificationSwitch5(Context context, String buget) {

        SharedPreferences mbuget;
        SharedPreferences.Editor editor;
        mbuget = context.getSharedPreferences(PREFS_switch5, Context.MODE_PRIVATE);

        editor = mbuget.edit();
        editor.putString(PREFS_KEYswitch5,buget);

        editor.commit();
    }

    public String getSwitch1(Context context){

        SharedPreferences buget;
        String text;
        buget = context.getSharedPreferences(PREFS_switch1,Context.MODE_PRIVATE);
        text = buget.getString(PREFS_KEYswitch1,null);
        return text;

    }

    public String getSwitch2(Context context){

        SharedPreferences buget;
        String text;
        buget = context.getSharedPreferences(PREFS_switch2,Context.MODE_PRIVATE);
        text = buget.getString(PREFS_KEYswitch2,null);
        return text;



    }

    public String getSwitch3(Context context){

        SharedPreferences buget;
        String text;
        buget = context.getSharedPreferences(PREFS_switch3,Context.MODE_PRIVATE);
        text = buget.getString(PREFS_KEYswitch3,null);
        return text;



    }

    public String getSwitch4(Context context){

        SharedPreferences buget;
        String text;
        buget = context.getSharedPreferences(PREFS_switch4,Context.MODE_PRIVATE);
        text = buget.getString(PREFS_KEYswitch4,null);
        return text;



    }

    public String getSwitch5(Context context){

        SharedPreferences buget;
        String text;
        buget = context.getSharedPreferences(PREFS_switch5,Context.MODE_PRIVATE);
        text = buget.getString(PREFS_KEYswitch5,null);
        return text;



    }



}
