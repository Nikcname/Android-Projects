package com.example.nikcname.fragmentdialog;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;

public class DetailsWebFragment extends WebViewFragment {

    public DetailsWebFragment() {}

    WebView mWebView = getWebView();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mWebView = getWebView();
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);

    }

    public void updateDetails(String url){
        mWebView.loadUrl(url);
    }
}
