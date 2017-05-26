package com.example.shirl1.mykitchenland;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ScrollingTabContainerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class WeeklyMenu extends AppCompatActivity {

    DBHandler db;
    ScrollView S;
    EditText day1;
    EditText day2;
    EditText day3;
    EditText day4;
    EditText day5;
    EditText day6;
    EditText day7;

    String day_1;
    String day_2;
    String day_3;
    String day_4;
    String day_5;
    String day_6;
    String day_7;
    String emailTo;
    String log;
    Week w;
    ArrayList<String> list;
    ArrayList<String> list2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName);
        setContentView(R.layout.activity_weekly_menu);

        emailTo = MainMenu.myEmail;

        db = new DBHandler(this);
        S =  (ScrollView) findViewById(R.id.ScrollWeek);
        day1 = (EditText)findViewById(R.id.txt_sunday);
        day2 = (EditText)findViewById(R.id.txt_munday);
        day3 = (EditText)findViewById(R.id.txt_thusday);
        day4 = (EditText)findViewById(R.id.txt_wedensday);
        day5 = (EditText)findViewById(R.id.txt_thursday);
        day6 = (EditText)findViewById(R.id.txt_friday);
        day7 = (EditText)findViewById(R.id.txt_saterday);


        w = db.getWeek();
        if(w!=null){

            day1.setText(w.getDay1());
            day2.setText(w.getDay2());
            day3.setText(w.getDay3());
            day4.setText(w.getDay4());
            day5.setText(w.getDay5());
            day6.setText(w.getDay6());
            day7.setText(w.getDay7());
        }

        day1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                S.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        day2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                S.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        day3.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                S.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        day4.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                S.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        day5.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                S.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        day6.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                S.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        day7.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                S.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    public void list_onclick1(View view){
        addRecipeToDay(day1);
    }
    public void list_onclick2(View view){
        addRecipeToDay(day2);
    }
    public void list_onclick3(View view){
        addRecipeToDay(day3);
    }
    public void list_onclick4(View view){
        addRecipeToDay(day4);
    }
    public void list_onclick5(View view){
        addRecipeToDay(day5);
    }
    public void list_onclick6(View view){
        addRecipeToDay(day6);
    }
    public void list_onclick7(View view){
        addRecipeToDay(day7);
    }

    public void addRecipeToDay(final EditText day){

        View view = LayoutInflater.from(WeeklyMenu.this).inflate(R.layout.activity_recipes_list, null);

        final ListView list_myRecipes = (ListView) view.findViewById(R.id.list_myrecipes);
        final ListView list_Recipes = (ListView) view.findViewById(R.id.list_recipes);

        //list1
        list = new ArrayList<>();
        Cursor data = db.getRecipeForList();
        ListAdapter listAdapter = new ArrayAdapter<>(WeeklyMenu.this, android.R.layout.simple_list_item_1, list);

        if (data.getCount() == 0) {
            Toast.makeText(WeeklyMenu.this, "המאגר עדיין ריק", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                list.add(data.getString(1));//column 2 is index of column-name
            }
        }
        list_myRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView , View view, int i, long l) {
                String D = day.getText().toString();
                if(D.isEmpty())
                    day.append(list_myRecipes.getItemAtPosition(i).toString());
                else
                    day.append("\n" + list_myRecipes.getItemAtPosition(i).toString());
            }
        });

        list_myRecipes.setAdapter(listAdapter);

        //list2
        list2 = new ArrayList<>();
        Cursor data2 =  db.getRecipeForList_manager();
        ListAdapter listAdapter2 = new ArrayAdapter<>(WeeklyMenu.this, android.R.layout.simple_list_item_1, list2);

        if (data2.getCount() == 0) {
            Toast.makeText(WeeklyMenu.this, "המאגר עדיין ריק", Toast.LENGTH_LONG).show();
        } else {
            while (data2.moveToNext()) {
                list2.add(data2.getString(1));//column 2 is index of column-name
            }
        }

        list_Recipes.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView , View view, int i, long l) {
                String D2 = day.getText().toString();
                if(D2.isEmpty())
                    day.append(list_Recipes.getItemAtPosition(i).toString());
                else
                    day.append("\n" + list_Recipes.getItemAtPosition(i).toString());
            }
        });
        list_Recipes.setAdapter(listAdapter2);

        AlertDialog.Builder builder = new AlertDialog.Builder(WeeklyMenu.this);
        builder.setCancelable(false);
        builder.setView(view)
                .setNegativeButton("סגור", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();

    }

    public void btn_back_On_Click(View view) {
        updateMenu();
        Intent menu = new Intent(this, MainMenu.class);
        startActivity(menu);
    }


    public void btn_delete_menu_On_Click(View view) {

        View v = LayoutInflater.from(WeeklyMenu.this).inflate(R.layout.activity_allowdeletemenu, null);
        AlertDialog.Builder builder= new AlertDialog.Builder(WeeklyMenu.this);
        builder.setCancelable(false);

        builder.setView(v)
                .setPositiveButton("מחק", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        db.clearTableWeek();
                        Intent refresh = new Intent(WeeklyMenu.this, WeeklyMenu.class);
                        startActivity(refresh);

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

    /*public void SetContent(View view){

        day_1 = day1.getText().toString();
        day_2 = day2.getText().toString();
        day_3 = day3.getText().toString();
        day_4 = day4.getText().toString();
        day_5 = day5.getText().toString();
        day_6 = day6.getText().toString();
        day_7 = day7.getText().toString();

        Week week = new Week(day_1, day_2, day_3, day_4, day_5, day_6, day_7);
        db.setWeek(week);
        Toast.makeText(WeeklyMenu.this, "התפריט עודכן בהצלחה", Toast.LENGTH_LONG).show();
    }*/

    public void updateMenu(){

        day_1 = day1.getText().toString();
        day_2 = day2.getText().toString();
        day_3 = day3.getText().toString();
        day_4 = day4.getText().toString();
        day_5 = day5.getText().toString();
        day_6 = day6.getText().toString();
        day_7 = day7.getText().toString();

        Week week = new Week(day_1, day_2, day_3, day_4, day_5, day_6, day_7);
        db.setWeek(week);
        Toast.makeText(WeeklyMenu.this, "התפריט עודכן בהצלחה", Toast.LENGTH_LONG).show();
    }



    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.myweekmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Week w1 = db.getWeek();

        if (id == R.id.display) {
            if(w1!=null){
                updateMenu();
                Intent Go = new Intent(WeeklyMenu.this, DisplayMenuActivity.class);
                startActivity(Go);
            }
            else
                Toast.makeText(WeeklyMenu.this, "התפריט עדיין ריק", Toast.LENGTH_LONG).show();


        }

        if (id == R.id.sendToEmail) {
            updateMenu();
            log =" ";
            Week wee = db.getWeek();
            if(wee!=null) {
                log = log + "יום ראשון:" + "\n" + wee.getDay1() + "\n\n" + "יום שני:" + "\n" + wee.getDay2() + "\n\n" +
                        "יום שלישי:" + "\n" + wee.getDay3() + "\n\n" + "יום רביעי:" + "\n" + wee.getDay4() + "\n\n" +
                        "יום חמישי:" + "\n" + wee.getDay5() + "\n\n" + "יום שישי:" + "\n" + wee.getDay6() + "\n\n" +
                        "יום שבת:" + "\n" + wee.getDay7() + "\n" +  "נשלח מאפליקציית: " +
                        "My Kitchen Land";
            }

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {emailTo};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, "התפריט השבועי שלי");
            intent.putExtra(Intent.EXTRA_TEXT, log);
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Send email..."));
        }
        return super.onOptionsItemSelected(item);

    }
}
