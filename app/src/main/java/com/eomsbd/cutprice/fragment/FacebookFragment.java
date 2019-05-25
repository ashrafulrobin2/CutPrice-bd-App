package com.eomsbd.cutprice.fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.eomsbd.cutprice.OnBackPressed;
import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.ShoppingActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FacebookFragment extends Fragment implements OnBackPressed {
    ProgressDialog progressDialog;

    public WebView mWebView;
    public FacebookFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_facebook, container, false);
        mWebView = (WebView) v.findViewById(R.id.webview);


            if(mWebView == null){
            progressDialog=new ProgressDialog(getContext());

            progressDialog.setMessage("Loading..");
            progressDialog.show();

        }
        else {

            mWebView.loadUrl("https://www.facebook.com/cutpricebd");
//  progressDialog.dismiss();
            // Enable Javascript
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            // Force links and redirects to open in the WebView instead of in a browser
            mWebView.setWebViewClient(new WebViewClient());


        }


        return v;
    }



    @Override
    public void onBackPressed() {
        startActivity(new Intent(getActivity(), ShoppingActivity.class));
    }
}
