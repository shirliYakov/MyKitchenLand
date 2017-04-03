package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.DialogInterface;

public class RecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

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
            final EditText search = (EditText) view.findViewById(R.id.search_rec);

            AlertDialog.Builder builder= new AlertDialog.Builder(RecipesActivity.this);
            builder.setTitle("חיפוש מתכון");

            builder.setCancelable(false);

            builder.setView(view).setPositiveButton("חפש", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    startActivity(new Intent(RecipesActivity.this, ShowRecipActivity.class));
                }

            });

            AlertDialog alert = builder.create();
            alert.show();


        }

        return super.onOptionsItemSelected(item);

    }




}
