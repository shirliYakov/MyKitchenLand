package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
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
    EditText item_name;
    EditText item_amount;
    ImageButton add;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Intent intent = getIntent();
        intent.getIntExtra("list_id", listID);
        // Toast.makeText(this, "הרשימה נוצרה בהצלחה", Toast.LENGTH_LONG).show();
        list_name = (EditText) findViewById(R.id.list_name);

        items = (RecyclerView) findViewById(R.id.items_list);
        items.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        db = new DBHandler(this);
        Bundle bundle = getIntent().getExtras();
        listname = bundle.getString("Name");
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
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item item = new Item();
                    if (item_name.getText().equals(""))
                        item_name.setError("אין להשאיר שדה ריק");

                    if (item_amount.getText().equals(""))
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


    public void btn_save_On_Click(View v)
    {
        List<Item> items=mAdapter.getItems();
        new_name=list_name.getText().toString();//save the list new name
        if (list_name.getText().equals(""))
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


    public void delete_table_on_click(View v)
    {
        db.delete_shoplist(listname);
        Toast.makeText(this, "הרשימה נמחקה", Toast.LENGTH_LONG).show();
        Intent Go = new Intent(this, ShopListActivity.class);
        startActivity(Go);
    }


}