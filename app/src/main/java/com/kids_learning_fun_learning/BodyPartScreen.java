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

import java.util.Locale;

public class BodyPartScreen extends AppCompatActivity implements TextToSpeech.OnInitListener{

    TextView titleText;
    String body_part_name_arr[];
    int count;

    private TextToSpeech tts;
    private TextView btnSpeak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_part_screen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Body Parts");
        tts = new TextToSpeech(this, this);

        body_part_name_arr=getResources().getStringArray(R.array.body_parts_name_arr);
    }

    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
  //      solarPlanetText.setText(solarPlanet_name_arr[count]);
        speakOut(body_part_name_arr[count]);
    }

    public void performNextClick(View view){
        if(count==16)
            return;

        count++;
    //    solarPlanetText.setText(solarPlanet_name_arr[count]);
        speakOut(body_part_name_arr[count]);
    }


    private void speakOut(String text) {

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
          //      btnSpeak.setEnabled(true);
                speakOut(body_part_name_arr[count]);

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
}
