package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowRecipActivity extends AppCompatActivity {


    TextView name;
    TextView ingredient;
    TextView instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recip);

        name = (TextView) findViewById(R.id.list_name);
        ingredient = (TextView) findViewById(R.id.input_ingredient);
        instructions = (TextView) findViewById(R.id.input_instructions);

    }

    public void btn_back_On_Click(View v){
        Intent Go = new Intent(ShowRecipActivity.this, MainMenu.class);
        startActivity(Go);
    }

    public void btn_back_myrecpie_On_Click(View v){
        Intent Go = new Intent(ShowRecipActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }

}
