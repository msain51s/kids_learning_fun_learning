package com.kids_learning_fun_learning;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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

public class ColorScreen extends AppCompatActivity implements TextToSpeech.OnInitListener{

    TextView titleText,colorText;
    CardView colorCard;

    String color_name_arr[];
    int color_arr[];
    int count=0;

    TextToSpeech tts;
    ImageView speakerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Color");

        colorText= (TextView) findViewById(R.id.color_text);
        colorCard= (CardView) findViewById(R.id.color_img_card);
        speakerBtn= (ImageView) findViewById(R.id.speaker_icon);

        color_name_arr=getResources().getStringArray(R.array.color_name_arr);
        color_arr=new int[]{R.color.red,R.color.green,R.color.blue,
                            R.color.yellow,R.color.white,R.color.black,
                            R.color.pink,R.color.orange,
                            R.color.purple,R.color.magenta};

        tts=new TextToSpeech(this,this);
    }

    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
        colorText.setText(color_name_arr[count]);

        YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(colorText);

        colorCard.setCardBackgroundColor(getResources().getColor(color_arr[count]));

        YoYo.with(Techniques.FadeIn) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(colorCard);

        Util.speakOut(tts,color_name_arr[count]);
    }

    public void performNextClick(View view){
        if(count==9)
            return;

        count++;
        colorText.setText(color_name_arr[count]);
        YoYo.with(Techniques.Landing) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(colorText);

        colorCard.setCardBackgroundColor(getResources().getColor(color_arr[count]));
        YoYo.with(Techniques.FadeIn) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(colorCard);

        Util.speakOut(tts,color_name_arr[count]);
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

        Util.speakOut(tts,color_name_arr[count]);
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
                Util.speakOut(tts,color_name_arr[count]);

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
