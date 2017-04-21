package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddRecActivity extends AppCompatActivity {

    DBHandler db;
    EditText re_name;
    EditText re_instructions;
    EditText re_ingredient;
    EditText re_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rec);
        db = new DBHandler(this);

        re_name = (EditText) findViewById(R.id.input_name);
        re_instructions = (EditText) findViewById(R.id.input_instructions);
        re_ingredient = (EditText) findViewById(R.id.input_ingre);
        re_amount = (EditText) findViewById(R.id.input_amount);

        //db.addRecipe(new Recipes("RECIP1"));
        //db.addRecipe(new Recipes("RECIP2"));
        //db.addRecipe(new Recipes("RECIP3"));
/*
        TextView t1 = (TextView) findViewById(R.id.textView1);
        t1.isClickable();*/


    }

    public void btn_cancel_On_Click(View v){

        Intent Go = new Intent(AddRecActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }


    public void btn_add_re_On_Click(View v){

        Recipes recipe = new Recipes(re_name.getText().toString(), re_instructions.getText().toString());
        db.addRecipe(recipe);

        Ingredient ingredient = new Ingredient(re_amount.getText().toString(), re_ingredient.getText().toString());
        db.addIngredient(ingredient);


        Intent Go = new Intent(AddRecActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }

}





