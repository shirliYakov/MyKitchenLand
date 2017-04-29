package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AddShopListActivity extends AppCompatActivity {

    private ListView mShoppingList;
    private EditText mItemEdit;
    private EditText amountItemEdit;
    private Button mAddButton;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_list);
        mItemEdit = (EditText) findViewById(R.id.item_name);
        amountItemEdit = (EditText) findViewById(R.id.amount);

        mAddButton = (Button) findViewById(R.id.add_button);
        mShoppingList = (ListView) findViewById(R.id.shopping_listView);
        ;

        //   mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        mShoppingList.setAdapter(mAdapter);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item();
                item.setItemName(mItemEdit.getText().toString());
                item.setAmount(amountItemEdit.getText().toString());
               mAdapter.add(item.getItemName());
                mAdapter.add(item.getAmount());
                mAdapter.notifyDataSetChanged();
                mItemEdit.setText("");
                amountItemEdit.setText("");
            }
        });
    }


    public void btn_add_sl_On_Click(View v) {
        //after add info to database go to
        Intent Go = new Intent(AddShopListActivity.this, ShopListActivity.class);
        startActivity(Go);
    }

}