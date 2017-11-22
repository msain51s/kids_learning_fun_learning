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

public class FruitScreen extends AppCompatActivity implements TextToSpeech.OnInitListener{

    TextView fruitText,titleText;
    ImageView fruit_image;
    View imageCard;
    int fruit_img_arr[];
    String fruit_name_arr[];
    int count=0;

    TextToSpeech tts;
    ImageView speakerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_screen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Fruits");

        fruitText= (TextView) findViewById(R.id.fruit_text);
        fruit_image= (ImageView) findViewById(R.id.fruit_img);
        imageCard=findViewById(R.id.fruit_img_card);
        speakerBtn= (ImageView) findViewById(R.id.speaker_icon);

        fruit_name_arr=getResources().getStringArray(R.array.fruits_name_arr);
        fruit_img_arr=new int[]{R.drawable.apple_icon,R.drawable.banana_icon,
                R.drawable.blueberry_icon,R.drawable.cherry_icon,
                R.drawable.graps_icon,R.drawable.lemon_icon,
                R.drawable.mango_icon,R.drawable.orange_icon,
                R.drawable.pear_icon,R.drawable.peach_icon,
                R.drawable.papaya,R.drawable.pineapple_icon,
                R.drawable.strawberry_icon
        };

     tts=new TextToSpeech(this,this);
    }

    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
        fruitText.setText(fruit_name_arr[count]);

        YoYo.with(Techniques.Wobble)
                .duration(700)
                .playOn(fruitText);

        fruit_image.setImageResource(fruit_img_arr[count]);

        YoYo.with(Techniques.Wave) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(fruit_image);

        Util.speakOut(tts,fruit_name_arr[count]);
    }

    public void performNextClick(View view){
        if(count==12)
            return;

        count++;
        fruitText.setText(fruit_name_arr[count]);
        YoYo.with(Techniques.Wobble) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(fruitText);

        fruit_image.setImageResource(fruit_img_arr[count]);
        YoYo.with(Techniques.Wave) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(fruit_image);

        Util.speakOut(tts,fruit_name_arr[count]);
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

        Util.speakOut(tts,fruit_name_arr[count]);
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
                Util.speakOut(tts,fruit_name_arr[count]);

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

