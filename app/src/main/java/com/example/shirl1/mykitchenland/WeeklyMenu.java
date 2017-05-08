package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class WeeklyMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_menu);
    }

    public void MenuLayout(View view)
    {
        Intent menu = new Intent(this, MainMenu.class);
        startActivity(menu);
    }

    public void SetContent(View view){


        //set to all edittext;
    }
}
