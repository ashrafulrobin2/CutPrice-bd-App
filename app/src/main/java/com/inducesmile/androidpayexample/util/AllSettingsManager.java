package com.inducesmile.androidpayexample.util;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.Locale;

public class AllSettingsManager {

    public AllSettingsManager() {
    }

    public static void setLocale(Context context, String localeName) {
        Locale locale = new Locale(localeName);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    public static boolean networkEnable(Context c, AllSettingsManager.NetworkSet netset) {
        @SuppressLint("WrongConstant") ConnectivityManager cm = (ConnectivityManager) c.getSystemService("connectivity");
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        } else if (!networkInfo.isAvailable()) {
            return false;
        } else {
            return cm.getActiveNetworkInfo().isConnected();
        }
    }

    public static boolean isWifiAvailable(Context ctx) {
        @SuppressLint("WrongConstant") ConnectivityManager myConnManager = (ConnectivityManager) ctx.getSystemService("connectivity");
        NetworkInfo myNetworkInfo = myConnManager.getNetworkInfo(1);
        return myNetworkInfo.isConnected();
    }

    public static boolean isGpsEnabled(Context ctx) {
        @SuppressLint("WrongConstant") LocationManager locationManager = (LocationManager) ctx.getSystemService("location");
        return locationManager.isProviderEnabled("gps");
    }

    public static boolean isWifiLocationEnabled(Context context) {
        ContentResolver cr = context.getContentResolver();
        String enabledProviders = Settings.Secure.getString(cr, "location_providers_allowed");
        if (!TextUtils.isEmpty(enabledProviders)) {
            String[] providersList = TextUtils.split(enabledProviders, ",");
            String[] var4 = providersList;
            int var5 = providersList.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                String provider = var4[var6];
                if ("network".equals(provider)) {
                    return true;
                }
            }
        }

        return false;
    }


    public static String removeFirstAndLast(String data) {
        return data.substring(1, data.length() - 1);
    }

    public static boolean isNullOrEmpty(String myString) {
        if (myString == null) {
            return true;
        } else {
            return myString.length() == 0 || myString.equalsIgnoreCase("null") || myString.equalsIgnoreCase("");
        }
    }

    public static boolean isInteger(String s) {
        if (s != null && s.length() != 0) {
            for (int i = 0; i < s.length(); ++i) {
                if (Character.digit(s.charAt(i), 10) < 0) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public enum NetworkSet {
        NETWORK_3G,
        NETWORK_WIFI,
        NETWORK_ALL;

        NetworkSet() {
        }
    }
}