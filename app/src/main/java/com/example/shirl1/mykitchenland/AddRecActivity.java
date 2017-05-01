package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class AddRecActivity extends AppCompatActivity {

    DBHandler db;
    EditText re_name;
    EditText re_instructions;
    EditText re_ingredient;
    EditText re_amount;
    LinearLayout lay_in;
    LinearLayout lay_am;
    int check;
    EditText e_in;
    EditText e_am;
    ArrayList<Ingredient> arr_in;
    String in1;
    String in2;
    String checkName;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rec);
        db = new DBHandler(this);

        re_name = (EditText) findViewById(R.id.input_name);
        re_instructions = (EditText) findViewById(R.id.input_instructions);
        re_ingredient = (EditText) findViewById(R.id.input_ingre);
        re_amount = (EditText) findViewById(R.id.input_amount);
        lay_in = (LinearLayout) findViewById(R.id.lay_ingredient);
        lay_am = (LinearLayout) findViewById(R.id.lay_amount);
        arr_in = new ArrayList<>();
        check = 0;

        //db.addRecipe(new Recipes("RECIP1"));
        //db.addRecipe(new Recipes("RECIP2"));
        //db.addRecipe(new Recipes("RECIP3"));

    }

    public void btn_add_ingre_on_click(View v) {

        if(check == 0) {
            Ingredient in = new Ingredient(re_amount.getText().toString(), re_ingredient.getText().toString());
            arr_in.add(in);

            e_in = new EditText(this);
            e_am = new EditText(this);

            lay_in.addView(e_in);
            lay_am.addView(e_am);

            check = 1;
        }

        else{

            in1 = e_in.getText().toString();
            in2 = e_am.getText().toString();
            Ingredient in_new = new Ingredient(in2,in1);
            arr_in.add(in_new);

            e_in = new EditText(this);
            e_am = new EditText(this);

            lay_in.addView(e_in);
            lay_am.addView(e_am);
        }
    }

    public void getLastIngredient(){

        in1 = e_in.getText().toString();
        in2 = e_am.getText().toString();
        Ingredient in_new = new Ingredient(in2,in1);
        arr_in.add(in_new);

    }


    public void btn_cancel_On_Click(View v){

        Intent Go = new Intent(AddRecActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }



    public void btn_add_re_On_Click(View v){


        getLastIngredient();

        Recipes recipe = new Recipes(re_name.getText().toString(), re_instructions.getText().toString());
        db.addRecipe(recipe);

        for (int i = 0; i < arr_in.size(); i++) {
            Ingredient ing = new Ingredient(arr_in.get(i).get_amount(), arr_in.get(i).get_ingredient());
            db.addIngredient(ing);
        }
        Toast.makeText(AddRecActivity.this, "נוסף למתכונים שלי", Toast.LENGTH_LONG).show();

        Intent Go = new Intent(AddRecActivity.this, MyRecipesActivity.class);
        startActivity(Go);


    }

}





