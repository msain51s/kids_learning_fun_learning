package com.kids_learning_fun_learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class ShapeScreen extends AppCompatActivity {

    TextView shapeText,titleText;
    ImageView shape_image;
    View imageCard;
    int shape_img_arr[];
    String shape_name_arr[];
    int count=0;
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

        shape_name_arr=getResources().getStringArray(R.array.shape_name_arr);
        shape_img_arr=new int[]{R.drawable.circle_shape,R.drawable.triangle_shape,
                R.drawable.rectangle_shape,R.drawable.square_shape,
                R.drawable.pentagon_shape,R.drawable.hexagon_shape,
                R.drawable.star_shape,R.drawable.heart_shape,
                R.drawable.cube_shape
               };


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
