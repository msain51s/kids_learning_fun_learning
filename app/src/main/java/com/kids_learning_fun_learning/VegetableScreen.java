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

public class VegetableScreen extends AppCompatActivity implements TextToSpeech.OnInitListener{
    TextView vegetableText,titleText;
    ImageView vegetable_image;
    View imageCard;
    int vegetable_img_arr[];
    String vegetable_name_arr[];
    int count=0;

    TextToSpeech tts;
    ImageView speakerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_screen);

        Util.setupInerstitialAd(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Vegetables");

        vegetableText= (TextView) findViewById(R.id.vegetable_text);
        vegetable_image= (ImageView) findViewById(R.id.vegetable_img);
        imageCard=findViewById(R.id.vegetable_img_card);
        speakerBtn= (ImageView) findViewById(R.id.speaker_icon);

        vegetable_name_arr=getResources().getStringArray(R.array.vegetable_name_arr);
        vegetable_img_arr=new int[]{R.drawable.brinjal_icon,R.drawable.broccoli,
                R.drawable.cabbage,R.drawable.capcicum_icon,
                R.drawable.carrot_icon,R.drawable.cauliflower_icon,
                R.drawable.cucumber_icon,R.drawable.onion_icon,
                R.drawable.pea_icon,R.drawable.potato_icon,
                R.drawable.tomato_icon
        };

      tts=new TextToSpeech(this,this);
    }

    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
        vegetableText.setText(vegetable_name_arr[count]);

        YoYo.with(Techniques.Wobble)
                .duration(700)
                .playOn(vegetableText);

        vegetable_image.setImageResource(vegetable_img_arr[count]);

        YoYo.with(Techniques.Wave) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(vegetable_image);

        Util.speakOut(tts,vegetable_name_arr[count]);
    }

    public void performNextClick(View view){
        if(count==10)
            return;

        count++;
        vegetableText.setText(vegetable_name_arr[count]);
        YoYo.with(Techniques.Wobble) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(vegetableText);

        vegetable_image.setImageResource(vegetable_img_arr[count]);
        YoYo.with(Techniques.Wave) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(vegetable_image);

        Util.speakOut(tts,vegetable_name_arr[count]);
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

        Util.speakOut(tts,vegetable_name_arr[count]);
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
                Util.speakOut(tts,vegetable_name_arr[count]);

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

