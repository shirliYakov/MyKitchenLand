package com.example.shirl1.mykitchenland;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowManagerRecipActivity extends AppCompatActivity{


    TextView name1;
    TextView ingredient1;
    TextView instructions1;
    DBHandler db;
    TextView time1;
    String name;
    String nameinput;
    ImageView image1;
    int id;
    Bitmap Myimage;
    List <Ingredient> ing;
    ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName +",");
        setContentView(R.layout.activity_show_manager_recip);
        db = new DBHandler(this);

        name1 = (TextView) findViewById(R.id.recipe_name);
        ingredient1 = (TextView) findViewById(R.id.input_ingredient);
        instructions1 = (TextView) findViewById(R.id.input_instructions);
        time1 = (TextView) findViewById(R.id.input_time);
        image1 = (ImageView)findViewById(R.id.input_image);

        Bundle bundle = getIntent().getExtras();
        nameinput = bundle.getString("Name");
        Recipes r = db.getRecipeByName_manager(nameinput);

        if (r!= null) {
            instructions1.setText(r.get_recipeinstructions());
            name1.setText(r.get_recipename());
            time1.setText(r.getTime());
            if (r.getImage() != null) {
                Myimage = getImage(r.getImage());
                image1.setImageBitmap(Myimage);
            }
            id = r.get_id();

            String log = "";
            ing = db.getIngredientById_manager(id);
            for (Ingredient ingre : ing) {
                log = log + ingre.get_amount() + "  " + ingre.get_ingredient() + "\n";
                ingredient1.setText(log);
            }
        }
        else {
            Toast.makeText(ShowManagerRecipActivity.this, "מתכון לא קיים במאגר", Toast.LENGTH_LONG).show();
            Intent Go = new Intent(ShowManagerRecipActivity.this, RecipesActivity.class);
            startActivity(Go);
        }

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowManagerRecipActivity.this, fullscreenimageActivity.class);
                intent.putExtra("image", Myimage);
                startActivity(intent);
            }
        });
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }


  /*  public void addToShopList_On_Click(View v) {

        View view = LayoutInflater.from(ShowManagerRecipActivity.this).inflate(R.layout.addrecipetolist, null);
        final ListView list_shoplist = (ListView) view.findViewById(R.id.list_shoplist);

        list = new ArrayList<>();
        Cursor data = db.getListOfShopList();
        ListAdapter listAdapter = new ArrayAdapter<>(ShowManagerRecipActivity.this, android.R.layout.simple_list_item_1, list);

        if (data.getCount() == 0) {
            Toast.makeText(ShowManagerRecipActivity.this, "המאגר עדיין ריק", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                list.add(data.getString(1));
            }
        }

        list_shoplist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView , View view, int i, long l) {

                String listName = list_shoplist.getItemAtPosition(i).toString();
                Shopping_list MyList =  db.getShopListByID(listName);
                String id_list = MyList.get_list_id();
                db.addIngregientByListId(ing,id_list);
                Toast.makeText(ShowManagerRecipActivity.this,"המתכון נוסף לרשימה" + "\n" +  "'" + listName + "'", Toast.LENGTH_LONG).show();
            }

        });

        list_shoplist.setAdapter(listAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(ShowManagerRecipActivity.this);
        builder.setCancelable(false);
        builder.setView(view)
                .setNegativeButton("סגור", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();



    }*/

    public void btn_back_On_Click(View v){
        Intent Go = new Intent(ShowManagerRecipActivity.this, MainMenu.class);
        startActivity(Go);
    }

    public void btn_back_myrecpie_On_Click(View v){
        Intent Go = new Intent(ShowManagerRecipActivity.this, RecipesActivity.class);
        startActivity(Go);
    }

    public void info_On_Click(View view) {

        String log = "\n" + "בחר ב'הוסף לרשימת קניות' כדי להוסיף את המצרכים ישירות לרשימה קיימת" + "\n"
                +"לחץ על התמונה כדי לצפות בה על מסך מלא" + "\n";


        View v = LayoutInflater.from(ShowManagerRecipActivity.this).inflate(R.layout.info, null);
        final TextView info = (TextView)v.findViewById(R.id.txt_info);
        info.setText(log);

        AlertDialog.Builder builder= new AlertDialog.Builder(ShowManagerRecipActivity.this);
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
