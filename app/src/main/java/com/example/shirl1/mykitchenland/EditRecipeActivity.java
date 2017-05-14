package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditRecipeActivity extends AppCompatActivity {

    DBHandler db;
    EditText re_name, re_instructions, re_ingredient, re_amount, re_time, e_in, e_am;
    LinearLayout lay_in, lay_am;
    int check;
    ArrayList <Ingredient> arr_in;
    String in1, in2, checkName;
    ListView listView;
    Cursor data;
    ArrayList <String> list;
    Bitmap photo;
    String nameToEdit;
    ListAdapter listAdapter;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName +",");
        setContentView(R.layout.activity_edit_recipe);

        db = new DBHandler(this);
        Bundle bundle = getIntent().getExtras();
        nameToEdit = bundle.getString("NameToEdit");

        re_name = (EditText)findViewById(R.id.input_name);
        re_instructions = (EditText) findViewById(R.id.input_instructions);
        re_ingredient = (EditText) findViewById(R.id.input_ingre);
        re_amount = (EditText) findViewById(R.id.input_amount);
        re_time = (EditText) findViewById(R.id.input_time);
        arr_in = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview_ingre);
        list = new ArrayList<>();
        check = 0;

        listAdapter = new ArrayAdapter<>(EditRecipeActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listAdapter);

        Recipes r = db.getRecipeByName(nameToEdit);
        re_name.setText(r.get_recipename());
        re_instructions.setText(r.get_recipeinstructions());
        re_time.setText(r.getTime());

        id =r.get_id();
        List<Ingredient> i = db.getIngredientById(id);
        for (Ingredient ingre : i) {
            list.add(ingre.get_ingredient() + " " + ingre.get_amount());
            arr_in.add(ingre);
        }

        data = db.getRecipeForList();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                list.remove(pos);
                arr_in.remove(pos);
                listView.invalidateViews();
                return false;
            }
        });
    }

    public void btn_add_ingre_on_click(View v) {

        in1 = re_amount.getText().toString();
        in2 = re_ingredient.getText().toString();

        if(in1.isEmpty() && in2.isEmpty())
            Toast.makeText(EditRecipeActivity.this, "לא ניתן להכניס מוצר ריק", Toast.LENGTH_LONG).show();

        else if(in1.isEmpty() && (!in2.isEmpty()))
            Toast.makeText(EditRecipeActivity.this, "לא ניתן להכניס מוצר ללא כמות", Toast.LENGTH_LONG).show();

        else if(in2.isEmpty() && (!in1.isEmpty()))
            Toast.makeText(EditRecipeActivity.this, "לא ניתן להכניס כמות ללא מוצר", Toast.LENGTH_LONG).show();

        else {
            Ingredient in_new = new Ingredient(in2, in1);
            arr_in.add(in_new);
            list.add(in1 + " " + in2);//column 2 is index of column-name
            re_amount.setText("");
            re_ingredient.setText("");
        }
    }

    public void takePicture_on_click(View v) {
        Intent Go = new Intent(EditRecipeActivity.this, ImageRecipActivity.class);
        startActivity(Go);
    }

    public void btn_cancel_On_Click(View v){
        Intent Go = new Intent(EditRecipeActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }

    public void btn_add_re_On_Click(View v){

        if(re_name.getText().toString().isEmpty())
            re_name.setError("נא הכנס שם מתכון");

        else {

            if (nameToEdit.equals(re_name.getText().toString())) {

                Recipes recipe = new Recipes(re_name.getText().toString(), re_instructions.getText().toString(), re_time.getText().toString());
                db.EditRecipe2(recipe);

                for (int i = 0; i < arr_in.size(); i++) {
                    Ingredient ing = new Ingredient(arr_in.get(i).get_amount(), arr_in.get(i).get_ingredient());
                    db.addIngredient(ing);
                }
            } else {

                Recipes recipe = new Recipes(re_name.getText().toString(), re_instructions.getText().toString(), re_time.getText().toString());
                db.addRecipe(recipe);

                for (int i = 0; i < arr_in.size(); i++) {
                    Ingredient ing = new Ingredient(arr_in.get(i).get_amount(), arr_in.get(i).get_ingredient());
                    db.addIngredient(ing);
                }
            }

            Toast.makeText(EditRecipeActivity.this, "נוסף למתכונים שלי", Toast.LENGTH_LONG).show();
            Intent Go = new Intent(EditRecipeActivity.this, MyRecipesActivity.class);
            startActivity(Go);
        }
    }
}
