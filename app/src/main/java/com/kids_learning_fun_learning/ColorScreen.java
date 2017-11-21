package com.kids_learning_fun_learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class ColorScreen extends AppCompatActivity {

    TextView titleText,colorText;
    CardView colorCard;

    String color_name_arr[];
    int color_arr[];
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Color");

        colorText= (TextView) findViewById(R.id.color_text);
        colorCard= (CardView) findViewById(R.id.color_img_card);

        color_name_arr=getResources().getStringArray(R.array.color_name_arr);
        color_arr=new int[]{R.color.red,R.color.green,R.color.blue,
                            R.color.yellow,R.color.white,R.color.black,
                            R.color.pink,R.color.orange,
                            R.color.purple,R.color.magenta};
    }

    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
        colorText.setText(color_name_arr[count]);

        YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(colorText);

        colorCard.setCardBackgroundColor(getResources().getColor(color_arr[count]));

        YoYo.with(Techniques.FadeIn) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(colorCard);
    }

    public void performNextClick(View view){
        if(count==9)
            return;

        count++;
        colorText.setText(color_name_arr[count]);
        YoYo.with(Techniques.Landing) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(colorText);

        colorCard.setCardBackgroundColor(getResources().getColor(color_arr[count]));
        YoYo.with(Techniques.FadeIn) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(colorCard);
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
