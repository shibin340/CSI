package com.example.csi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class upload_profile extends AppCompatActivity {

    Button bt_choose,bt_upload,back;
    ImageView imag_upload;
    private static final int pick_image_request=100;
    Uri imgpath;
    Bitmap imagetostore;
    DatabaseHelper db;
    String mail="ansarisaifulla7@gmail.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile);
        bt_choose=findViewById(R.id.bt_choose);
        bt_upload=findViewById(R.id.bt_upload);
        imag_upload=findViewById(R.id.img_upload);
        db=new DatabaseHelper(this);
        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        bt_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,pick_image_request);
            }
        });

        bt_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mail.isEmpty() && imag_upload.getDrawable()!=null) {
                    if(db.storeimage(imagetostore,email))
                        Toast.makeText(getApplicationContext(),"inserted successfully ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode==pick_image_request && resultCode==RESULT_OK &&  data!=null && data.getData()!=null) {
                imgpath = data.getData();
                imagetostore = MediaStore.Images.Media.getBitmap(getContentResolver(), imgpath);
                imag_upload.setImageBitmap(imagetostore);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}