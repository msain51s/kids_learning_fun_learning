package com.kids_learning_fun_learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kids_learning_fun_learning.utility.TypeWriterTextView;

public class LearningScreen extends AppCompatActivity {

    TextView alphabet_txt;
    ImageView name_image;
    TextView typeWriterTextView;
    Animation zoomout,slide_down_animation;
    String[] alphabet_arr;
    String[] name_arr;
    int[] image_arr;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        alphabet_txt= (TextView) findViewById(R.id.alphabet_text);
        name_image= (ImageView) findViewById(R.id.name_img);
        alphabet_arr=getResources().getStringArray(R.array.alphabet_arr);
        name_arr=getResources().getStringArray(R.array.name_arr);
        zoomout=AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        slide_down_animation=AnimationUtils.loadAnimation(this, R.anim.slide_down);
        typeWriterTextView= (TextView) findViewById(R.id.type_writer_text);

        typeWriterTextView.setText("APPLE");

        image_arr=new int[]{R.drawable.apple_icon,R.drawable.ball_icon,
                            R.drawable.cat_icon,R.drawable.dog_icon,
                            R.drawable.elephant_icon,R.drawable.fox_icon,
                            R.drawable.goat_icon,R.drawable.house_icon,
                            R.drawable.ice_cream_icon,R.drawable.jug_icon,
                            R.drawable.kite_icon,R.drawable.lion_icon,
                            R.drawable.monkey_icon,R.drawable.nest_icon,
                            R.drawable.orange_icon,R.drawable.parrot_icon,
                            R.drawable.queen_icon,R.drawable.rat_icon,
                            R.drawable.sun_icon,R.drawable.torch_icon,
                            R.drawable.umbrella_icon,R.drawable.volleyball_icon,
                            R.drawable.wolf_icon,R.drawable.xmas_icon,
                            R.drawable.yalk_icon,R.drawable.zebra_icon};

    }

    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
        alphabet_txt.setText(alphabet_arr[count]);
        zoomout=null;
        zoomout=AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        alphabet_txt.setAnimation(zoomout);
        name_image.setImageResource(image_arr[count]);
//        Glide.with(this).load(image_arr[count]).into(name_image);
        zoomout=null;
        zoomout=AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        name_image.setAnimation(zoomout);

        slide_down_animation=null;
        slide_down_animation=AnimationUtils.loadAnimation(this, R.anim.slide_down);
        typeWriterTextView.setText(name_arr[count]);
        typeWriterTextView.setAnimation(slide_down_animation);

    }

    public void performNextClick(View view){
        if(count==25)
            return;

        count++;
        alphabet_txt.setText(alphabet_arr[count]);
        zoomout=null;
        zoomout=AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        alphabet_txt.setAnimation(zoomout);
        name_image.setImageResource(image_arr[count]);
//        Glide.with(this).load(image_arr[count]).into(name_image);
        zoomout=null;
        zoomout=AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        name_image.setAnimation(zoomout);
        slide_down_animation=null;
        slide_down_animation=AnimationUtils.loadAnimation(this, R.anim.slide_down);
        typeWriterTextView.setText(name_arr[count]);
        typeWriterTextView.setAnimation(slide_down_animation);
    }
}
