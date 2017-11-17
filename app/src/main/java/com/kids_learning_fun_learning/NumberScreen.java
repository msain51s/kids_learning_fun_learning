package com.kids_learning_fun_learning;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class NumberScreen extends AppCompatActivity {

    TextView titleText,number_spelling_text,number_text;
    String[] number_string_arr;
    int[] number_int_arr;

    Animation zoomout;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Numbers");

        number_spelling_text= (TextView) findViewById(R.id.number_spelling_text);
        number_text= (TextView) findViewById(R.id.number_text);

        zoomout= AnimationUtils.loadAnimation(this, R.anim.zoom_out);

        number_string_arr=getResources().getStringArray(R.array.numbers_string_arr);
        number_int_arr=getResources().getIntArray(R.array.numbers_int_arr);
    }

    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
        number_text.setText(""+number_int_arr[count]);
        zoomout=null;
        zoomout=AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        number_text.setAnimation(zoomout);

        number_spelling_text.setText(number_string_arr[count]);
        YoYo.with(Techniques.Tada) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(number_spelling_text);

    }

    public void performNextClick(View view){
        if(count==19)
            return;

        count++;
        number_text.setText(""+number_int_arr[count]);
        zoomout=null;
        zoomout=AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        number_text.setAnimation(zoomout);

        number_spelling_text.setText(number_string_arr[count]);
        YoYo.with(Techniques.Tada) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(number_spelling_text);

    }

    public void animateTextView(int initialValue, int finalValue, final TextView  textview) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(initialValue, finalValue);
        valueAnimator.setDuration(1500);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                textview.setText(valueAnimator.getAnimatedValue().toString());

            }
        });
        valueAnimator.start();

    }
}
