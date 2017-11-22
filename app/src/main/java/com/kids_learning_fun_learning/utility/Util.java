package com.kids_learning_fun_learning.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import com.kids_learning_fun_learning.R;
import com.kids_learning_fun_learning.SplashScreen;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 11/10/2017.
 */

public class Util {
    static String dateString=null;

    public static boolean ChechInternetAvalebleOrNot(Activity act) {

        ConnectivityManager connectivityManager = (ConnectivityManager) act
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showAppUpgradeDialog(final Activity ctx, String msg, boolean upgrade) {

        final Preferences per = new Preferences(ctx);
        final Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,2);
        final Date date=calendar.getTime();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");

        try{
            dateString=dateFormat.format(date);
        }
        catch (Exception e){

        }

        SweetAlertDialog dialog = new SweetAlertDialog(ctx, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        dialog.setTitleText("Upgrade Application");
        dialog.setContentText(msg);
        dialog.setCancelText("Later");
        dialog.setConfirmText("Upgrade");
        dialog.setCustomImage(ctx.getResources().getDrawable(R.drawable.app_upgrade_icon));
        dialog.setCancelable(false);
        dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismissWithAnimation();
                ((SplashScreen) ctx).navigateToHome();
                per.setAppUpgradeTime(dateString);

            }
        });
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismissWithAnimation();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.kids_learning_fun_learning"));
                ctx.startActivity(intent);
            }
        })
                .show();


    }
    public static String getAppVersionFromMarket(String playUrl) {

        HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties props = cleaner.getProperties();
        props.setAllowHtmlInsideAttributes(true);
        props.setAllowMultiWordAttributes(true);
        props.setRecognizeUnicodeChars(true);
        props.setOmitComments(true);
        try {
            URL url = new URL(playUrl);
            URLConnection conn = url.openConnection();
            TagNode node = cleaner.clean(new InputStreamReader(conn.getInputStream()));
            Object[] new_nodes = node.evaluateXPath("//*[@class='recent-change']");
            Object[] version_nodes = node.evaluateXPath("//*[@itemprop='softwareVersion']");

            String version = "", whatsNew = "";
            for (Object new_node : new_nodes) {
                TagNode info_node = (TagNode) new_node;
                whatsNew += info_node.getAllChildren().get(0).toString().trim()
                        + "\n";
            }
            if (version_nodes.length > 0) {
                TagNode ver = (TagNode) version_nodes[0];
                version = ver.getAllChildren().get(0).toString().trim();
            }

            //   return new String[]{version, whatsNew};
            return version;

        } catch (IOException | XPatherException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAppVersionCode(Context ctx) {

        PackageInfo pInfo = null;
        try {
            pInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            return "" + pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


  /*SPEAK OUT FUNCTION*/

    public static void speakOut(TextToSpeech tts,String text) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //    Log.v(TAG, "Speak new API");
            Bundle bundle = new Bundle();
            bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_MUSIC);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, bundle, null);
        } else {
            //     Log.v(TAG, "Speak old API");
            HashMap<String, String> param = new HashMap<>();
            param.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_MUSIC));
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, param);
        }

        //      tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}
