package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends AppCompatActivity {


        DBHandler db;
        EditText item_name;
        EditText item_amount;
        ImageButton add;
        int listID;
        private RecyclerView items;
        private ItemAdapter mAdapter;


        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
         items = (RecyclerView) findViewById(R.id.products);
        items.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        db = new DBHandler(this);
        List<Item> inventory=db.getInventory();
        mAdapter=new ItemAdapter(inventory);
        items.setAdapter(mAdapter);
        items.setVisibility(View.VISIBLE);
        items.setHasFixedSize(true);

        add = (ImageButton) findViewById(R.id.add_more_btn);
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

    public void btn_back_On_Click(View view)
    {
        Intent menu = new Intent(this, MainMenu.class);
        startActivity(menu);
    }

   public void btn_add_On_Click(View v)
    {
        List<Item> items=mAdapter.getItems();
        db.addItemsToInventory(items);
        db.close();
        Toast.makeText(this, "המלאי עודכן בהצלחה", Toast.LENGTH_LONG).show();

        Intent Go = new Intent(this, MainMenu.class);
        startActivity(Go);
    }
}
