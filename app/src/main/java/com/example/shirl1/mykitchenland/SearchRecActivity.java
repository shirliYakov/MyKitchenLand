package com.example.shirl1.mykitchenland;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchRecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search_rec);
    }


}
