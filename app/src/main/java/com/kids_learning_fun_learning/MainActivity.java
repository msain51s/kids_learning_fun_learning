package com.kids_learning_fun_learning;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;

import com.kids_learning_fun_learning.utility.AppRater;

public class MainActivity extends AppCompatActivity {

    ImageView learn_alphabet;
    TextView titleText;
    Animation pulse_animation,zoomOutAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText(getResources().getText(R.string.app_name));

        pulse_animation=AnimationUtils.loadAnimation(this,R.anim.pulse);
        zoomOutAnimation= AnimationUtils.loadAnimation(this,R.anim.zoom_out);

        learn_alphabet= (ImageView) findViewById(R.id.learn_letters_btn);
      /*  String text="Learn"+"\n"+"Alphabets";
        learn_alphabet.setText(text);*/

        zoomOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                learn_alphabet.setAnimation(pulse_animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        learn_alphabet.setAnimation(zoomOutAnimation);

        AppRater.app_launched(this);

    }

    public void performLearnClick(View view){
        Intent intent=new Intent(this,LearningScreen.class);
        startActivity(intent);
    }

    public void performNumberClick(View view){
        Intent intent=new Intent(this,NumberScreen.class);
        startActivity(intent);
    }

    public void performShapeClick(View view){
        Intent intent=new Intent(this,ShapeScreen.class);
        startActivity(intent);
    }

    public void performColorClick(View view){
        Intent intent=new Intent(this,ColorScreen.class);
        startActivity(intent);
    }

    public void performAnimalClick(View view){
        Intent intent=new Intent(this,AnimalScreen.class);
        startActivity(intent);
    }

    public void performFruitClick(View view){
        Intent intent=new Intent(this,FruitScreen.class);
        startActivity(intent);
    }
}
