package com.example.testapplicationwesmm;

import android.app.Application;
import android.util.Log;

import com.appsflyer.AppsFlyer2dXConversionCallback;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.onesignal.OneSignal;

import java.util.Map;

public class ApplicationClass extends Application {
    private final String AF_DEV_KEY = "8E5jvDrNzWTEa9tZcUsTse";
    private boolean organic;

    @Override
    public void onCreate() {
        super.onCreate();

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
            @Override
            public void onConversionDataSuccess(Map<String, Object> map) {
                for (String attrName : map.keySet()){
                    Log.d("AppsFlyer", "attribute: " + attrName + " = " + map.get(attrName));
                }
                organic = map.get("af_status").toString().contains("Organic");
                DataManager.setOrganic(organic);
            }

            @Override
            public void onConversionDataFail(String s) {
                Log.d("AppsFlyer", "error getting conversation data: " + s);
            }

            @Override
            public void onAppOpenAttribution(Map<String, String> map) {
                for(String attrName : map.keySet()){
                    Log.d("AppsFlyer", "attribute: " + attrName + " = " + map.get(attrName));
                }
            }

            @Override
            public void onAttributionFailure(String s) {
                Log.d("AppsFlyer", "error onAttributionFailure: " + s);
            }
        };

        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, getApplicationContext());
        AppsFlyerLib.getInstance().startTracking(this);
    }

    public boolean isOrganic() {
        return organic;
    }
}
