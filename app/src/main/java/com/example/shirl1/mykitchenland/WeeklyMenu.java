package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class WeeklyMenu extends AppCompatActivity {

    DBHandler db;
    EditText day1;
    EditText day2;
    EditText day3;
    EditText day4;
    EditText day5;
    EditText day6;
    EditText day7;

    String day_1;
    String day_2;
    String day_3;
    String day_4;
    String day_5;
    String day_6;
    String day_7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName +",");
        setContentView(R.layout.activity_weekly_menu);

        db = new DBHandler(this);
        day1 = (EditText)findViewById(R.id.txt_sunday);
        day2 = (EditText)findViewById(R.id.txt_munday);
        day3 = (EditText)findViewById(R.id.txt_thusday);
        day4 = (EditText)findViewById(R.id.txt_wedensday);
        day5 = (EditText)findViewById(R.id.txt_thursday);
        day6 = (EditText)findViewById(R.id.txt_friday);
        day7 = (EditText)findViewById(R.id.txt_saterday);

        Week w = db.getWeek();
        if(w!=null){

            day1.setText(w.getDay1());
            day2.setText(w.getDay2());
            day3.setText(w.getDay3());
            day4.setText(w.getDay4());
            day5.setText(w.getDay5());
            day6.setText(w.getDay6());
            day7.setText(w.getDay7());
        }
    }

    public void btn_back_On_Click(View view) {
        Intent menu = new Intent(this, MainMenu.class);
        startActivity(menu);
    }


    public void btn_delete_menu_On_Click(View view) {
        db.clearTableWeek();
        Intent refresh = new Intent(this, WeeklyMenu.class);
        startActivity(refresh);
        this.finish();
    }

    public void SetContent(View view){
        day_1 = day1.getText().toString();
        day_2 = day2.getText().toString();
        day_3 = day3.getText().toString();
        day_4 = day4.getText().toString();
        day_5 = day5.getText().toString();
        day_6 = day6.getText().toString();
        day_7 = day7.getText().toString();

        Week week = new Week(day_1, day_2, day_3, day_4, day_5, day_6, day_7);
        db.setWeek(week);
    }
}
