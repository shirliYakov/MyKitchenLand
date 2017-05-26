package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.*;

public class AddShopListActivity extends AppCompatActivity {

    DBHandler db;
    EditText list_name;
    EditText item_name;
    ListView recipelist;
    EditText item_amount;
    ImageButton add;
    String listID;
    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    List<Ingredient> ing;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_list);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName +",");
        mRecyclerView = (RecyclerView) findViewById(R.id.list_of_items);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // specify an adapter (see also next example)
        mAdapter = new ItemAdapter(new ArrayList<Item>());
        mRecyclerView.setAdapter(mAdapter);
        db = new DBHandler(this);
        add = (ImageButton) findViewById(R.id.add_more_btn);
        list_name = (EditText) findViewById(R.id.list_name);
        item_name = (EditText) findViewById(R.id.input_itemName);
        item_amount = (EditText) findViewById(R.id.input_itemAmount);
        recipelist = (ListView) findViewById(R.id.showRecipes) ;

        ArrayList <String> list = new ArrayList<>();
        Cursor data = db.getRecipeForList();

        ListAdapter listAdapter = new ArrayAdapter<>(AddShopListActivity.this, android.R.layout.simple_list_item_1, list);

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



        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item=new Item();
                if (item_name.getText().toString().isEmpty() ||((item_amount.getText().toString().isEmpty())))
                {
                    if (item_name.getText().toString().isEmpty())
                        item_name.setError("אין להשאיר שדה ריק");
                    if (item_amount.getText().toString().isEmpty())
                        item_amount.setError("אין להשאיר שדה ריק");
                }
                else
                    {
                    item.setItemName(item_name.getText().toString());
                    item.setAmount(item_amount.getText().toString());
                    mAdapter.AddItem(item);
                    item_name.setText("");
                    item_amount.setText("");
                }
            }
        });
    }


    public void btn__On_Click(View v) {

        Intent Go = new Intent(this, Shopping_list.class);
        startActivity(Go);
    }


    public void btn_back_On_Click(View v){

        Intent Go = new Intent(this, ShopListActivity.class);
        startActivity(Go);
    }


    public void btn_add_On_Click(View v)
    {
        List<Item> items=mAdapter.getItems();
        int id;
        Shopping_list shoppingList = create_List_And_get_list();
        if (shoppingList!=null)
        {
            String list_id=shoppingList.get_list_id();
        db.addItems(items);
        db.close();
        Toast.makeText(this, "הרשימה נוצרה בהצלחה", Toast.LENGTH_LONG).show();

        Intent Go = new Intent(this, ShopListActivity.class);
       startActivity(Go);
        }
    }


    public Shopping_list create_List_And_get_list() {
        Shopping_list shopList = new Shopping_list();
        shopList.set_listname(list_name.getText().toString());

        if (db.addShopList(shopList) == 0)
        {
            list_name.setError("שם הרשימה תפוס,אנא הזן שם חדש");
            db.close();
            return null;
        }
        else if (list_name.getText().toString().isEmpty())
        {
            list_name.setError("אין להשאיר שדה ריק");

            db.close();
            return null;
        }

        else
        db.close();
        return shopList;

    }


}