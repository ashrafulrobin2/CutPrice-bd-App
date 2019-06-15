package com.eomsbd.cutprice.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.tabLayoutFragment.Tab1Fragment;
import com.eomsbd.cutprice.tabLayoutFragment.Tab2Fragment;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryPagerAdapter extends PagerAdapter {

    Context context;
    private List<String> mItems = new ArrayList<>();

    public SubCategoryPagerAdapter() {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.subcategorymenu_list, container, false);

        TextView textView = view.findViewById(R.id.title);
        textView.setText("Page: " + mItems.get(position));
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Tab1";
            case 1:
                return "Tab2";
            default:
                return null;
        }

    }


    public void addAll(List<String> items) {
        mItems = new ArrayList<>(items);
    }

}

