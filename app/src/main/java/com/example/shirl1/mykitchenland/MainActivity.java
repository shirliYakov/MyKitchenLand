package com.example.shirl1.mykitchenland;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    DBHandler db;
    EditText email;
    EditText password;
    String email1;
    String pass1;
    USERS useri;
    Boolean flag;
    TextView print;
    String FullName;



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        email = (EditText)findViewById(R.id.emailEt);
        password = (EditText)findViewById(R.id.passEt);
        flag = true;

        db = new DBHandler(this);
        Cursor data = db.getUsersForList();

        if(data.getCount()==0){
            USERS u = new USERS("MANAGER", "N/S", "admin", "123");
            db.addUser(u);
        }

        //printall users
        /*print = (TextView)findViewById(R.id.printtable) ;
        //print table
        String log ="";
        List<USERS> userList = db.getAllusers();
        for (USERS u : userList) {
            log = log + "email: " + u.getEmail() + " , pass: "  + u.getPassword()
                    + ", name: " + u.getName() + ", lastname: " + u.getLastname() + "\n";
            print.setText(log);
        }*/
    }

    public void Register(View view)
    {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);

    }

    public void forget_on_click(View view){

        email1 = email.getText().toString();

        if(email1.isEmpty())
            Toast.makeText(MainActivity.this, "אנא הכנס את האימייל שלך", Toast.LENGTH_LONG).show();
        else {

            useri = db.getUserByEmail(email1);
            if (useri != null) {

                String log = "שם המשתמש שלך הוא " + email1 + "\n" + "הסיסמא שלך היא " + useri.getPassword() + "\n" +
                        "נשלח מאפליקציית " + "My Kitchen Land";

                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.setData(Uri.parse("mailto:"));
                String[] to = {email1};
                mail.putExtra(Intent.EXTRA_EMAIL, to);
                mail.putExtra(Intent.EXTRA_SUBJECT, "פרטי הרשמה לאפליקציה לניהול מטבח");
                mail.putExtra(Intent.EXTRA_TEXT, log);
                mail.setType("message/rfc822");
                startActivity(Intent.createChooser(mail, "Send email..."));
            }
            else
                Toast.makeText(MainActivity.this, "משתמש לא קיים" +
                        "\n" + "נסה שוב או הירשם", Toast.LENGTH_LONG).show();

        }
    }

    public void login(View view){

        email1 = email.getText().toString();
        pass1 = password.getText().toString();
        useri = db.getUserByEmail(email1);

        //FullName = useri.getName().toString() + " " + useri.getLastname().toString();


        if(useri!= null) {

            String myPass = useri.getPassword().toString();
            if (pass1.equals(myPass)){

                db.changePosition(useri);
                Intent intent = new Intent(MainActivity.this, MainMenu.class);
                //intent.putExtra("FullName", FullName);
                startActivity(intent);

            } else {
                Toast.makeText(MainActivity.this, "סיסמא לא נכונה" +
                        "\n" + "נסה שוב", Toast.LENGTH_LONG).show();
                password.setText("");
            }
        }
        else {
            Toast.makeText(MainActivity.this, "משתמש לא קיים" +
                    "\n" + "נסה שוב או הירשם", Toast.LENGTH_LONG).show();
            password.setText("");
            email.setText("");

        }

    }



}
