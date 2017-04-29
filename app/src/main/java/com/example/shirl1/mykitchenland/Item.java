package com.example.shirl1.mykitchenland;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * Created by shirl on 18/04/2017.
 */

class Item
{
    String itemName;
    String amount;


    public void setItemName(String itemName)
    {
            this.itemName = itemName;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getItemName()
        {
        return itemName;
         }

    public String getAmount() {
        return amount;
    }
}
