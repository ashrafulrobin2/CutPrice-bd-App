package com.eomsbd.cutprice.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eomsbd.cutprice.Interface.DataPass;
import com.eomsbd.cutprice.ProductActivity;
import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.SubCategoryActivity;
import com.eomsbd.cutprice.fragment.SubCategoryFragment;
import com.eomsbd.cutprice.model.sub_category.CategoryId;
import com.eomsbd.cutprice.model.category_model.Datum1;
import com.eomsbd.cutprice.model.sub_category.SubCategory;
import com.eomsbd.cutprice.web_api.IClientServer;
import com.eomsbd.cutprice.web_api.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewHolder>  {
IClientServer iClientServer;

    Context context;
    List<Datum1> data;
    private LayoutInflater inflater;

    public CategoryRecyclerViewAdapter(Context context, List<Datum1> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CategoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_list, viewGroup, false);
        return new CategoryRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewHolder holder, int i) {
        final Datum1 singleProduct = data.get(i);
        String image = "https://www.rokomari.com/static/new/img/electronics/computer.png";
        Picasso.get().load(image).into(holder.imageView);
        holder.textView.setText(singleProduct.getCategoriesName());
        iClientServer= RetrofitService.getRetrofitInstance().create(IClientServer.class);
       final String catId = singleProduct.getCategoriesId();
       final String name=singleProduct.getCategoriesName();
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productIntent = new Intent(v.getContext(), SubCategoryActivity.class);
                productIntent.putExtra("PRODUCT", catId);
                productIntent.putExtra("name",name);
                v.getContext().startActivity(productIntent);

            }
        });
    }

    private void getValueFromApi(CategoryId catId, final View v) {
        final Call<SubCategory>subCategoryCall=  iClientServer.getSubCategory(catId);
        subCategoryCall.enqueue(new Callback<SubCategory>() {
            @Override
            public void onResponse(Call<SubCategory> call, Response<SubCategory> response) {
                SubCategory subCategory=response.body();
                Toasty.success(v.getContext(),""+subCategory.getData().size(),Toasty.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SubCategory> call, Throwable t) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


}
