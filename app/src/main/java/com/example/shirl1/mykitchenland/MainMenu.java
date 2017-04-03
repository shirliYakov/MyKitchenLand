package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void MyRecipesActivity_onClick(View view)
    {
        Intent myBook = new Intent(MainMenu.this, MyRecipesActivity.class);
        startActivity(myBook);
    }

    public void LogOut(View view)
    {
        Intent logOut = new Intent(this, MainActivity.class);
        startActivity(logOut);
    }

    public void AdminRecipesActivity_onClick(View view)
    {
        Intent adminRec = new Intent(this, RecipesActivity.class);
        startActivity(adminRec);
    }

    public void ShoppingListActivity_onClick(View view)
    {
        Intent shopping = new Intent(this, ShopListActivity.class);
        startActivity(shopping);
    }

    public void WeeklyMenuLayout(View view)
    {
        Intent weeklyMenu = new Intent(this, WeeklyMenu.class);
        startActivity(weeklyMenu);
    }

    public void inventoryLayout(View view)
    {
        Intent inventory = new Intent(this, Inventory.class);
        startActivity(inventory);
    }

}
