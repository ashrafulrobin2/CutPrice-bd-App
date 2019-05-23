
package com.eomsbd.cutprice.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.Constant;
import com.eomsbd.cutprice.model.products_model.Datum;

import java.util.List;

public class CheckRecyclerViewAdapter extends RecyclerView.Adapter<CheckRecyclerViewAdapter.CheckRecyclerViewHolder> implements View.OnCreateContextMenuListener {

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
    public void onBindViewHolder(CheckRecyclerViewHolder holder, final int position) {
        //get product quantity
        holder.quantity.setText("1");
        holder.productName.setText(mProductObject.get(position).getProductName());
        holder.productPrice.setText(mProductObject.get(position).getProductSellingPrice() + " bdt");



        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context, "Do you want to remove product from cart", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mProductObject.size();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

    }


    public class CheckRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        public TextView quantity, productName, productPrice, removeProduct;

        public CheckRecyclerViewHolder(View itemView) {
            super(itemView);

            quantity = itemView.findViewById(R.id.quantity);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            removeProduct = itemView.findViewById(R.id.remove_from_cart);
            removeProduct.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderIcon(R.drawable.ic_category_on_black_24dp);
            menu.add(0, 0, getAdapterPosition(), Constant.DELETE);
        }
    }

}