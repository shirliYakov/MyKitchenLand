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
 * Created by shirl on 29/04/2017.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {
    private List<Shopping_list> shopping_lists;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView listName;
        public ImageButton delete;

        public ViewHolder(View v) {
            super(v);
            listName = (TextView) v.findViewById(R.id.name);
            delete = (ImageButton) v.findViewById(R.id.btn_delete);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ShopListAdapter(List<Shopping_list> shopping_lists) {
        this.shopping_lists = shopping_lists;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listof_shopping_list, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        int index =holder.getAdapterPosition();
        final Shopping_list shoplist = shopping_lists.get(index);

        holder.listName.setText(shoplist.list_name);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopping_lists.remove(shoplist);
                notifyDataSetChanged();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return shopping_lists.size();
    }

    public void setAdapter(List <Shopping_list> list)
    {
        this.shopping_lists=list;
    }

    public void AddItem(Shopping_list shoplist){
        shopping_lists.add(shoplist);
        notifyDataSetChanged();
    }

    public List<Shopping_list> getShopping_list()
    {
        return shopping_lists;
    }
}



