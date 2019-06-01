package com.eomsbd.cutprice.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.util.AllSettingsManager;
import com.eomsbd.cutprice.util.AppUtil;
import com.eomsbd.cutprice.util.BaseUpdateListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashActivity extends AppCompatActivity {

    private TextView tvAppVersion;
    private ImageView ivAppLogo, ivAppLogoFlavor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initActivityViews();
        initActivityViewsData();
    }



    public void initActivityViews() {
        tvAppVersion = findViewById(R.id.tv_app_version);
        ivAppLogo = findViewById(R.id.iv_app_logo);
    }

    public void initActivityViewsData() {
        //Set app version
        String appVersion = AppUtil.getAppVersion(SplashActivity.this);
        if (!AllSettingsManager.isNullOrEmpty(appVersion)) {
            tvAppVersion.setText("Version: " + appVersion);
        }

        //Set flavor icon

        //Rotate app logo
        AppUtil.makeRotateAnimation(ivAppLogo, 1, new BaseUpdateListener() {
            @Override
            public void onUpdate(Object update) {
                if ((boolean) update) {
                    //Navigate to the next screen
                    navigateNextScreen();
                }
            }
        });
    }

    private void navigateNextScreen() {
        Intent intentAppDriver = new Intent(SplashActivity.this, ShoppingActivity.class);
        startActivity(intentAppDriver);
    }

}
