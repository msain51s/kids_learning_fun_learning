package com.kids_learning_fun_learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class VegetableScreen extends AppCompatActivity {
    TextView vegetableText,titleText;
    ImageView vegetable_image;
    View imageCard;
    int vegetable_img_arr[];
    String vegetable_name_arr[];
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_screen);

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

        vegetable_name_arr=getResources().getStringArray(R.array.vegetable_name_arr);
        vegetable_img_arr=new int[]{R.drawable.brinjal_icon,R.drawable.broccoli,
                R.drawable.cabbage,R.drawable.capcicum_icon,
                R.drawable.carrot_icon,R.drawable.cauliflower_icon,
                R.drawable.cucumber_icon,R.drawable.onion_icon,
                R.drawable.pea_icon,R.drawable.potato_icon,
                R.drawable.tomato_icon
        };


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

}

