package com.kids_learning_fun_learning;

import android.content.Intent;
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
    Animation pulse_animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        pulse_animation=AnimationUtils.loadAnimation(this,R.anim.pulse);

        learn_alphabet= (TextView) findViewById(R.id.learn_btn);
        String text="Learn"+"\n"+"Alphabets";
        learn_alphabet.setText(text);

        learn_alphabet.setAnimation(pulse_animation);
    }

    public void performLearnClick(View view){
        Intent intent=new Intent(this,LearningScreen.class);
        startActivity(intent);
    }
}
