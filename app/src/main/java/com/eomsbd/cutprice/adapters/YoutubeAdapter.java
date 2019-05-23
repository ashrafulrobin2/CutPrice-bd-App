package com.eomsbd.cutprice.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.model.DataSetList;

import java.util.ArrayList;
import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeViewHolder>{
  List<DataSetList> arrayList;
    Context context;
    private LayoutInflater inflater;

    public YoutubeAdapter(List<DataSetList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_per_row,viewGroup,false);
        return new YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeViewHolder youtubeViewHolder, int i) {
        final DataSetList current = arrayList.get(i);

        youtubeViewHolder.webView.loadData(current.getLink(), "text/html", "utf-8");
        /*youtubeViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Intent i = new Intent(context,VideoFullScreen.class);
                i.putExtra("link",current.getLink());
                context.startActivity(i);*//*


            }
        });*/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
