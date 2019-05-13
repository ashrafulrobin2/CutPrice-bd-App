
package com.inducesmile.androidpayexample.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inducesmile.androidpayexample.R;
import com.inducesmile.androidpayexample.model.products_model.Datum;

import java.util.List;

public class CheckRecyclerViewAdapter extends RecyclerView.Adapter<CheckRecyclerViewHolder> {

    private Context context;
    int num = 1;

    private List<Datum> mProductObject;


    public CheckRecyclerViewAdapter(Context context, List<Datum> mProductObject) {
        this.context = context;
        this.mProductObject = mProductObject;
    }

    @Override
    public CheckRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_layout, parent, false);
        CheckRecyclerViewHolder productHolder = new CheckRecyclerViewHolder(layoutView);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(CheckRecyclerViewHolder holder, int position) {
        //get product quantity
        holder.quantity.setText("1");
        holder.productName.setText(mProductObject.get(position).getProductName());
        holder.productPrice.setText(mProductObject.get(position).getProductOldPrice() + " bdt");

        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context, "Do you want to remove product from cart", Toast.LENGTH_LONG).show();
                Datum data = new Datum();
                int position = mProductObject.indexOf(data);
                mProductObject.remove(position);
                notifyItemRemoved(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (num * 10 > mProductObject.size()) {
            return mProductObject.size();
        } else {
            return num * 10;
        }
    }
}