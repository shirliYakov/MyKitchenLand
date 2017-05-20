package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class fullscreenimageActivity extends AppCompatActivity {

    Bitmap Myimage;
    ImageView Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_fullscreenimage);
        Image = (ImageView)findViewById(R.id.input_image3);

        Intent intent = getIntent();
        Myimage = intent.getParcelableExtra("image");
        Image.setImageBitmap(Myimage);

    }
}
