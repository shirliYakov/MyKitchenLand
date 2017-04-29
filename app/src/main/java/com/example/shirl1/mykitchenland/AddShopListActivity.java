package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddShopListActivity extends AppCompatActivity {

    DBHandler db;
    EditText list_name;
    EditText item_name;
    EditText item_amount;
    ImageButton add;
    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_list);
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
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item=new Item();
                item.setItemName(item_name.getText().toString());
                item.setAmount(item_amount.getText().toString());
                mAdapter.AddItem(item);
                item_name.setText("");
                item_amount.setText("");
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
        Shopping_list shoppingList = create_List_And_get_list();
        int list_id=shoppingList.get_list_id();
        db.addItems(items);
        db.close();
        Toast.makeText(this, "הרשימה נוצרה בהצלחה", Toast.LENGTH_LONG).show();

        Intent Go = new Intent(this, ShopListActivity.class);
       startActivity(Go);
    }


    public Shopping_list create_List_And_get_list()
    {
        Shopping_list shopList = new Shopping_list();
        shopList.set_listname(list_name.getText().toString());
        db.addShopList(shopList);
        db.close();
        return shopList;

    }
}