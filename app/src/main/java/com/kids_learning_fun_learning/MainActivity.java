package com.kids_learning_fun_learning;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity {

    TextView learn_alphabet;
    Animation pulse_animation,zoomOutAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pulse_animation=AnimationUtils.loadAnimation(this,R.anim.pulse);
        zoomOutAnimation= AnimationUtils.loadAnimation(this,R.anim.zoom_out);

        learn_alphabet= (TextView) findViewById(R.id.learn_btn);
        String text="Learn"+"\n"+"Alphabets";
        learn_alphabet.setText(text);

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

    }

    public void performLearnClick(View view){
        Intent intent=new Intent(this,LearningScreen.class);
        startActivity(intent);
    }
}
