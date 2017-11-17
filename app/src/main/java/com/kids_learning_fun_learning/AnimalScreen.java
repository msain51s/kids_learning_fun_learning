package com.kids_learning_fun_learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class AnimalScreen extends AppCompatActivity {

    TextView animalText,titleText;
    ImageView animal_image;
    View imageCard;
    int animal_img_arr[];
    String animal_name_arr[];
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Shape");

        animalText= (TextView) findViewById(R.id.animal_text);
        animal_image= (ImageView) findViewById(R.id.animal_img);
        imageCard=findViewById(R.id.animal_img_card);

        animal_name_arr=getResources().getStringArray(R.array.shape_name_arr);
        animal_img_arr=new int[]{R.drawable.circle_shape,R.drawable.triangle_shape,
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
        animalText.setText(animal_name_arr[count]);

        YoYo.with(Techniques.RubberBand)
                .duration(700)
                .playOn(animalText);

        animal_image.setImageResource(animal_img_arr[count]);

        YoYo.with(Techniques.RotateIn) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(animal_image);
    }

    public void performNextClick(View view){
        if(count==8)
            return;

        count++;
        animalText.setText(animal_name_arr[count]);
        YoYo.with(Techniques.RubberBand) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(animalText);

        animal_image.setImageResource(animal_img_arr[count]);
        YoYo.with(Techniques.RotateIn) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(animal_image);
    }

}
