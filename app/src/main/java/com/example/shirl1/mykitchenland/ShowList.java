package com.example.shirl1.mykitchenland;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowList extends AppCompatActivity {
    String listname,new_name;
    private RecyclerView items;
    DBHandler db;
    ListItemAdapter mAdapter;
    int listID;
    EditText list_name;
    ListView recipelist;
    EditText item_name;
    EditText item_amount;
    ImageButton add;
    Button delete;
    List<Ingredient> ing;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName +",");
        Intent intent = getIntent();
        intent.getIntExtra("list_id", listID);
        // Toast.makeText(this, "הרשימה נוצרה בהצלחה", Toast.LENGTH_LONG).show();
        list_name = (EditText) findViewById(R.id.list_name);

        items = (RecyclerView) findViewById(R.id.items_list);
        items.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        db = new DBHandler(this);
        Bundle bundle = getIntent().getExtras();
        listname = bundle.getString("Name");
        recipelist = (ListView) findViewById(R.id.showRecipes) ;

        ArrayList <String> list = new ArrayList<>();
        Cursor data = db.getRecipeForList();

        ListAdapter listAdapter = new ArrayAdapter<>(ShowList.this, android.R.layout.simple_list_item_1, list);

        if(data.getCount()!=0)
        {
            while(data.moveToNext()){
                list.add(data.getString(1));//column 2 is index of column-name
            }
        }
        recipelist.setAdapter(listAdapter);
        if (recipelist.getCount() != 0)
        {
            recipelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String name = recipelist.getItemAtPosition(i).toString();
                    Recipes r = db.getRecipeByName(name);
                    if (r != null) {
                        int id = r.get_id();
                        ing = db.getIngredientById(id);
                    }
                    for (Ingredient ingredient : ing)
                    {
                        Item itm=new Item();
                        itm.setItemName(ingredient.get_amount());
                        itm.setAmount(ingredient.get_ingredient());
                        mAdapter.AddItem(itm);
                    }
                }
            });

        }
        Shopping_list s = db.getShopListByID(listname);
        if (s==null)
        {
            Toast.makeText(this, "הרשימה לא קיימת", Toast.LENGTH_LONG).show();
            Intent goBack =new Intent(this,ShopListActivity.class);
            startActivity(goBack);
        }

        else {
            list_name.setText(s.get_listname());

            mAdapter = new ListItemAdapter(db.getItemsByListId(s.get_list_id()));
            items.setAdapter(mAdapter);
            items.setVisibility(View.VISIBLE);
            items.setHasFixedSize(true);

            add = (ImageButton) findViewById(R.id.add_more_btn);
            item_name = (EditText) findViewById(R.id.item_name);
            item_amount = (EditText) findViewById(R.id.item_amount);
           /* delete= (Button) findViewById(R.id.deleteShopList);
            delete.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    db.delete_shoplist(listname);
                    Toast.makeText(ShowList.this, "הרשימה נמחקה", Toast.LENGTH_LONG).show();
                    Intent Go = new Intent(ShowList.this, ShopListActivity.class);
                    startActivity(Go);
                }
            });*/

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item item = new Item();
                    if (item_name.getText().toString().isEmpty())
                        item_name.setError("אין להשאיר שדה ריק");

                    if (item_amount.getText().toString().isEmpty())
                        item_amount.setError("אין להשאיר שדה ריק");
                    else {
                        item.setItemName(item_name.getText().toString());
                        item.setAmount(item_amount.getText().toString());
                        mAdapter.AddItem(item);
                        item_name.setText("");
                        item_amount.setText("");
                    }
                }
            });
        }


    }

    public void btn_back_On_Click(View v)
    {
        Intent Go = new Intent(this, ShopListActivity.class);
        startActivity(Go);
    }


    public void info_On_Click(View view) {

        String log = "\n" + "לחץ על הוסף מוצר כדי להוסיף את המצרך לרשימה" + "\n"
                + "לחיצה על מתכון מתוך רשימת המתכונים,תוסיף את מצרכיו לרשימת הקניות שלך" + "\n"
                + " תוכל תמיד להסיר מוצרים מהרשימה על ידי לחיצה על פח האשפה" + "\n";



        View v = LayoutInflater.from(ShowList.this).inflate(R.layout.info, null);
        final TextView info = (TextView)v.findViewById(R.id.txt_info);
        info.setText(log);

        AlertDialog.Builder builder= new AlertDialog.Builder(ShowList.this);
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




    public void btn_save_On_Click(View v)
    {
        List<Item> items=mAdapter.getItems();
        new_name=list_name.getText().toString();//save the list new name
        if (list_name.getText().toString().isEmpty())
            list_name.setError("אין להשאיר שדה ריק");
        else {
            //add sql question to change the values
            if (db.change_list_details_by_name(listname, new_name, items) == 0)
            //     db.addItems(items);
            {
                db.close();
                Toast.makeText(this, "הרשימה עודכנה בהצלחה", Toast.LENGTH_LONG).show();
                Intent Go = new Intent(this, ShopListActivity.class);
                startActivity(Go);
            } else
                list_name.setError("שם הרשימה תפוס,אנא הזן שם אחר");
        }
    }




}