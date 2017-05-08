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



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);

        input_email = (EditText)findViewById(R.id.input_email);
        input_name = (EditText)findViewById(R.id.input_name);
        input_password = (EditText)findViewById(R.id.input_password);
        input_lastname = (EditText)findViewById(R.id.input_lastname);
    }

    public void Register_on_click(View view)
    {

        if ((input_name!=null) & (input_lastname!=null) & (input_email!=null) & (input_password!=null)) {

            String write = "נרשם בהצלחה";
            Toast.makeText(Registration.this, write /*+username database*/, Toast.LENGTH_LONG).show();

            //error red at not filled places
        }

        Intent menu = new Intent(this, MainMenu.class);
        startActivity(menu);

    }
}
