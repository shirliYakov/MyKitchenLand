package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class DisplayMenuActivity extends AppCompatActivity {

    TextView print;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName +",");
        setContentView(R.layout.activity_display_menu);

        print = (TextView)findViewById(R.id.showMenu);
        db = new DBHandler(this);

        //print table
        String log ="";
        Week w = db.getWeek();
        log = log +  "יום ראשון:" + "\n" + w.getDay1() + "\n\n" + "יום שני:" + "\n"+ w.getDay2() + "\n\n" +
                "יום שלישי:" + "\n" + w.getDay3() + "\n\n" + "יום רביעי:"+ "\n" + w.getDay4() + "\n\n" +
                "יום חמישי:" + "\n"+ w.getDay5() + "\n\n" + "יום שישי:" + "\n"+ w.getDay6() + "\n\n" +
                "יום שבת:" + "\n"+ w.getDay7();
        print.setText(log);

    }

    public void btn_back_weekmenu_On_Click(View v){
        Intent Go = new Intent(DisplayMenuActivity.this, WeeklyMenu.class);
        startActivity(Go);
    }

    public void btn_back_On_Click(View v){
        Intent Go = new Intent(DisplayMenuActivity.this, MainMenu.class);
        startActivity(Go);
    }

}
