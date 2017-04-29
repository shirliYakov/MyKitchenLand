package com.example.shirl1.mykitchenland;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.content.Intent;


/**
 * Created by shirl on 18/04/2017.
 */

class Item
{
    int list_id;
    String itemName;
    String amount;
    int item_id;

    public void set_list_id(int list_id){
        this.list_id = list_id;
    }

    public void setItemName(String itemName)
    {
            this.itemName = itemName;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public int getItemId()
        {
        return item_id;
         }

    public void setItemId(int item_id)
    {
        this.item_id = item_id;
    }

    public String getItemName()
    {
        return itemName;
    }


    public String getAmount() {
        return amount;
    }

    public int get_list_id(){
        return this.list_id;
    }

}
