package com.eomsbd.cutprice.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.activity.ProductActivity;
import com.eomsbd.cutprice.model.products_model.Datum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopRecyclerViewAdapter1 extends RecyclerView.Adapter<ShopRecyclerViewHolder1> {

    private static final String TAG = ShopRecyclerViewAdapter1.class.getSimpleName();
    boolean userClicked = false;
    private Context context;
    int num = 1;
    private List<Datum> allProducts;

    public ShopRecyclerViewAdapter1(Context context, List<Datum> allProducts) {
        this.context = context;
        this.allProducts = allProducts;
    }

    @Override
    public ShopRecyclerViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_listing, parent, false);
        ShopRecyclerViewHolder1 productHolder = new ShopRecyclerViewHolder1(layoutView);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopRecyclerViewHolder1 holder1, int i) {
        String path = "http://cutpricebd.com/oms/product_image/thumbs/";
        final Datum singleProduct = allProducts.get(i);
        String product_id = singleProduct.getProductId();
        if (getItemViewType(i) == 0 && allProducts.size() >= 10) {
            if ((path + singleProduct.getImg1()).length() <= 60) {
                Picasso.get().load(path + singleProduct.getImg1()).into(holder1.produceImage);
            } else {
                Picasso.get().load(singleProduct.getImg1()).into(holder1.produceImage);
            }


            holder1.productName.setText(singleProduct.getProductName());
        }


        holder1.produceImage.setOnClickListener(new View.OnClickListener() {

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
      /*  if (userClicked)
            return allProducts.size();
        else
            return allProducts.size() > 10 ? 10 : allProducts.size();*/
        return (null != allProducts ? allProducts.size() : 10);
    }


}
