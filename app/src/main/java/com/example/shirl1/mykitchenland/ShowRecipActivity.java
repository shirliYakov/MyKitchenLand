package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ShowRecipActivity extends AppCompatActivity {



    TextView name1;
    TextView ingredient1;
    TextView instructions1;
    DBHandler db;
    String name;
    String nameinput;
    int id;
    String instruct;
    String in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recip);

        db = new DBHandler(this);

        name1 = (TextView) findViewById(R.id.recipe_name);
        ingredient1 = (TextView) findViewById(R.id.input_ingredient);
        instructions1 = (TextView) findViewById(R.id.input_instructions);

        //get name from list
        Bundle bundle = getIntent().getExtras();
        nameinput = bundle.getString("Name");

        Recipes r = db.getRecipeByName(nameinput);
        instructions1.setText(r.get_recipeinstructions());
        name1.setText(r.get_recipename());
        id =r.get_id();

        String log="";
        List <Ingredient> i = db.getIngredientById(id);
        for (Ingredient ingre : i) {
            log = log +  ingre.get_amount() + "  "  + ingre.get_ingredient() + "\n";
            ingredient1.setText(log);
        }
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
