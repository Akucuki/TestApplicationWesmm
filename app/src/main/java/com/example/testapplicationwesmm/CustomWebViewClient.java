package com.example.testapplicationwesmm;

import android.os.Handler;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class CustomWebViewClient extends WebViewClient {
    private LottieAnimationView splashScreen;

    public CustomWebViewClient(LottieAnimationView splashScreen){
        this.splashScreen = splashScreen;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return false;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        splashScreen.setVisibility(View.INVISIBLE);
        view.setVisibility(View.VISIBLE);
        super.onPageFinished(view, url);
    }
}
