package com.eomsbd.cutprice.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eomsbd.cutprice.R;

public class CategoryRecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;

    public CategoryRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.category_image);
        textView = itemView.findViewById(R.id.category_name);
    }
}
