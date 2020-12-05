package com.example.testapplicationwesmm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.airbnb.lottie.LottieAnimationView;
import com.appsflyer.AppsFlyerLib;
import com.example.testapplicationwesmm.ApplicationClass;
import com.example.testapplicationwesmm.CustomWebViewClient;
import com.example.testapplicationwesmm.DataManager;
import com.example.testapplicationwesmm.R;

public class MainActivity extends AppCompatActivity {
    private String urlToShow = "https://google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView webPageView = (WebView) findViewById(R.id.web_view);
        webPageView.setBackgroundColor(Color.WHITE);
        webPageView.getSettings().setJavaScriptEnabled(true);
        webPageView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webPageView.getSettings().setAppCacheEnabled(false);

        LottieAnimationView splashScreen = (LottieAnimationView) findViewById(R.id.loading_animation);

        webPageView.setWebViewClient(new CustomWebViewClient(splashScreen));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!DataManager.isValueIsSet()){
                    handler.postDelayed(this, 100);
                }else{
                    if(!DataManager.isOrganic()){
                        urlToShow = "https://bing.com";
                    }
                    webPageView.loadUrl(urlToShow);
                    handler.removeCallbacks(this);
                }
            }
        }, 100);
    }
}