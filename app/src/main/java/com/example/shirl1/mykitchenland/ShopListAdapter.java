package com.example.shirl1.mykitchenland;

import android.content.Context;
import android.content.Intent;
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

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder>
{
    private List<Shopping_list> shopping_lists;
    private Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView listName;
        public ImageButton delete;
        private Context context;

        public ViewHolder(View v)
        {
            super(v);
            context = v.getContext();
            listName = (TextView) v.findViewById(R.id.listname);
            delete = (ImageButton) v.findViewById(R.id.delete);
        }
    }


    public ShopListAdapter(List<Shopping_list> shopping_lists) {
        this.shopping_lists = shopping_lists;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listof_shopping_list, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

   @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
   {
       int index = holder.getAdapterPosition();
       final Shopping_list shoplist = shopping_lists.get(index);

        holder.listName.setText(shoplist.list_name);
        holder.listName.setClickable(true);
        holder.delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               shopping_lists.remove(shoplist);
               ////add sql for remove shoplist from database
               notifyDataSetChanged();
           }
       });
   }


    @Override
    public int getItemCount()
       {
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



