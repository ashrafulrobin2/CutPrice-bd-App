package com.eomsbd.cutprice.adapters.helpers;

import android.support.v4.app.FragmentManager;

import com.eomsbd.cutprice.adapters.Interface.NavigationManager;
import com.eomsbd.cutprice.adapters.ShoppingActivity;

public class FragmentNavigationManager implements NavigationManager {
    private static FragmentNavigationManager mInstance;
    private FragmentManager fragmentManager;
    private ShoppingActivity shoppingActivity;

    public static FragmentNavigationManager getInstance(ShoppingActivity shoppingActivity) {
        if (mInstance == null)
            mInstance = new FragmentNavigationManager();
        mInstance.configure(shoppingActivity);
        return mInstance;
    }

    private void configure(ShoppingActivity shoppingActivity) {
        shoppingActivity = shoppingActivity;
        fragmentManager = shoppingActivity.getSupportFragmentManager();
    }


    @Override
    public void showFragment(String title) {

    }
}
