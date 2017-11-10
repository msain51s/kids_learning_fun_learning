package com.kids_learning_fun_learning;

import android.animation.Animator;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.kids_learning_fun_learning.utility.Preferences;
import com.kids_learning_fun_learning.utility.Util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SplashScreen extends AppCompatActivity {

    Preferences pref;
    Calendar calendar,calendar1;
    View contentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        pref=new Preferences(this);

        contentLayout=findViewById(R.id.contentLayout);

        contentLayout.post(new Runnable() {
            @Override
            public void run() {

                circularRevealAnimation();
                continueAfterAnimation();
            }
        });


    }

public void continueAfterAnimation(){
   new Handler().postDelayed(new Runnable() {
       @Override
       public void run() {
           checkForUpdates();
       }
   },3000);
}
    public void circularRevealAnimation(){
        int x = contentLayout.getRight();
        int y = contentLayout.getBottom();

        int startRadius = 0;
        int endRadius = (int) Math.max(y,contentLayout.getHeight()-y);

        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(contentLayout, x, y, startRadius, endRadius);
            anim.setDuration(1000);
            contentLayout.setVisibility(View.VISIBLE);
            anim.start();
        }else
            contentLayout.setVisibility(View.VISIBLE);

    }

    public void checkForUpdates(){
        if(isAppUpgradeTime()) {
            if (Util.ChechInternetAvalebleOrNot(this)) {
                new getAppMarketVersionAsync().execute("https://play.google.com/store/apps/details?id=com.musa.intellicataloge");
            }
        }
        else {
            navigateToHome();
        }
    }

    public void navigateToHome(){
        Intent intent=new Intent(SplashScreen.this,MainActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,contentLayout,"bg_view");
            startActivity(intent,options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    public boolean isAppUpgradeTime()
    {
        String appUpgradeTime=pref.getAppUpgradeTime();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
        calendar= Calendar.getInstance();
        calendar1=Calendar.getInstance();
        String currentDate=null;
        try {

            calendar.setTime(dateFormat.parse(appUpgradeTime));
            currentDate=dateFormat.format(calendar1.getTime());
            calendar1.setTime(dateFormat.parse(currentDate));

            if (calendar.equals(calendar1))
                return true;
        }
        catch (Exception e){
            return true;
        }
        return false;
    }

    /*Manoj Says...
	*      checking market version of the app
	* */
    public class getAppMarketVersionAsync extends AsyncTask<String,Void,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return Util.getAppVersionFromMarket(params[0]);
        }

        @Override
        protected void onPostExecute(String version) {
            super.onPostExecute(version);

            String market_version=version;
            String app_version=Util.getAppVersionCode(SplashScreen.this);
            if(market_version==null){
                navigateToHome();
                return;
            }
            DecimalFormat df = new DecimalFormat("#.#");
            boolean upgradeFlag=false;
            double market_version_value=Double.parseDouble(market_version);
            double app_version_value=Double.parseDouble(app_version);

            if(market_version.equalsIgnoreCase(app_version))
            {
                upgradeFlag=false;

                navigateToHome();
            }
            else if(app_version_value<market_version_value)
            {
                upgradeFlag=true;
                Util.showAppUpgradeDialog(SplashScreen.this,"Application Update is available ",upgradeFlag);
            }

        }
    }
}
