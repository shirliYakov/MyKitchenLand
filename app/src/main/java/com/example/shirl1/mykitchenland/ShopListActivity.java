package com.example.shirl1.mykitchenland;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class ShopListActivity extends AppCompatActivity {
    DBHandler db;
    ListView listView;
    TextView info;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    // private RecyclerView myRecyclerView;
    //private ShopListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        db = new DBHandler(this);
        listView = (ListView) findViewById(R.id.list_list);
        info = (TextView) findViewById(R.id.showtable);
        ArrayList<String> list = new ArrayList<>();
        Cursor data = db.getListOfShopList();

        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        if (data.getCount() == 0) {
            Toast.makeText(this, "המאגר עדיין ריק", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                list.add(data.getString(1));//column 2 is index of column-name
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ShopListActivity.this, ShowList.class);
                intent.putExtra("Name", listView.getItemAtPosition(i).toString());

                startActivity(intent);
            }
        });


        listView.setAdapter(listAdapter);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void btn_delete(View v) {
        db.clearShopListTable();
        Intent again = new Intent(this, ShopListActivity.class);
        startActivity(again);
        this.finish();
    }

    public void btn_back_On_Click(View v) {
        Intent Go = new Intent(ShopListActivity.this, MainMenu.class);
        startActivity(Go);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shoplistmenu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        final Context context = this;
        int id = item.getItemId();
        switch (id) {
            case R.id.add_sl:
                Intent Go = new Intent(ShopListActivity.this, AddShopListActivity.class);
                startActivity(Go);
                break;

            case R.id.search_sl: {
                View view = LayoutInflater.from(ShopListActivity.this).inflate(R.layout.activity_search_list, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ShopListActivity.this);
                builder.setCancelable(false);
                final EditText list_name;
                list_name = (EditText) view.findViewById(R.id.search_by_name);
                builder.setView(view).setTitle("חפש רשימת קניות")
                        .setIcon(R.drawable.search)
                        .setPositiveButton("חפש", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(ShopListActivity.this, ShowList.class);
                                intent.putExtra("Name", list_name.getText().toString());
                                startActivity(intent);
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
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ShopList Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }



    /*print in text view
    db = new DBHandler(this);
    info = (TextView) findViewById(R.id.txt_show);
    Cursor i = db.getListOfItems();
    Cursor c = db.getListOfShopList();

    if ((c.getCount() == 0) ) {
        Toast.makeText(this, "לא קיימות רשימות", Toast.LENGTH_LONG).show();
        return;
    }
    StringBuffer buffer = new StringBuffer();
    while (c.moveToNext()&&i.moveToNext()) {
        buffer.append("מספר רשימה: " + c.getString(0) + "\n");
        buffer.append("שם הרשימה: " + c.getString(1) + "\n");
        buffer.append("מספר רשימה: "+i.getString(0)+"\n");
        buffer.append("שם הפריט: "+i.getString(1)+"\n");
        buffer.append("כמות: "+i.getString(2)+"\n");

    }
    info.setText(buffer);*/
}
