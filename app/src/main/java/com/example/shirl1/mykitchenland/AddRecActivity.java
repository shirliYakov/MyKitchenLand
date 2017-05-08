package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
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
    EditText re_name, re_instructions, re_ingredient, re_amount, re_time, e_in, e_am;
    LinearLayout lay_in, lay_am;
    int check;
    ArrayList<Ingredient> arr_in;
    String in1, in2, checkName;
    ListView listView;
    Cursor data;
    ArrayList <String> list;
    Bitmap photo;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rec);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName +",");
        db = new DBHandler(this);

        re_name = (EditText)findViewById(R.id.input_name);
        re_instructions = (EditText) findViewById(R.id.input_instructions);
        re_ingredient = (EditText) findViewById(R.id.input_ingre);
        re_amount = (EditText) findViewById(R.id.input_amount);
        re_time = (EditText) findViewById(R.id.input_time);
        //lay_in = (LinearLayout) findViewById(R.id.lay_ingredient);
        lay_am = (LinearLayout) findViewById(R.id.lay_amount);
        arr_in = new ArrayList<>();

        check = 0;



        listView = (ListView) findViewById(R.id.listview_ingre);
        //info = (TextView) findViewById(R.id.showtable);
        list = new ArrayList<>();
        data = db.getRecipeForList();

        ListAdapter listAdapter = new ArrayAdapter<>(AddRecActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listAdapter);

        //db.addRecipe(new Recipes("RECIP1"));
        //db.addRecipe(new Recipes("RECIP2"));
        //db.addRecipe(new Recipes("RECIP3"));

    }

    public void btn_add_ingre_on_click(View v) {

        in1 = re_amount.getText().toString();
        in2 = re_ingredient.getText().toString();
        Ingredient in_new = new Ingredient(in2,in1);
        arr_in.add(in_new);

        list.add(arr_in.get(list.size()).get_ingredient().toString() + " " + arr_in.get(list.size()).get_amount().toString());//column 2 is index of column-name
        re_amount.setText("");
        re_ingredient.setText("");
    }

    public void takePicture_on_click(View v) {
        Intent Go = new Intent(AddRecActivity.this, ImageRecipActivity.class);
        startActivity(Go);

    }

    public void btn_cancel_On_Click(View v){
        Intent Go = new Intent(AddRecActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }

    public void btn_add_re_On_Click(View v){


        Recipes recipe = new Recipes(re_name.getText().toString(), re_instructions.getText().toString() ,re_time.getText().toString());
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





