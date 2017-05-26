package com.example.shirl1.mykitchenland;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shirl on 24/04/2017.
 */


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>
{
    private List<Item> items;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv;
        public TextView amuntTv;
        public ImageButton delete;

        public ViewHolder(View v)
        {
            super(v);
            nameTv = (TextView) v.findViewById(R.id.name);
            amuntTv=(TextView)v.findViewById(R.id.amount);
            delete=(ImageButton)v.findViewById(R.id.btn_delete);
        }
    }

    public ItemAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_show, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int index =holder.getAdapterPosition();
        final Item item = items.get(index);

        holder.nameTv.setText(item.itemName);
        holder.amuntTv.setText(item.amount);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(item);
                notifyDataSetChanged();
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