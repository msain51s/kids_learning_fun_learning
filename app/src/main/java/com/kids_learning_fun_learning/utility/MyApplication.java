package com.kids_learning_fun_learning.utility;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.kids_learning_fun_learning.R;

/**
 * Created by mamtakumari on 9/17/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize the AdMob app
        MobileAds.initialize(this, getString(R.string.admob_app_id));
    }
}