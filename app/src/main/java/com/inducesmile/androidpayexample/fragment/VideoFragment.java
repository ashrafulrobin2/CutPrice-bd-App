package com.inducesmile.androidpayexample.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inducesmile.androidpayexample.OnBackPressed;
import com.inducesmile.androidpayexample.R;
import com.inducesmile.androidpayexample.ShoppingActivity;
import com.inducesmile.androidpayexample.adapters.YoutubeRecyclerViewAdapter;
import com.inducesmile.androidpayexample.model.products_model.Datum;
import com.inducesmile.androidpayexample.model.products_model.Products;
import com.inducesmile.androidpayexample.web_api.IClientServer;
import com.inducesmile.androidpayexample.web_api.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment implements OnBackPressed {
    IClientServer iClientServer;
    ProgressDialog progressDialog;
    private RecyclerView recyclerView;


    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);

        iClientServer = RetrofitService.getRetrofitInstance().create(IClientServer.class);
        getProductsFromApi();
        return view;
    }

    public void getProductsFromApi() {
        String id = "Cutprice@987";
        final Call<Products> productsCall = iClientServer.getALlProducts(id);

        productsCall.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                Products products = response.body();
                progressDialog.dismiss();
                loadDataList(products.getData());
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(getContext(), "Unable to load users " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getActivity(), ShoppingActivity.class));
    }

    private void loadDataList(List<Datum> usersList) {

//Get a reference to the RecyclerView//


//Use a LinearLayoutManager with default vertical orientation//

        YoutubeRecyclerViewAdapter shopAdapter = new YoutubeRecyclerViewAdapter(getContext(), usersList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(shopAdapter);
        shopAdapter.notifyDataSetChanged();
      /*  GridLayoutManager mGrid = new GridLayoutManager(getActivity(), 3);
        shoppingRecyclerView.setLayoutManager(mGrid);
        shoppingRecyclerView.setHasFixedSize(true);
        shoppingRecyclerView.addItemDecoration(new SpacesItemDecoration(3, 12, false));*/


//Set the Adapter to the RecyclerView//

    }
}
