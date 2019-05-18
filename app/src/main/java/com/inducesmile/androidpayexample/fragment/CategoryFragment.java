package com.inducesmile.androidpayexample.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inducesmile.androidpayexample.OnBackPressed;
import com.inducesmile.androidpayexample.R;
import com.inducesmile.androidpayexample.ShoppingActivity;
import com.inducesmile.androidpayexample.model.category_model.Category;
import com.inducesmile.androidpayexample.web_api.IClientServer;
import com.inducesmile.androidpayexample.web_api.RetrofitService;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements OnBackPressed {

    RecyclerView recyclerView;
    IClientServer iClientServer;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        iClientServer = RetrofitService.getRetrofitInstance().create(IClientServer.class);
        getCategoryData();
        return view;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getActivity(), ShoppingActivity.class));
    }


    public void getCategoryData() {


        Call<Category> categoryCall = iClientServer.getSubmenu();
        categoryCall.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                Category category = response.body();


            }


            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Toasty.error(getContext(), "Errrooooor" + t.getMessage(), Toasty.LENGTH_LONG).show();
            }
        });

    }
}
