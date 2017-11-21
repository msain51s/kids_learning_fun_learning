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

public class SolarSystemScreen extends AppCompatActivity {
    TextView solarPlanetText,titleText;
    ImageView solarPlanet_image;
    View imageCard;
    int solarPlanet_img_arr[];
    String solarPlanet_name_arr[];
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_system_screen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Solar System");

        solarPlanetText= (TextView) findViewById(R.id.solar_text);
        solarPlanet_image= (ImageView) findViewById(R.id.solar_img);
        imageCard=findViewById(R.id.solar_img_card);

        solarPlanet_name_arr=getResources().getStringArray(R.array.solar_system_arr);
        solarPlanet_img_arr=new int[]{R.drawable.sun_icon,R.drawable.mercury_icon,
                R.drawable.venus_icon,R.drawable.earth_icon,
                R.drawable.mars_icon,R.drawable.jupiter_icon,
                R.drawable.saturn_icon,R.drawable.uranus_icon,
                R.drawable.neptune_icon
        };


    }

    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
        solarPlanetText.setText(solarPlanet_name_arr[count]);

        YoYo.with(Techniques.Wobble)
                .duration(700)
                .playOn(solarPlanetText);

        solarPlanet_image.setImageResource(solarPlanet_img_arr[count]);

        YoYo.with(Techniques.Wave) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(solarPlanet_image);
    }

    public void performNextClick(View view){
        if(count==8)
            return;

        count++;
        solarPlanetText.setText(solarPlanet_name_arr[count]);
        YoYo.with(Techniques.Wobble) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(solarPlanetText);

        solarPlanet_image.setImageResource(solarPlanet_img_arr[count]);
        YoYo.with(Techniques.Wave) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(solarPlanet_image);
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

