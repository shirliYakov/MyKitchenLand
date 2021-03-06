package com.example.shirl1.mykitchenland;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WeeklyMenu extends AppCompatActivity {

    DBHandler db;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(" שלום " + MainMenu.myFullName +",");
        setContentView(R.layout.activity_weekly_menu);

        emailTo = MainMenu.myEmail;

        db = new DBHandler(this);
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

    }

    public void btn_back_On_Click(View view) {
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
                        //this.finish();
                        //Intent refresh = new Intent(WeeklyMenu.this, WeeklyMenu.class);
                        //startActivity(refresh);
                        //this.finish();
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

    public void SetContent(View view){

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
                Intent Go = new Intent(WeeklyMenu.this, DisplayMenuActivity.class);
                startActivity(Go);
            }
            else
                Toast.makeText(WeeklyMenu.this, "התפריט עדיין ריק", Toast.LENGTH_LONG).show();


        }

        if (id == R.id.sendToEmail) {

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


    public void info_On_Click(View view) {

        String log ="\n" + "ראה אפשרויות נוספות בתפריט למעלה- תצוגה נוחה ושליחת תפריט למייל" + "\n"
                + "הוסף מתכון מתוך רשימות המתכונים ליום ספציפי בכפתור המופיע לשמאלו" + "\n"
                +"מחק את כל התפריט בסוף השבוע לעדכון תפריט חדש בתחתית המסך" + "\n"
                +"ביציאה דרך 'חזרה לתפריט ראשי' יתעדכן התפריט" + "\n";

        View v = LayoutInflater.from(WeeklyMenu.this).inflate(R.layout.info, null);
      //  final TextView info = (TextView)v.findViewById(R.id.txt_info);
     //   info.setText(log);

        AlertDialog.Builder builder= new AlertDialog.Builder(WeeklyMenu.this);
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
}
