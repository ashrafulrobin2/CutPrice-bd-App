package com.eomsbd.cutprice.adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eomsbd.cutprice.R;


import com.eomsbd.cutprice.model.products_model.Products;

import java.util.ArrayList;

public class ProductListItemAdapter extends RecyclerView.Adapter<ProductRecyclerViewHolder> {
    ShopRecyclerViewAdapter shopRecyclerViewAdapter;
    private ArrayList<Products> itemsList;
    private Context mContext;

    public ProductListItemAdapter(ArrayList<Products> itemsList, Context mContext) {
        this.itemsList = itemsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProductRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list, null);
        ProductRecyclerViewHolder mh = new ProductRecyclerViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerViewHolder holder, final int i) {
        if (getItemViewType(i) == 0) {
            ArrayList singleSectionItems = (ArrayList) itemsList.get(0).getData();
            String headerName = itemsList.get(0).getData().get(0).getProductName();
            shopRecyclerViewAdapter = new ShopRecyclerViewAdapter(mContext, singleSectionItems);
            holder.recyclerView.setHasFixedSize(true);
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            holder.recyclerView.setAdapter(shopRecyclerViewAdapter);
            holder.recyclerView.setNestedScrollingEnabled(false);
            holder.title.setText(headerName);

        }


        if (getItemViewType(i) == 1) {
            ArrayList singleSectionItems = (ArrayList) itemsList.get(1).getData();
            String headerName = itemsList.get(1).getData().get(1).getProductName();
            shopRecyclerViewAdapter = new ShopRecyclerViewAdapter(mContext, singleSectionItems);
            holder.recyclerView.setHasFixedSize(true);
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            holder.recyclerView.setAdapter(shopRecyclerViewAdapter);
            holder.recyclerView.setNestedScrollingEnabled(false);
            holder.title.setText(headerName);

        }

        if (getItemViewType(i) == 2) {
            ArrayList singleSectionItems = (ArrayList) itemsList.get(2).getData();
            String headerName = itemsList.get(0).getData().get(2).getProductName();
            shopRecyclerViewAdapter = new ShopRecyclerViewAdapter(mContext, singleSectionItems);
            holder.recyclerView.setHasFixedSize(true);
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            holder.recyclerView.setAdapter(shopRecyclerViewAdapter);
            holder.recyclerView.setNestedScrollingEnabled(false);
            holder.title.setText(headerName);

        }
    }



    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }
}
