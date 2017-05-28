package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.provider.CalendarContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by shirl on 24/04/2017.
 */


public class ShowItemAdapter extends RecyclerView.Adapter<ShowItemAdapter.ViewHolder>
{
    private List<Item> items;
    DBHandler db;
    String itemName,amount;
    int index;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv;
        public TextView amuntTv;



        public ViewHolder(View v) {
            super(v);
            nameTv = (TextView) v.findViewById(R.id.name);
            amuntTv=(TextView)v.findViewById(R.id.amount);

        }
    }

    public ShowItemAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        index =holder.getAdapterPosition();
        final Item item = items.get(index);
        itemName=holder.nameTv.getText().toString();
        amount=holder.amuntTv.getText().toString();
        holder.nameTv.setText(item.itemName);
        holder.amuntTv.setText(item.amount);

        holder.nameTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                db = new DBHandler(v.getContext());
                db.addItemFromShoppingList(item.getItemName().toString(), item.getAmount());

                (v).setBackgroundColor(Color.GREEN);
                Toast.makeText(v.getContext(), "המוצר נוסף למלאי הביתי", Toast.LENGTH_LONG).show();
                notifyDataSetChanged();
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void AddItem(Item item){
        items.add(item);
        notifyDataSetChanged();
    }

    public List<Item> getItems()
    {
        return items;
    }
}