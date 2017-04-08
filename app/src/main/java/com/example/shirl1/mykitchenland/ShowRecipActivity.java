package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ShowRecipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recip);

        //*******BIG BIG HELP TO SHOW ONE ITEM!!***** EDIT THAT!

        /*db = new DBHandler(this);
        info = (TextView)findViewById(R.id.txt_info);

        List<Recipes> recipesList = db.getAllRecipes();

        for (Recipes recipes : recipesList) {
            String log = "Id: " + recipes.get_id() + " ,Name: "  + recipes.get_recipename();
// Writing shops to log
            //Log.d("Recipes: : ", log);
            info.setText(log);
        }*/
    }

    public void btn_back_On_Click(View v){
        Intent Go = new Intent(ShowRecipActivity.this, MainMenu.class);
        startActivity(Go);
    }
}
