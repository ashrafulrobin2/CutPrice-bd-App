package com.eomsbd.cutprice.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eomsbd.cutprice.OnBackPressed;
import com.eomsbd.cutprice.R;


import com.eomsbd.cutprice.activity.ShoppingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements OnBackPressed {



    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getActivity(), ShoppingActivity.class));
    }
}
