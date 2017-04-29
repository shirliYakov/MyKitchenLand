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

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nameTv;
        public TextView amuntTv;
        public ImageButton delete;

        public ViewHolder(View v) {
            super(v);
            nameTv = (TextView) v.findViewById(R.id.name);
            amuntTv=(TextView)v.findViewById(R.id.amount);
            delete=(ImageButton)v.findViewById(R.id.btn_delete);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ItemAdapter(List<Item> items) {
        this.items = items;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_show, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
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

    // Return the size of your dataset (invoked by the layout manager)
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