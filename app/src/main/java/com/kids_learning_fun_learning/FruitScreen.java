package com.kids_learning_fun_learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class FruitScreen extends AppCompatActivity {

    TextView fruitText,titleText;
    ImageView fruit_image;
    View imageCard;
    int fruit_img_arr[];
    String fruit_name_arr[];
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_screen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Fruits");

        fruitText= (TextView) findViewById(R.id.fruit_text);
        fruit_image= (ImageView) findViewById(R.id.fruit_img);
        imageCard=findViewById(R.id.fruit_img_card);

        fruit_name_arr=getResources().getStringArray(R.array.fruits_name_arr);
        fruit_img_arr=new int[]{R.drawable.apple_icon,R.drawable.banana_icon,
                R.drawable.blueberry_icon,R.drawable.cherry_icon,
                R.drawable.graps_icon,R.drawable.lemon_icon,
                R.drawable.mango_icon,R.drawable.orange_icon,
                R.drawable.pear_icon,R.drawable.peach_icon,
                R.drawable.papaya,R.drawable.pineapple_icon,
                R.drawable.strawberry_icon
        };


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
    }

}

