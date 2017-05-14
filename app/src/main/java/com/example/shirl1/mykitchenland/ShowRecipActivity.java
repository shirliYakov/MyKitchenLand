package com.example.shirl1.mykitchenland;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ShowRecipActivity extends AppCompatActivity {

    TextView name1;
    TextView ingredient1;
    TextView instructions1;
    TextView time1;
    ImageView image1;
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
        time1 = (TextView) findViewById(R.id.input_time);
        image1 = (ImageView)findViewById(R.id.input_image);

        //get name from list
        Bundle bundle = getIntent().getExtras();
        nameinput = bundle.getString("Name");

        Recipes r = db.getRecipeByName(nameinput);

        if (r!= null) {
            instructions1.setText(r.get_recipeinstructions());
            name1.setText(r.get_recipename());
            time1.setText(r.getTime());
            //image1.setImageBitmap(r.getImage());
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

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.showrecipemenu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.delete_recipe) {

            db.deleteRecipe(nameinput);
            Intent Go = new Intent(ShowRecipActivity.this, MyRecipesActivity.class);
            startActivity(Go);
        }

        if (id == R.id.edit_recipe) {

            Intent intent = new Intent(ShowRecipActivity.this, EditRecipeActivity.class);
            intent.putExtra("NameToEdit", nameinput);
            startActivity(intent);

        }

        if (id == R.id.send_recipe_ingre) {
            Intent Go = new Intent(ShowRecipActivity.this, MainMenu.class);
            startActivity(Go);
        }

        return super.onOptionsItemSelected(item);

    }

}
