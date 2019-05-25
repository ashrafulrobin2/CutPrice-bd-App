package com.eomsbd.cutprice.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.adapters.SubCategoryRecyclerViewAdapter;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryFragment extends Fragment {
    RecyclerView recyclerView;
    SubCategoryRecyclerViewAdapter adapter;


    public SubCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sub_category, container, false);
        Bundle args = getArguments();
        //String categoryId = args.getString("index");

        String categoryId = getArguments().getString("category_id");
        //String categoryId = getArguments().getString("category_id");



        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
