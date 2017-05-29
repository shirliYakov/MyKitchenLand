package com.example.shirl1.mykitchenland;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class viewShopList extends AppCompatActivity {
    int listID;
    Button add;
    TextView list_name;
    RecyclerView items;
    List<Item> forInventoryList;
    DBHandler db;
    ShowItemAdapter mAdapter;
    String listname;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shop_list);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName + ",");

        Intent intent = getIntent();
        intent.getIntExtra("list_id", listID);
        list_name = (TextView) findViewById(R.id.list_name);
        items = (RecyclerView) findViewById(R.id.items_list);
        add=(Button) findViewById(R.id.addToInve) ;
        items.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        db = new DBHandler(this);
        Bundle bundle = getIntent().getExtras();
        listname = bundle.getString("Name");
        ArrayList<String> list = new ArrayList<>();
        Shopping_list s = db.getShopListByID(listname);

        if (s == null) {
            Toast.makeText(this, "הרשימה לא קיימת", Toast.LENGTH_LONG).show();
            Intent goBack = new Intent(this, ShopListActivity.class);
            startActivity(goBack);
        } else {
            list_name.setText(s.get_listname());
            mAdapter = new ShowItemAdapter(db.getItemsByListId(s.get_list_id()));
            items.setAdapter(mAdapter);
            items.setVisibility(View.VISIBLE);
            items.setHasFixedSize(true);

        }
        forInventoryList=mAdapter.getItems();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                db.addShopListToInventory(forInventoryList);
                Toast.makeText(viewShopList.this,"המוצרים התווספו למלאי הביתי",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void btn_back_On_Click(View v) {
        Intent Go = new Intent(this, ShopListActivity.class);
        startActivity(Go);
    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.show_list_menu, menu);
        return true;
    }

    public void info_On_Click(View view) {

        String log = "\n" + "אם רכשת את המוצר,לחץ עליו לחיצה ארוכה" + "\n"+
                "\n" + "לחיצה זו תוסיף אותו למלאי הביתי שלך ותסמן את רקעו בירוק" + "\n";


        View v = LayoutInflater.from(viewShopList.this).inflate(R.layout.info, null);
        final TextView info = (TextView)v.findViewById(R.id.txt_info);
        info.setText(log);

        AlertDialog.Builder builder= new AlertDialog.Builder(viewShopList.this);
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
        if (id==R.id.edit)
        {
            Intent intent = new Intent(viewShopList.this, ShowList.class);
            intent.putExtra("Name", listname);

            startActivity(intent);
        }
        if (id == R.id.delete_list) {
            final View view = LayoutInflater.from(viewShopList.this).inflate(R.layout.activity_allow_delete_list, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(viewShopList.this);

            builder.setCancelable(false);

            builder.setView(view)
                    .setPositiveButton("מחק", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            delete_table_on_click(view);
                            Intent Go = new Intent(viewShopList.this, ShopListActivity.class);
                            startActivity(Go);
                        }

                    })
                    .setNegativeButton("סגור", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }


        if (id == R.id.send_to_email)
        {
            String log = " ";
            String email= MainMenu.myEmail;;
            log = log + listname + " \n\n" ;
            List <Item> items=mAdapter.getItems();
            for (Item _item:items)
            {
                log+=_item.getItemName();
                log+= "         ";
                log+=_item.getAmount();
                log+= "\n ";
            }
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {email};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, "רשימת קניות: "+ listname);
            intent.putExtra(Intent.EXTRA_TEXT, log);
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Send email..."));

        }

        return super.onOptionsItemSelected(item);

    }


    public void delete_table_on_click(View v) {
        db.delete_shoplist(listname);
        Toast.makeText(this, "הרשימה נמחקה", Toast.LENGTH_LONG).show();
        Intent Go = new Intent(this, ShopListActivity.class);
        startActivity(Go);
    }
}