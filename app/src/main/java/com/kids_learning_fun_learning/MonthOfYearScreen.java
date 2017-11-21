package com.kids_learning_fun_learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MonthOfYearScreen extends AppCompatActivity {
    TextView titleText,monthText;
    String[] month_name_arr;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_of_year_screen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        titleText= (TextView) findViewById(R.id.toolbar_title_text);
        titleText.setText("Months Of Year");

        monthText= (TextView) findViewById(R.id.month_text);
        month_name_arr=getResources().getStringArray(R.array.months_name_arr);
    }

    public void performPreviousClick(View view){
        if(count==0)
            return;

        count--;
        monthText.setText(month_name_arr[count]);

        YoYo.with(Techniques.StandUp)
                .duration(700)
                .playOn(monthText);
    }

    public void performNextClick(View view){
        if(count==11)
            return;

        count++;
        monthText.setText(month_name_arr[count]);
        YoYo.with(Techniques.StandUp) // Tada is a Animation type.<br />
                .duration(700)
                .playOn(monthText);
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
