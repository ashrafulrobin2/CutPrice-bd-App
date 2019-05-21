package com.cutprice.eomsbd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.cutprice.eomsbd.R;


import com.cutprice.eomsbd.util.AllSettingsManager;
import com.cutprice.eomsbd.util.AppUtil;
import com.cutprice.eomsbd.util.BaseUpdateListener;

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
        AppUtil.makeRotateAnimation(ivAppLogo, 3, new BaseUpdateListener() {
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
        finish();
    }

}
