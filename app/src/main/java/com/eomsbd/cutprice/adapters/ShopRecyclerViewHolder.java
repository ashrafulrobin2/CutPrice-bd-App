package com.eomsbd.cutprice.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eomsbd.cutprice.R;



public class ShopRecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView produceImage;

    public TextView productName;

    public ShopRecyclerViewHolder(View itemView) {
        super(itemView);
        produceImage = itemView.findViewById(R.id.product_image);
        productName = itemView.findViewById(R.id.product_name);

    }
}
