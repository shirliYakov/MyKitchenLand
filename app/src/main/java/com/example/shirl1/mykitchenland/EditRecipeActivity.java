package com.example.shirl1.mykitchenland;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
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
    byte[] imageMe;
    Bitmap Myimage;
    ImageView IV;
    Button camera, upload, gallery;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName);
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
        IV = (ImageView) findViewById(R.id.imageView_pic);
        check = 0;

        listAdapter = new ArrayAdapter<>(EditRecipeActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listAdapter);

        Recipes r = db.getRecipeByName(nameToEdit);
        re_name.setText(r.get_recipename());
        re_instructions.setText(r.get_recipeinstructions());
        re_time.setText(r.getTime());
        if (r.getImage() != null) {
            Myimage = getImage(r.getImage());
            IV.setImageBitmap(Myimage);
        }

        id =r.get_id();
        List<Ingredient> i = db.getIngredientById(id);
        for (Ingredient ingre : i) {
            list.add(ingre.get_amount() + " " + ingre.get_ingredient());
            arr_in.add(ingre);
        }

        data = db.getRecipeForList();
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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                list.remove(pos);
                arr_in.remove(pos);
                listView.invalidateViews();
                return false;
            }
        });
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
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
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
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
            Ingredient in_new = new Ingredient(in1, in2);
            arr_in.add(in_new);
            list.add(in1 + " " + in2);//column 2 is index of column-name
            re_amount.setText("");
            re_ingredient.setText("");
        }
    }

    public void info_On_Click(View view) {

        String log = "\n" + "לחץ על הוסף מוצר כדי להוסיף את המצרך לרשימה" + "\n"
                + "לחיצה ארוכה על המצרך תסיר אותו מהרשימה" + "\n"
                +"לחץ על הוסף תמונה כדי לצלם תמונה הקשורה למתכון" + "\n"
                +"בסוף העריכה לחץ 'עדכן מתכון' בתחתית המסך כדי לשמור את השינויים" + "\n";

        View v = LayoutInflater.from(EditRecipeActivity.this).inflate(R.layout.info, null);
        final TextView info = (TextView)v.findViewById(R.id.txt_info);
        info.setText(log);

        AlertDialog.Builder builder= new AlertDialog.Builder(EditRecipeActivity.this);
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


    public void btn_cancel_On_Click(View v){
        Intent Go = new Intent(EditRecipeActivity.this, MyRecipesActivity.class);
        startActivity(Go);
    }

    public void btn_add_re_On_Click(View v){

        if(re_name.getText().toString().isEmpty())
            re_name.setError("נא הכנס שם מתכון");

        /*else {

            if (nameToEdit.equals(re_name.getText().toString().trim())) {

                if(imageMe==null) {
                    Recipes recipe = new Recipes(re_name.getText().toString().trim(), re_instructions.getText().toString(), re_time.getText().toString());
                    db.EditRecipe2(recipe);
                }
                else {
                    Recipes recipe = new Recipes(re_name.getText().toString().trim(), re_instructions.getText().toString(), imageMe, re_time.getText().toString());
                    db.EditRecipe2(recipe);
                }

                for (int i = 0; i < arr_in.size(); i++) {
                    Ingredient ing = new Ingredient(arr_in.get(i).get_amount(), arr_in.get(i).get_ingredient());
                    db.addIngredient(ing);
                }
            } else {

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
            }*/

        else {

            if (imageMe == null) {
                Recipes recipe = new Recipes(re_name.getText().toString().trim(), re_instructions.getText().toString(), re_time.getText().toString());
                db.EditRecipe2(recipe,nameToEdit);
            } else {
                Recipes recipe = new Recipes(re_name.getText().toString().trim(), re_instructions.getText().toString(), imageMe, re_time.getText().toString());
                db.EditRecipe2(recipe,nameToEdit);
            }

            for (int i = 0; i < arr_in.size(); i++) {
                Ingredient ing = new Ingredient(arr_in.get(i).get_amount(), arr_in.get(i).get_ingredient());
                db.addIngredient(ing);
            }
        }


            Toast.makeText(EditRecipeActivity.this, "המתכון עודכן", Toast.LENGTH_LONG).show();
            Intent Go = new Intent(EditRecipeActivity.this, MyRecipesActivity.class);
            startActivity(Go);

    }
}
