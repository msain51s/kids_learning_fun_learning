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

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kids_learning_fun_learning.utility.AppRater;

public class MainActivity extends AppCompatActivity {

    ImageView learn_alphabet,numberView,fruiteView,
                vegetableView,shapeView,colorView,
               dayView,monthView,solarSystemView,bodyPartView;
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
        numberView= (ImageView) findViewById(R.id.learn_numbers_btn);
        fruiteView= (ImageView) findViewById(R.id.learn_fruit_btn);
        vegetableView= (ImageView) findViewById(R.id.learn_vegetable_btn);
        shapeView= (ImageView) findViewById(R.id.learn_shapes_btn);
        colorView= (ImageView) findViewById(R.id.learn_colors_btn);
        dayView= (ImageView) findViewById(R.id.learn_weekday_btn);
        monthView= (ImageView) findViewById(R.id.learn_month_name_btn);
        solarSystemView= (ImageView) findViewById(R.id.learn_solar_system_btn);
        bodyPartView= (ImageView) findViewById(R.id.learn_body_parts_name_btn);


        YoYo.with(Techniques.ZoomIn) // Tada is a Animation type.<br />
                .duration(500)
                .playOn(learn_alphabet);

        YoYo.with(Techniques.ZoomIn) // Tada is a Animation type.<br />
                .duration(1000)
                .delay(500)
                .playOn(numberView);
        YoYo.with(Techniques.ZoomIn) // Tada is a Animation type.<br />
                .duration(500)
                .delay(1000)
                .playOn(fruiteView);
        YoYo.with(Techniques.ZoomIn) // Tada is a Animation type.<br />
                .duration(500)
                .delay(1500)
                .playOn(vegetableView);
        YoYo.with(Techniques.ZoomIn) // Tada is a Animation type.<br />
                .duration(500)
                .delay(2000)
                .playOn(shapeView);
        YoYo.with(Techniques.ZoomIn) // Tada is a Animation type.<br />
                .duration(500)
                .delay(2500)
                .playOn(colorView);
        YoYo.with(Techniques.ZoomIn) // Tada is a Animation type.<br />
                .duration(500)
                .delay(3000)
                .playOn(dayView);
        YoYo.with(Techniques.ZoomIn) // Tada is a Animation type.<br />
                .duration(500)
                .delay(3500)
                .playOn(monthView);
        YoYo.with(Techniques.ZoomIn) // Tada is a Animation type.<br />
                .duration(500)
                .delay(4000)
                .playOn(solarSystemView);
        YoYo.with(Techniques.ZoomIn) // Tada is a Animation type.<br />
                .duration(500)
                .delay(4500)
                .playOn(bodyPartView);

      /*  String text="Learn"+"\n"+"Alphabets";
        learn_alphabet.setText(text);*/

        /*zoomOutAnimation.setAnimationListener(new Animation.AnimationListener() {
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

        learn_alphabet.setAnimation(zoomOutAnimation);*/

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

    public void performVegetableClick(View view){
        Intent intent=new Intent(this,VegetableScreen.class);
        startActivity(intent);
    }

    public void performWeekDayClick(View view){
        Intent intent=new Intent(this,DaysOfWeekScreen.class);
        startActivity(intent);
    }

    public void performMonthClick(View view){
        Intent intent=new Intent(this,MonthOfYearScreen.class);
        startActivity(intent);
    }

    public void performSolarSystemClick(View view){
        Intent intent=new Intent(this,SolarSystemScreen.class);
        startActivity(intent);
    }

    public void performBodyPartsClick(View view){
        Intent intent=new Intent(this,BodyPartScreen.class);
        startActivity(intent);
    }
}
