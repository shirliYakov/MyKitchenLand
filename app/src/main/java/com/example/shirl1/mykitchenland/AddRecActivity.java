package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class AddRecActivity extends AppCompatActivity {

    EditText re_name;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rec);
        re_name = (EditText) findViewById(R.id.input_name);
        db = new DBHandler(this);

        // Inserting Shop/Rows
        //Log.d("Insert: ", "Inserting ..");
        //db.addRecipe(new Recipes("RECIP1"));
        //db.addRecipe(new Recipes("RECIP2"));
        //db.addRecipe(new Recipes("RECIP3"));

    }


    public void btn_add_re_On_Click(View v){

        Recipes recipe = new Recipes(re_name.getText().toString());
        db.addRecipe(recipe);

        // Reading all shops
        Log.d("Reading: ", "Reading all shops..");
        List <Recipes> recipesList = db.getAllRecipes();

        for (Recipes recipes : recipesList) {
            String log = "Id: " + recipes.get_id() + " ,Name: "  + recipes.get_recipename();
// Writing shops to log
            Log.d("Recipes: : ", log);
        }

        Intent Go = new Intent(AddRecActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }

}





