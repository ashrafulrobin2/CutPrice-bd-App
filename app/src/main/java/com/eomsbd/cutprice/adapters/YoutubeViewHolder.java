package com.eomsbd.cutprice.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.eomsbd.cutprice.R;

public class YoutubeViewHolder extends RecyclerView.ViewHolder {
    WebView webView;
    Button button;
    public YoutubeViewHolder(@NonNull View itemView) {
        super(itemView);
        webView = itemView.findViewById(R.id.video_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient(){

        });

    }
}
