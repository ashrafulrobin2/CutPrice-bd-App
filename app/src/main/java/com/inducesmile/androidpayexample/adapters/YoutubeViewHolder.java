package com.inducesmile.androidpayexample.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.inducesmile.androidpayexample.R;

public class YoutubeViewHolder extends RecyclerView.ViewHolder {
    WebView webView;
    ImageButton imageButton;

    public YoutubeViewHolder(@NonNull View itemView) {
        super(itemView);

        webView = itemView.findViewById(R.id.video);
        imageButton = itemView.findViewById(R.id.fullscreen);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
    }
}
