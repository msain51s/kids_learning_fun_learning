package com.kids_learning_fun_learning;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.kids_learning_fun_learning.utility.Util;

import java.util.Locale;

public class LearningScreen extends AppCompatActivity implements TextToSpeech.OnInitListener{

    TextView alphabet_txt,titleText;
    ImageView name_image;
    TextView typeWriterTextView;
    Animation zoomout,slide_down_animation;
    String[] alphabet_arr;
    String[] name_arr;
    int[] image_arr;
    int count=0;

    private TextToSpeech tts;
    ImageView speakerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Alphabet");


        alphabet_txt= (TextView) findViewById(R.id.alphabet_text);
        name_image= (ImageView) findViewById(R.id.name_img);
        alphabet_arr=getResources().getStringArray(R.array.alphabet_arr);
        name_arr=getResources().getStringArray(R.array.name_arr);
        zoomout=AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        slide_down_animation=AnimationUtils.loadAnimation(this, R.anim.slide_down);
        typeWriterTextView= (TextView) findViewById(R.id.type_writer_text);
        speakerBtn= (ImageView) findViewById(R.id.speaker_icon);

        typeWriterTextView.setText("APPLE");

        image_arr=new int[]{R.drawable.apple_icon,R.drawable.ball_icon,
                            R.drawable.cat_icon,R.drawable.dog_icon,
                            R.drawable.elephant_icon,R.drawable.fox_icon,
                            R.drawable.goat_icon,R.drawable.house_icon,
                            R.drawable.ice_cream_icon,R.drawable.jug_icon,
                            R.drawable.kite_icon,R.drawable.lion_icon,
                            R.drawable.monkey_icon,R.drawable.nest_icon,
                            R.drawable.orange_icon,R.drawable.parrot_icon,
                            R.drawable.queen_icon,R.drawable.rat_icon,
                            R.drawable.sun_icon1,R.drawable.torch_icon,
                            R.drawable.umbrella_icon,R.drawable.volleyball_icon,
                            R.drawable.wolf_icon,R.drawable.xmas_icon,
                            R.drawable.yalk_icon,R.drawable.zebra_icon};

        tts=new TextToSpeech(this,this);

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
    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
        alphabet_txt.setText(alphabet_arr[count]);
        zoomout=null;
        zoomout=AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        alphabet_txt.setAnimation(zoomout);
        name_image.setImageResource(image_arr[count]);
//        Glide.with(this).load(image_arr[count]).into(name_image);
        zoomout=null;
        zoomout=AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        name_image.setAnimation(zoomout);

        slide_down_animation=null;
        slide_down_animation=AnimationUtils.loadAnimation(this, R.anim.slide_down);
        typeWriterTextView.setText(name_arr[count]);
        typeWriterTextView.setAnimation(slide_down_animation);

        Util.speakOut(tts,name_arr[count]);
    }

    public void performNextClick(View view){
        if(count==25)
            return;

        count++;
        alphabet_txt.setText(alphabet_arr[count]);
        zoomout=null;
        zoomout=AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        alphabet_txt.setAnimation(zoomout);
        name_image.setImageResource(image_arr[count]);
//        Glide.with(this).load(image_arr[count]).into(name_image);
        zoomout=null;
        zoomout=AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        name_image.setAnimation(zoomout);
        slide_down_animation=null;
        slide_down_animation=AnimationUtils.loadAnimation(this, R.anim.slide_down);
        typeWriterTextView.setText(name_arr[count]);
        typeWriterTextView.setAnimation(slide_down_animation);

        Util.speakOut(tts,name_arr[count]);
    }

    public void performSpeakerClick(View view){

        Util.speakOut(tts,name_arr[count]);
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
                Util.speakOut(tts,name_arr[count]);

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
