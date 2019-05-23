package com.cutprice.eomsbd.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutprice.eomsbd.OnBackPressed;
import com.cutprice.eomsbd.R;
import com.cutprice.eomsbd.ShoppingActivity;

/**
 * A simple {@link Fragment} subclass.
 */


public class VideoFragment extends Fragment implements OnBackPressed {

    ProgressDialog progressDialog;


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        progressDialog=new ProgressDialog(getContext());

        progressDialog.setMessage("Loading..");
        progressDialog.show();

        return inflater.inflate(R.layout.fragment_video, container, false);



    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getActivity(), ShoppingActivity.class));
    }
}
