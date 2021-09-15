package com.kids_learning_fun_learning;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kids_learning_fun_learning.utility.Util;

import java.util.Locale;

public class MonthOfYearScreen extends AppCompatActivity implements TextToSpeech.OnInitListener{
    TextView titleText,monthText;
    String[] month_name_arr;
    int count=0;

    TextToSpeech tts;
    ImageView speakerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_of_year_screen);

        Util.setupInerstitialAd(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Months Of Year");
        monthText= (TextView) findViewById(R.id.month_text);
        speakerBtn= (ImageView) findViewById(R.id.speaker_icon);

        month_name_arr=getResources().getStringArray(R.array.months_name_arr);

        tts=new TextToSpeech(this,this);
    }

    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
        monthText.setText(month_name_arr[count]);

        YoYo.with(Techniques.StandUp)
                .duration(700)
                .playOn(monthText);

        Util.speakOut(tts,month_name_arr[count]);
    }

    public void performNextClick(View view){
        if(count==11)
            return;

        count++;
        monthText.setText(month_name_arr[count]);
        YoYo.with(Techniques.StandUp) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(monthText);

        Util.speakOut(tts,month_name_arr[count]);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void performSpeakerClick(View view){

        Util.speakOut(tts,month_name_arr[count]);
    }
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakerBtn.setEnabled(true);
                Util.speakOut(tts,month_name_arr[count]);

                Log.e("TTS", "Speech done");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
