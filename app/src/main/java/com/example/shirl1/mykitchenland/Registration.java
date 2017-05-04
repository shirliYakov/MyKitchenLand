package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText input_email;
    EditText input_name;
    EditText input_password;
    EditText input_lastname;

    String email;
    String password;
    String name;
    String lastname;
    DBHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_registration);

        input_email = (EditText)findViewById(R.id.input_email);
        input_name = (EditText)findViewById(R.id.input_name);
        input_password = (EditText)findViewById(R.id.input_password);
        input_lastname = (EditText)findViewById(R.id.input_lastname);
        db = new DBHandler(this);

    }

    public void Register_on_click(View view) {

        String log= "";
        email = input_email.getText().toString();
        password = input_password.getText().toString();
        name= input_name.getText().toString();
        lastname = input_lastname.getText().toString();

        if(email.isEmpty())
            log = log + " הכנס דואר אקלטרוני " + "\n";

        if(password.isEmpty())
            log = log + " הכנס סיסמא " + "\n";

        if(name.isEmpty())
            log = log + " הכנס שם פרטי " + "\n";

        if(lastname.isEmpty())
            log = log + " הכנס שם משפחה " + "\n";

        if(log.isEmpty()) {

            USERS user = new USERS(name, lastname, email, password);
            db.addUser(user);
            Toast.makeText(Registration.this, "נרשם בהצלחה", Toast.LENGTH_LONG).show();

            String FullName = name + " " + lastname;

            Intent intent = new Intent(Registration.this, MainMenu.class);
            intent.putExtra("FullName", FullName);
            startActivity(intent);

        }

        else
            Toast.makeText(Registration.this, "שים לב" + "\n" + log, Toast.LENGTH_LONG).show();

    }
}
