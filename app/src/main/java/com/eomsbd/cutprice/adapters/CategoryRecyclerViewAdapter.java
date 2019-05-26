package com.eomsbd.cutprice.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.SubCategoryActivity;
import com.eomsbd.cutprice.model.category_model.Datum1;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewHolder> {


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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_list,viewGroup,false);
        return new CategoryRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewHolder holder, int i) {
        final Datum1 singleProduct = data.get(i);
        String image = "https://www.rokomari.com/static/new/img/electronics/computer.png";
        Picasso.get().load(image).into(holder.imageView);
        holder.textView.setText(singleProduct.getCategoriesName());

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

    @Override
    public int getItemCount() {
        return data.size();
    }
}
