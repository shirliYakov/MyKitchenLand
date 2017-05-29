package com.example.shirl1.mykitchenland;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ShowInventory extends AppCompatActivity {
    DBHandler db;
    EditText item_name;
    EditText item_amount;
    ImageButton add;
    int listID;
    private RecyclerView items;
    private ShowItemAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_inventory);
        db=new DBHandler(this);
        if (db.getInventory()==null)

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName +",");
        items = (RecyclerView) findViewById(R.id.products);
        items.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        db = new DBHandler(this);
        List<Item> inventory=db.getInventory();
        if (inventory==null)
            Toast.makeText(this, "המלאי ריק", Toast.LENGTH_LONG).show();
        else
            {
            mAdapter = new ShowItemAdapter(inventory);
            items.setAdapter(mAdapter);
            items.setVisibility(View.VISIBLE);
            items.setHasFixedSize(true);
        }


    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.inventory_menu, menu);
        return true;
    }

    public void info_On_Click(View view) {

        String log = "\n" + "לחץ על התפריט בצד שמאל על מנת לערוך את המלאי או לאפסו" + "\n";



        View v = LayoutInflater.from(ShowInventory.this).inflate(R.layout.info, null);
        final TextView info = (TextView)v.findViewById(R.id.txt_info);
        info.setText(log);

        AlertDialog.Builder builder= new AlertDialog.Builder(ShowInventory.this);
        builder.setView(v)
                .setTitle("מידע כללי")
                .setIcon(R.drawable.infopink)
                .setNegativeButton("סגור", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        String name;
        int id = item.getItemId();
        if (id == R.id.edit)
        {
            Intent intent = new Intent(ShowInventory.this, Inventory.class);
            startActivity(intent);
        }
        else if (id==R.id.delete)
        {
            deleteInventory();
        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteInventory()
    {
        DBHandler db=new DBHandler(this);
        db.clearInventoryTable();
        db.close();
        Toast.makeText(this, "המלאי אותחל", Toast.LENGTH_LONG).show();
        Intent refresh = new Intent(this, ShowInventory.class);
        startActivity(refresh);
    }




        public void btn_back_On_Click(View view)
        {
            Intent menu = new Intent(this, MainMenu.class);
            startActivity(menu);
        }


}
