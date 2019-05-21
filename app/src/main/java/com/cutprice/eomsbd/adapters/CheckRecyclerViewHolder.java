package com.cutprice.eomsbd.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.cutprice.eomsbd.R;

import com.cutprice.eomsbd.Constant;

public class CheckRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

    public TextView quantity, productName, productPrice, removeProduct;

    public CheckRecyclerViewHolder(View itemView) {
        super(itemView);

        quantity = itemView.findViewById(R.id.quantity);
        productName = itemView.findViewById(R.id.product_name);
        productPrice = itemView.findViewById(R.id.product_price);
        removeProduct = itemView.findViewById(R.id.remove_from_cart);
        itemView.setOnCreateContextMenuListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderIcon(R.drawable.ic_category_on_black_24dp);
        menu.add(0, 0, getAdapterPosition(), Constant.DELETE);
    }
}
