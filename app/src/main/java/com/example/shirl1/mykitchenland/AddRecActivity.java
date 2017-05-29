package com.example.shirl1.mykitchenland;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class AddRecActivity extends AppCompatActivity {

    DBHandler db;
    byte[] imageMe;
    EditText re_name, re_instructions, re_ingredient, re_amount, re_time, e_in, e_am;
    LinearLayout lay_in, lay_am;
    int check;
    ArrayList<Ingredient> arr_in;
    String in1, in2, checkName;
    ListView listView;
    Cursor data;
    ArrayList <String> list;
    Bitmap photo;
    ListAdapter listAdapter;
    ImageView IV;
    Button camera, upload, gallery;
    static final int REQUEST_IMAGE_CAPTURE = 0;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rec);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName);
        db = new DBHandler(this);

        re_name = (EditText)findViewById(R.id.input_name);
        re_instructions = (EditText) findViewById(R.id.input_instructions);
        re_ingredient = (EditText) findViewById(R.id.input_ingre);
        re_amount = (EditText) findViewById(R.id.input_amount);
        re_time = (EditText) findViewById(R.id.input_time);
        //lay_in = (LinearLayout) findViewById(R.id.lay_ingredient);
        lay_am = (LinearLayout) findViewById(R.id.lay_amount);
        arr_in = new ArrayList<>();
        IV = (ImageView) findViewById(R.id.imageView_pic);
        check = 0;

        listView = (ListView) findViewById(R.id.listview_ingre);
        listView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        //info = (TextView) findViewById(R.id.showtable);
        list = new ArrayList<>();
        data = db.getRecipeForList();

        listAdapter = new ArrayAdapter<>(AddRecActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listAdapter);

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
            Toast.makeText(AddRecActivity.this, "לא ניתן להכניס מוצר ריק", Toast.LENGTH_LONG).show();

        else if(in1.isEmpty() && (!in2.isEmpty()))
            Toast.makeText(AddRecActivity.this, "לא ניתן להכניס מוצר ללא כמות", Toast.LENGTH_LONG).show();

        else if(in2.isEmpty() && (!in1.isEmpty()))
            Toast.makeText(AddRecActivity.this, "לא ניתן להכניס כמות ללא מוצר", Toast.LENGTH_LONG).show();

        else {
            Ingredient in_new = new Ingredient(in1, in2);
            arr_in.add(in_new);
            list.add(in1 + " " + in2);
            re_amount.setText("");
            re_ingredient.setText("");
        }
    }

    public void takePicture_on_click(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);//PASS RESULT TO onactivityresult
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            IV.setImageBitmap(photo);
            imageMe = getBytes(photo);
        }
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , stream);
        return stream.toByteArray();
    }

    public void btn_cancel_On_Click(View v){
        Intent Go = new Intent(AddRecActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }

    public void btn_add_re_On_Click(View v){

        if(re_name.getText().toString().isEmpty())
            re_name.setError("נא הכנס שם מתכון");

        else {

            if(imageMe==null) {
                Recipes recipe = new Recipes(re_name.getText().toString().trim(), re_instructions.getText().toString(), re_time.getText().toString());
                db.addRecipe(recipe);
            }
            else {
                Recipes recipe = new Recipes(re_name.getText().toString().trim(), re_instructions.getText().toString(), imageMe, re_time.getText().toString());
                db.addRecipe(recipe);
            }

            for (int i = 0; i < arr_in.size(); i++) {
                Ingredient ing = new Ingredient(arr_in.get(i).get_amount(), arr_in.get(i).get_ingredient());
                db.addIngredient(ing);
            }
            Toast.makeText(AddRecActivity.this, "נוסף למתכונים שלי", Toast.LENGTH_LONG).show();

            Intent Go = new Intent(AddRecActivity.this, MyRecipesActivity.class);
            startActivity(Go);
        }
    }

    public void info_On_Click(View view) {

        String log = "\n" + "לחץ על הוסף מוצר כדי להוסיף את המצרך לרשימה" + "\n"
                + "לחיצה ארוכה על המצרך תסיר אותו מהרשימה" + "\n"
                +"לחץ על הוסף תמונה כדי לצלם תמונה הקשורה למתכון" + "\n";


        View v = LayoutInflater.from(AddRecActivity.this).inflate(R.layout.info, null);
        final TextView info = (TextView)v.findViewById(R.id.txt_info);
        info.setText(log);

        AlertDialog.Builder builder= new AlertDialog.Builder(AddRecActivity.this);
        builder.setView(v)
                .setTitle("מידע כללי")
                .setIcon(R.drawable.infopink)
                .setNegativeButton("סגור", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}





