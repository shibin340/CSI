package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class FullScreenImage extends AppCompatActivity {

    ImageView fullimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        fullimg=findViewById(R.id.fullimg);
        Intent intent=getIntent();
        byte[] imageBytes=intent.getByteArrayExtra("imginbytes");
        Bitmap bitmap= BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
        fullimg.setImageBitmap(bitmap);

    }
}
