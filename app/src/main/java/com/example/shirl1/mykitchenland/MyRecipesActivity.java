package com.example.shirl1.mykitchenland;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;


public class MyRecipesActivity extends AppCompatActivity {

    TextView info;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        db = new DBHandler(this);
        info = (TextView)findViewById(R.id.txt_info);

        //db.addRecipe(new Recipes("RECIP1"));


        String log ="";
        List <Recipes> recipesList = db.getAllRecipes();
        for (Recipes recipes : recipesList) {
            log = log + "Id: " + recipes.get_id() + " ,Name: "  + recipes.get_recipename() + "\n";
            info.setText(log);
        }

    }


    public void btn_back_On_Click(View v){
        Intent Go = new Intent(MyRecipesActivity.this, MainMenu.class);
        startActivity(Go);
    }

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.myrecipmenu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.add_rec) {
            Intent Go = new Intent(MyRecipesActivity.this, AddRecActivity.class);
            startActivity(Go);
        }

        if (id == R.id.search_rec) {
            View view = LayoutInflater.from(MyRecipesActivity.this).inflate(R.layout.activity_search_rec, null);
            final EditText search = (EditText) view.findViewById(R.id.search_rec);

            AlertDialog.Builder builder= new AlertDialog.Builder(MyRecipesActivity.this);
            builder.setTitle("חיפוש מתכון");

            builder.setCancelable(false);

            builder.setView(view).setPositiveButton("חפש", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    startActivity(new Intent(MyRecipesActivity.this, ShowRecipActivity.class));
                }

            });

            AlertDialog alert = builder.create();
            alert.show();
        }


        return super.onOptionsItemSelected(item);

    }


}
