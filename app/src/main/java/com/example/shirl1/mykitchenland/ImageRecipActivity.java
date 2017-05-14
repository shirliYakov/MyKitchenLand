package com.example.shirl1.mykitchenland;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class ImageRecipActivity extends AppCompatActivity {

    ImageView IV;
    Button camera, upload, gallery;

    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_recip);

        IV = (ImageView) findViewById(R.id.imageViewCamera);
        Button btn = (Button) findViewById(R.id.btn_takepicture);

        //if user have no camera
        if (!hasCamera())
            btn.setEnabled(false);

    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);//PASS RESULT TO onactivityresult
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
           Bundle extras = data.getExtras();
           Bitmap photo = (Bitmap) extras.get("data");
           IV.setImageBitmap(photo);

           Intent intent = new Intent(ImageRecipActivity.this, AddRecActivity.class);
           intent.putExtra("img", photo);
           startActivity(intent);
       }
    }
}
