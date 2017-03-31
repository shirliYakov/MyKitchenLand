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

    public void MyRecipesActivity(View view)
    {
        Intent myBook = new Intent(this, MyBook.class);
        startActivity(myBook);
    }

    public void LogOut(View view)
    {
        Intent logOut = new Intent(this, MainActivity.class);
        startActivity(logOut);
    }

    public void AdminRecipesActivity(View view)
    {
        Intent adminRec = new Intent(this, AdminRecipes.class);
        startActivity(adminRec);
    }

    public void ShoppingListActivity(View view)
    {
        Intent shopping = new Intent(this, ShoppingList.class);
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
