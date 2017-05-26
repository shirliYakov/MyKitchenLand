package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRecipeToSLActivity extends AppCompatActivity {


    TextView info;
    TextView info2;
    DBHandler db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe_to_sl);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName);

        db = new DBHandler(this);

        listView = (ListView) findViewById(R.id.listview_recipes);
        //info = (TextView) findViewById(R.id.showtable);
        ArrayList<String> list = new ArrayList<>();
        Cursor data = db.getRecipeForList();

        ListAdapter listAdapter = new ArrayAdapter<>(AddRecipeToSLActivity.this, android.R.layout.simple_list_item_1, list);

        if (data.getCount() == 0) {
            Toast.makeText(AddRecipeToSLActivity.this, "המאגר עדיין ריק", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                list.add(data.getString(1));//column 2 is index of column-name
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(AddRecipeToSLActivity.this, AddShopListActivity.class);
                intent.putExtra("Name", listView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

        listView.setAdapter(listAdapter);

    }
}