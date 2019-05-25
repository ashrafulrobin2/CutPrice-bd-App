package com.eomsbd.cutprice.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eomsbd.cutprice.R;

import es.dmoral.toasty.Toasty;

public class SubCategoryRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    ImageView imageView;
    public SubCategoryRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

            textView = itemView.findViewById(R.id.subcategory_name);
            imageView = itemView.findViewById(R.id.subcategory_image);



    }
}
