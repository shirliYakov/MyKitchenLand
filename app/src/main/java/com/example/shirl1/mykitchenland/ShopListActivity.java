package com.example.shirl1.mykitchenland;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShopListActivity extends AppCompatActivity {
    DBHandler db;
    TextView info;
    EditText list_name;
    private RecyclerView mRecyclerView;
    private ShopListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new ShopListAdapter((ArrayList)db.getListOfshoppingList());
        mRecyclerView.setAdapter(mAdapter);

    }

    public void btn_delete(View v)
    {
        db.clearShopListTable();
        Intent again = new Intent(this, ShopListActivity.class);
        startActivity(again);
        this.finish();
    }

    public void btn_back_On_Click(View v){
        Intent Go = new Intent(ShopListActivity.this, MainMenu.class);
        startActivity(Go);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.shoplistmenu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_sl) {
            Intent Go = new Intent(ShopListActivity.this, AddShopListActivity.class);
            startActivity(Go);
        }

        return super.onOptionsItemSelected(item);
    }


    /*print in text view
    db = new DBHandler(this);
    info = (TextView) findViewById(R.id.txt_show);
    Cursor i = db.getListOfItems();
    Cursor c = db.getListOfShopList();

    if ((c.getCount() == 0) ) {
        Toast.makeText(this, "לא קיימות רשימות", Toast.LENGTH_LONG).show();
        return;
    }
    StringBuffer buffer = new StringBuffer();
    while (c.moveToNext()&&i.moveToNext()) {
        buffer.append("מספר רשימה: " + c.getString(0) + "\n");
        buffer.append("שם הרשימה: " + c.getString(1) + "\n");
        buffer.append("מספר רשימה: "+i.getString(0)+"\n");
        buffer.append("שם הפריט: "+i.getString(1)+"\n");
        buffer.append("כמות: "+i.getString(2)+"\n");

    }
    info.setText(buffer);*/
}
