package com.hamzajbr.fannak_user.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static boolean isArabic(Context context) {
        if (context != null) {
            return Utils.getValue(context, Constants.USER_LANGUAGE, Constants.ENGLISH_LANGUAGE).equalsIgnoreCase(Constants.ARABIC_LANGUAGE);
        } else {
            return false;
        }
    }

    public static void share(String text, Context mContext) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);

        mContext.startActivity(intent);

    }
    public static boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    public static boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String getCurrentLanguageFontPath(Context context) {
        String fontPath;
        if (Utils.getValue(context, Constants.USER_LANGUAGE, Constants.ENGLISH_LANGUAGE).equalsIgnoreCase(Constants.ENGLISH_LANGUAGE)) {
            fontPath = "fonts/english_regular.ttf";
        } else {
            fontPath = "fonts/arabic_regular.ttf";
        }
        return fontPath;
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }


    public static void refreshLocal(Context context) {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (sharedPreferences.getString(Constants.USER_LANGUAGE, "en").equals("en")) {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                sharedPreferences.edit().putString(Constants.USER_LANGUAGE, "en").apply();
                updateLanguage(context, sharedPreferences);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                }
                //PreferenceUtil.setSelectedLanguageId("en");

            } else {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                sharedPreferences.edit().putString(Constants.USER_LANGUAGE, "ar").apply();
                updateLanguage(context, sharedPreferences);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ((Activity) context).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                }
                //PreferenceUtil.setSelectedLanguageId("ar");


            }
        } catch (Exception e) {
            Log.e("fff", "refreshLocal: ");
        }

    }

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static long getDaysBetweenDates(String start, String end) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        Date startDate, endDate;
        long numberOfDays = 0;
        try {
            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
            numberOfDays = getUnitBetweenDates(startDate, endDate, TimeUnit.DAYS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return numberOfDays;
    }

    private static long getUnitBetweenDates(Date startDate, Date endDate, TimeUnit unit) {
        long timeDiff = endDate.getTime() - startDate.getTime();
        return unit.convert(timeDiff, TimeUnit.MILLISECONDS);
    }


    public static void setLanguage(String language, Context context) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(Constants.USER_LANGUAGE, language);
        editor.apply();

        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }


    public static void updateLanguage(Context cxt, SharedPreferences sharedPreferences) {
        Locale local = new Locale(sharedPreferences.getString(Constants.USER_LANGUAGE, "en"));
        Locale.setDefault(local);
        Configuration configuration = cxt.getResources().getConfiguration();
        configuration.setLocale(local);
        cxt.getResources().updateConfiguration(configuration, cxt.getResources().getDisplayMetrics());

    }


    public static boolean isMobile(String s) {
        return Patterns.PHONE.matcher(s).matches();

    }

    public static synchronized void setValue(Context context, String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(key, value).commit();
    }

    public static synchronized String getValue(Context context, String key, String defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, defaultValue);
    }

    public static synchronized void setValue(Context context, String key, boolean value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean(key, value).commit();
    }

    public static synchronized boolean getValue(Context context, String key, boolean defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(key, defaultValue);
    }

    public static synchronized void setValue(Context context, String key, float value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putFloat(key, value).commit();
    }

    public static synchronized float getValue(Context context, String key, float defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getFloat(key, defaultValue);
    }

    public static synchronized void setValue(Context context, String key, double value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putFloat(key, (float) value).commit();
    }

    public static synchronized double getValue(Context context, String key, double defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getFloat(key, (float) defaultValue);
    }

    public static synchronized void setValue(Context context, String key, int value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(key, value).apply();
    }

    public static synchronized int getValue(Context context, String key, int defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(key, defaultValue);
    }

    public static void goToActivity(Context context, Class<?> to, boolean finishAfter) {
        Intent i = new Intent(context, to);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }

}
