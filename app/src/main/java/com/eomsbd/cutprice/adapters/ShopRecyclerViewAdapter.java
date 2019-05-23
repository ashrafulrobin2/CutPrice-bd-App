package com.eomsbd.cutprice.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eomsbd.cutprice.ProductActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.eomsbd.cutprice.R;


import com.eomsbd.cutprice.model.products_model.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopRecyclerViewAdapter extends RecyclerView.Adapter<ShopRecyclerViewHolder>{

    private static final String TAG = ShopRecyclerViewAdapter.class.getSimpleName();
    boolean userClicked = false;
    private Context context;
    int num = 1;
    private List<Datum> allProducts;

    public ShopRecyclerViewAdapter(Context context, List<Datum> allProducts) {
        this.context = context;
        this.allProducts = allProducts;
    }

    @Override
    public ShopRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_listing, parent, false);
        ShopRecyclerViewHolder productHolder = new ShopRecyclerViewHolder(layoutView);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(ShopRecyclerViewHolder holder, int position) {
        String path = "http://cutpricebd.com/oms/product_image/thumbs/";
        final Datum singleProduct = allProducts.get(position);
        String product_id = singleProduct.getProductId();
        if (getItemViewType(position) == 0 && allProducts.size() >= 10) {
            if ((path + singleProduct.getImg1()).length() <= 60) {
                Picasso.get().load(path + singleProduct.getImg1()).into(holder.produceImage);
            } else {
                Picasso.get().load(singleProduct.getImg1()).into(holder.produceImage);
            }


            holder.productName.setText(singleProduct.getProductName());
        }
        int ii = getItemViewType(position);
        final int index = holder.getAdapterPosition();






        holder.produceImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent productIntent = new Intent(context, ProductActivity.class);

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                              String stringObjectRepresentation = gson.toJson(singleProduct);

                productIntent.putExtra("PRODUCT", stringObjectRepresentation);
                context.startActivity(productIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
       /* if (userClicked)
            return allProducts.size();
        else
            return allProducts.size() > 12 ? 12 : allProducts.size();*/

        return (null != allProducts ? allProducts.size() : 10);
    }


}
