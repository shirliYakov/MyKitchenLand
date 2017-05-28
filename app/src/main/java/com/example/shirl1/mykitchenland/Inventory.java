package com.example.shirl1.mykitchenland;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName +",");
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
                if(item_name.getText().toString().isEmpty() && item_amount.getText().toString().isEmpty())
                    Toast.makeText(Inventory.this, "לא ניתן להכניס מוצר ריק", Toast.LENGTH_LONG).show();

                else if(item_name.getText().toString().isEmpty() && (!item_amount.getText().toString().isEmpty()))
                    Toast.makeText(Inventory.this, "אנא הכנס את שם המוצר", Toast.LENGTH_LONG).show();

                else if(item_amount.getText().toString().isEmpty() && (!item_name.getText().toString().isEmpty()))
                    Toast.makeText(Inventory.this, "אנא הכנס כמות", Toast.LENGTH_LONG).show();
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

    public void btn_back_On_Click(View view)
    {
        Intent menu = new Intent(this, MainMenu.class);
        startActivity(menu);
    }


    public void btn_delete_On_Click(View view)
    {
        DBHandler db=new DBHandler(this);
        db.clearInventoryTable();
        db.close();
        Toast.makeText(this, "המלאי אותחל", Toast.LENGTH_LONG).show();
        Intent refresh = new Intent(this, Inventory.class);
        startActivity(refresh);
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


    public void info_On_Click(View view) {

        String log = "\n" + "על מנת להוסיף מוצר,הוסף את פרטיו ולחץ על כפתור ה-+ " + "\n"
        +  "\n" + "על מנת להסיר מוצר מהמלאי הביתי שלך,לחץ על פח האשפה " + "\n";



        View v = LayoutInflater.from(Inventory.this).inflate(R.layout.info, null);
        final TextView info = (TextView)v.findViewById(R.id.txt_info);
        info.setText(log);

        AlertDialog.Builder builder= new AlertDialog.Builder(Inventory.this);
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
