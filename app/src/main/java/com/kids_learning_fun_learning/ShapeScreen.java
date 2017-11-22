package com.kids_learning_fun_learning;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kids_learning_fun_learning.utility.Util;

import java.util.Locale;

public class ShapeScreen extends AppCompatActivity implements TextToSpeech.OnInitListener{

    TextView shapeText,titleText;
    ImageView shape_image;
    View imageCard;
    int shape_img_arr[];
    String shape_name_arr[];
    int count=0;

    TextToSpeech tts;
    ImageView speakerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Shape");

        shapeText= (TextView) findViewById(R.id.shape_text);
        shape_image= (ImageView) findViewById(R.id.shape_img);
        imageCard=findViewById(R.id.shape_img_card);
        speakerBtn= (ImageView) findViewById(R.id.speaker_icon);

        shape_name_arr=getResources().getStringArray(R.array.shape_name_arr);
        shape_img_arr=new int[]{R.drawable.circle_shape,R.drawable.triangle_shape,
                R.drawable.rectangle_shape,R.drawable.square_shape,
                R.drawable.pentagon_shape,R.drawable.hexagon_shape,
                R.drawable.star_shape,R.drawable.heart_shape,
                R.drawable.cube_shape
               };

        tts=new TextToSpeech(this,this);
    }

    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
        shapeText.setText(shape_name_arr[count]);

        YoYo.with(Techniques.RubberBand)
                .duration(700)
                .playOn(shapeText);

        shape_image.setImageResource(shape_img_arr[count]);

        YoYo.with(Techniques.FlipInX) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(shape_image);

        Util.speakOut(tts,shape_name_arr[count]);
    }

    public void performNextClick(View view){
        if(count==8)
            return;

        count++;
        shapeText.setText(shape_name_arr[count]);
        YoYo.with(Techniques.RubberBand) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(shapeText);

        shape_image.setImageResource(shape_img_arr[count]);
        YoYo.with(Techniques.FlipInX) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(shape_image);

        Util.speakOut(tts,shape_name_arr[count]);
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

        Util.speakOut(tts,shape_name_arr[count]);
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
                Util.speakOut(tts,shape_name_arr[count]);

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
