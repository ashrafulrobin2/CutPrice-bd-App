package com.eomsbd.cutprice;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.eomsbd.cutprice.adapters.ShopRecyclerViewAdapter;
import com.eomsbd.cutprice.adapters.SubCategoryRecyclerViewAdapter;
import com.eomsbd.cutprice.fragment.CategoryFragment;
import com.eomsbd.cutprice.fragment.SubCategoryFragment;
import com.eomsbd.cutprice.helpers.SpacesItemDecoration;
import com.eomsbd.cutprice.model.category_model.Category;
import com.eomsbd.cutprice.model.products_model.Products;
import com.eomsbd.cutprice.model.sub_category.CategoryId;
import com.eomsbd.cutprice.model.sub_category.Datum2;
import com.eomsbd.cutprice.model.sub_category.SubCategory;
import com.eomsbd.cutprice.web_api.IClientServer;
import com.eomsbd.cutprice.web_api.RetrofitService;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryActivity extends AppCompatActivity {
    RecyclerView subCategoryRecyclerView;
    SubCategoryRecyclerViewAdapter adapter;
    IClientServer iClientServer;
    private AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        subCategoryRecyclerView = findViewById(R.id.subCategory_list);

        String productInStringFormat = getIntent().getExtras().getString("PRODUCT");
        String name = getIntent().getExtras().getString("name");
        getProductsFromApi(productInStringFormat);
        setTitle(name);

    }

    public void getProductsFromApi(String productInStringFormat) {
        iClientServer = RetrofitService.getRetrofitInstance().create(IClientServer.class);
        CategoryId categoryId = new CategoryId();
        categoryId.setCatId(productInStringFormat);

        final Call<SubCategory> productsCall = iClientServer.getSubCategory(categoryId);

        productsCall.enqueue(new Callback<SubCategory>() {
            @Override
            public void onResponse(Call<SubCategory> call, Response<SubCategory> response) {
                SubCategory subCategory = response.body();

                loadDataList(subCategory.getData());
            }

            @Override
            public void onFailure(Call<SubCategory> call, Throwable t) {
                alertDialogBuilder = new AlertDialog.Builder(SubCategoryActivity.this);

                //For Setting Title



                //for setting message
                //fot setting Icon
                alertDialogBuilder.setIcon(R.drawable.wifi);
                alertDialogBuilder.setMessage(R.string.message_text2);

                alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //exit
                        finish();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void loadDataList(List<Datum2> data1) {
//Get a reference to the RecyclerView//
        if (data1 != null) {
            adapter = new SubCategoryRecyclerViewAdapter(SubCategoryActivity.this, data1);
            //Use a LinearLayoutManager with default vertical orientation//
            GridLayoutManager mGrid = new GridLayoutManager(SubCategoryActivity.this, 2);
            subCategoryRecyclerView.setLayoutManager(mGrid);
            subCategoryRecyclerView.setHasFixedSize(true);
            subCategoryRecyclerView.addItemDecoration(new SpacesItemDecoration(2, 12, false));

         //Set the Adapter to the RecyclerView//
            subCategoryRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            dialogBox();
        }


    }

    @SuppressLint("ResourceType")
    public void dialogBox() {
        alertDialogBuilder = new AlertDialog.Builder(SubCategoryActivity.this);

        //For Setting Title

        alertDialogBuilder.setTitle(R.string.title_text2);

        //for setting message
        //fot setting Icon
        alertDialogBuilder.setIcon(R.drawable.error);

        alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //exit
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
