package com.eomsbd.cutprice.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eomsbd.cutprice.R;



public class ProductRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView title, more;
    RecyclerView recyclerView;

    public ProductRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.tv_title);
        more = itemView.findViewById(R.id.tv_more);
        recyclerView = itemView.findViewById(R.id.recycler_view_list);
    }
}
