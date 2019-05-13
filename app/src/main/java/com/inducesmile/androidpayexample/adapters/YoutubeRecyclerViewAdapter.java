package com.inducesmile.androidpayexample.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inducesmile.androidpayexample.R;
import com.inducesmile.androidpayexample.model.products_model.Datum;

import java.util.List;

public class YoutubeRecyclerViewAdapter extends RecyclerView.Adapter<YoutubeViewHolder> {
    private static final String TAG = YoutubeRecyclerViewAdapter.class.getSimpleName();
    boolean userClicked = false;
    private Context context;

    private List<Datum> allProducts;

    public YoutubeRecyclerViewAdapter(Context context, List<Datum> allProducts) {
        this.context = context;
        this.allProducts = allProducts;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_list_item, viewGroup, false);
        YoutubeViewHolder youtubeViewHolder = new YoutubeViewHolder(layoutView);
        return youtubeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeViewHolder holder, int i) {
        final Datum singleProduct = allProducts.get(i);
        holder.webView.loadUrl("https://www.youtube.com/watch?v=zD6BGeq7qmw");

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        if (userClicked)
            return allProducts.size();
        else
            return allProducts.size() > 12 ? 12 : allProducts.size();
    }
}
