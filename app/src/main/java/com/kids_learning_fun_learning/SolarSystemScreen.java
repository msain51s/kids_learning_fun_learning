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

public class SolarSystemScreen extends AppCompatActivity implements TextToSpeech.OnInitListener{
    TextView solarPlanetText,titleText;
    ImageView solarPlanet_image;
    View imageCard;
    int solarPlanet_img_arr[];
    String solarPlanet_name_arr[];
    int count=0;

    TextToSpeech tts;
    ImageView speakerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_system_screen);
        Util.setupInerstitialAd(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Solar System");

        solarPlanetText= (TextView) findViewById(R.id.solar_text);
        solarPlanet_image= (ImageView) findViewById(R.id.solar_img);
        imageCard=findViewById(R.id.solar_img_card);
        speakerBtn= (ImageView) findViewById(R.id.speaker_icon);

        solarPlanet_name_arr=getResources().getStringArray(R.array.solar_system_arr);
        solarPlanet_img_arr=new int[]{R.drawable.sun_icon1,R.drawable.mercury_icon,
                R.drawable.venus_icon,R.drawable.earth_icon,
                R.drawable.mars_icon,R.drawable.jupiter_icon,
                R.drawable.saturn_icon,R.drawable.uranus_icon,
                R.drawable.neptune_icon
        };

      tts=new TextToSpeech(this,this);
    }

    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
        solarPlanetText.setText(solarPlanet_name_arr[count]);

        YoYo.with(Techniques.Wobble)
                .duration(700)
                .playOn(solarPlanetText);

        solarPlanet_image.setImageResource(solarPlanet_img_arr[count]);

        YoYo.with(Techniques.ZoomIn) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(solarPlanet_image);

        Util.speakOut(tts,solarPlanet_name_arr[count]);
    }

    public void performNextClick(View view){
        if(count==8)
            return;

        count++;
        solarPlanetText.setText(solarPlanet_name_arr[count]);
        YoYo.with(Techniques.Wobble) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(solarPlanetText);

        solarPlanet_image.setImageResource(solarPlanet_img_arr[count]);
        YoYo.with(Techniques.ZoomIn) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(solarPlanet_image);

        Util.speakOut(tts,solarPlanet_name_arr[count]);
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

        Util.speakOut(tts,solarPlanet_name_arr[count]);
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
                Util.speakOut(tts,solarPlanet_name_arr[count]);

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

