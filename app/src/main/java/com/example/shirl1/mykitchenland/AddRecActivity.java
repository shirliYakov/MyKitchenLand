package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddRecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rec);
    }

    public void btn_add_re_On_Click(View v){

        //transfer info to database and then-
        Intent Go = new Intent(AddRecActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }
}
