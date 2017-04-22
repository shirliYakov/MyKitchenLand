package com.example.shirl1.mykitchenland;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;


public class MyRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);
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
