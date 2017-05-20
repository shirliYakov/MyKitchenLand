package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.DialogInterface;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipesActivity extends AppCompatActivity {


    TextView info;
    TextView info2;
    DBHandler db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName +",");

        db = new DBHandler(this);

        //btn_delete_On_Click();

        listView = (ListView) findViewById(R.id.listview_recipes);
        ArrayList<String> list = new ArrayList<>();
        Cursor data = db.getRecipeForList_manager();

        ListAdapter listAdapter = new ArrayAdapter<>(RecipesActivity.this, android.R.layout.simple_list_item_1, list);

        if(data.getCount()==0){
            fillRecipes();
            data = db.getRecipeForList_manager();
            while (data.moveToNext()) {
                list.add(data.getString(1));//column 2 is index of column-name
            }
        }

        if(list.isEmpty()) {
            while (data.moveToNext()) {
                list.add(data.getString(1));//column 2 is index of column-name
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView , View view, int i, long l) {

                Intent intent = new Intent(RecipesActivity.this, ShowManagerRecipActivity.class);
                intent.putExtra("Name", listView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

        listView.setAdapter(listAdapter);

    }

    public void fillRecipes(){

        Recipes recipe = new Recipes("פשטידה","לערבב");
        db.addRecipe_manager(recipe);
        Ingredient ingredient = new Ingredient("5","בננות");
        db.addIngredient_manager(ingredient);
        Recipes recipe2 = new Recipes("חביתה","לערבב");
        db.addRecipe_manager(recipe2);
        Ingredient ingredient2 = new Ingredient("4","ביצים");
        db.addIngredient_manager(ingredient2);

    }

    public void btn_delete_On_Click(){
        db.clearTableManager();
    }

    public void btn_back_On_Click(View v){
        Intent Go = new Intent(RecipesActivity.this, MainMenu.class);
        startActivity(Go);
    }


    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.recipmenu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.search_rec) {

            View view = LayoutInflater.from(RecipesActivity.this).inflate(R.layout.activity_search_rec, null);
            final EditText search = (EditText) view.findViewById(R.id.txt_search_re);

            AlertDialog.Builder builder= new AlertDialog.Builder(RecipesActivity.this);
            builder.setTitle("חיפוש מתכון");

            builder.setCancelable(false);

            builder.setView(view)
                    .setPositiveButton("חפש", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(RecipesActivity.this, ShowRecipActivity.class);
                            intent.putExtra("Name", search.getText().toString());
                            startActivity(intent);
                        }

                    })
                    .setNegativeButton("סגור", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }

        return super.onOptionsItemSelected(item);

    }




}
