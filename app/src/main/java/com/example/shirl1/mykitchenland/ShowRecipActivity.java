package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ShowRecipActivity extends AppCompatActivity {


    TextView name1;
    TextView ingredient1;
    TextView instructions1;
    DBHandler db;

    int id1;
    String r_name;
    String r_instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recip);

        db = new DBHandler(this);
        name1 = (TextView) findViewById(R.id.recipe_name);
        ingredient1 = (TextView) findViewById(R.id.input_ingredient);
        instructions1 = (TextView) findViewById(R.id.input_instructions);

        //get name of recipe from listview
        Bundle bundle = getIntent().getExtras();
        r_name = bundle.getString("name");
        name1.setText(r_name);
/*
        Recipes myRecipe = db.getRecipeByName(r_name);
        Toast.makeText(ShowRecipActivity.this, "id: " + myRecipe.get_id() , Toast.LENGTH_LONG).show();
        r_instructions = myRecipe.get_recipeinstructions();*/


        //instructions1.setText(r_instructions);
        //Toast.makeText(ShowRecipActivity.this, "id: " + id1 , Toast.LENGTH_LONG).show();

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
