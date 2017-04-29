package com.example.shirl1.mykitchenland;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MyRecipesActivity extends AppCompatActivity {

    TextView info;
    TextView info2;
    DBHandler db;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        db = new DBHandler(this);
        info = (TextView)findViewById(R.id.Info);
        String log ="";
        List <Recipes> recipesList = db.getAllRecipes();
        for (Recipes recipes : recipesList) {
            log = log + "Id: " + recipes.get_id() + " , Name: "  + recipes.get_recipename()
                    + ", instructions: " + recipes.get_recipeinstructions() + "\n";
            info.setText(log);
        }

        listView = (ListView) findViewById(R.id.listview_myrecipe);
        ArrayList <String> list = new ArrayList<>();
        Cursor data = db.getRecipeForList();

        ListAdapter listAdapter = new ArrayAdapter<>(MyRecipesActivity.this, android.R.layout.simple_list_item_1, list);
        if(data.getCount()==0){
            Toast.makeText(MyRecipesActivity.this, "המאגר עדיין ריק", Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                list.add(data.getString(2));//column 2 is index of column-name
            }
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView , View view, int i, long l) {

                Intent inte = new Intent(MyRecipesActivity.this, ShowRecipActivity.class);
                inte.putExtra("name", listView.getItemAtPosition(i).toString());
                startActivity(inte);
            }
        });

        listView.setAdapter(listAdapter);

        //db.addRecipe(new Recipes("RECIP1", "FUN"));
        //db.addIngredient(new Ingredient(1,"6","BANANA"));

        /*
        //****ptint database*****
        String log ="";
        List <Recipes> recipesList = db.getAllRecipes();
        for (Recipes recipes : recipesList) {
            log = log + "Id: " + recipes.get_id() + " , Name: "  + recipes.get_recipename()
                    + ", instructions: " + recipes.get_recipeinstructions() + "\n";
            info.setText(log);
        }

        String log2 ="";
        List <Ingredient> ingredientsList = db.getAllIngredient();
        for (Ingredient ingredient : ingredientsList) {
            log2 = log2 + "Id: " + ingredient.get_id() + " , amount: "  + ingredient.get_amount()
                    + ", ingredient: " + ingredient.get_ingredient() + "\n";
            info2.setText(log2);
        }*/

    }

    public void btn_delete_On_Click(View v){
        db.clearTable();
        Intent refresh = new Intent(this, MyRecipesActivity.class);
        startActivity(refresh);
        this.finish();
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
