package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddShopListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_list);
    }

    public void btn_add_sl_On_Click(View v){
        //after add info to database go to
        Intent Go = new Intent(AddShopListActivity.this, ShopListActivity.class);
        startActivity(Go);
    }

}
