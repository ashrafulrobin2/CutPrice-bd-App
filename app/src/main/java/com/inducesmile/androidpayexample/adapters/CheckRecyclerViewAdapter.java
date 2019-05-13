package com.inducesmile.androidpayexample.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inducesmile.androidpayexample.R;
import com.inducesmile.androidpayexample.entities.ProductObject;
import com.inducesmile.androidpayexample.helpers.MySharedPreference;
import com.inducesmile.androidpayexample.model.products_model.Datum;

import java.util.List;

public class CheckRecyclerViewAdapter extends RecyclerView.Adapter<CheckRecyclerViewHolder> {

    private Context context;

    private List<Datum> mProductObject;
    private MySharedPreference sharedPreference;

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
    public void onBindViewHolder(CheckRecyclerViewHolder holder, final int position) {
        //get product quantity
        holder.quantity.setText("1");
        holder.productName.setText(mProductObject.get(position).getProductName());
        holder.productPrice.setText(mProductObject.get(position).getProductSellingPrice() + " $");

        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context, "Do you want to remove product from cart", Toast.LENGTH_LONG).show();
                removeItem(position);
            }

        });

    }

    @Override
    public int getItemCount() {
        return mProductObject.size();
    }

    private void removeItem(int position) {
        mProductObject.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mProductObject.size());
    }
}
