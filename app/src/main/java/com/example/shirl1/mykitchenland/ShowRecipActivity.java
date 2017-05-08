package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    String name;
    String nameinput;
    int id;
    String instruct;
    String in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recip);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName +",");
        db = new DBHandler(this);

        name1 = (TextView) findViewById(R.id.recipe_name);
        ingredient1 = (TextView) findViewById(R.id.input_ingredient);
        instructions1 = (TextView) findViewById(R.id.input_instructions);

        //get name from list
        Bundle bundle = getIntent().getExtras();
        nameinput = bundle.getString("Name");
    /*public Recipes getRecipeByName(String re_name) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_RECIPES + " WHERE "
                + COLUMN_RECIPE_NAME + " = '" + re_name + "'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        if(c.getCount() <= 0){
            c.close();
            return null;
        }

        if (c != null)
            c.moveToFirst();

        Recipes re = new Recipes();
        re.set_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
        re.set_recipename(c.getString(c.getColumnIndex(COLUMN_RECIPE_NAME)));
        re.set_recipeinstructions(c.getString(c.getColumnIndex(COLUMN_RECIPE_INSTRUCTIONS)));
        db.close();

        return re;
    }*/

        Recipes r = db.getRecipeByName(nameinput);

        if (r!= null) {
            instructions1.setText(r.get_recipeinstructions());
            name1.setText(r.get_recipename());
            id =r.get_id();

            String log="";
            List <Ingredient> i = db.getIngredientById(id);
            for (Ingredient ingre : i) {
                log = log +  ingre.get_ingredient() + "  "  + ingre.get_amount() + "\n";
                ingredient1.setText(log);
            }
        }

        else {
            Toast.makeText(ShowRecipActivity.this, "מתכון לא קיים במאגר", Toast.LENGTH_LONG).show();
            Intent Go = new Intent(ShowRecipActivity.this, MyRecipesActivity.class);
            startActivity(Go);
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

    public void DeleteRecipe_onClick(View v){

        db.deleteRecipe(nameinput);
        Intent Go = new Intent(ShowRecipActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }

}
