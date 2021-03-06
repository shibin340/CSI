package com.example.csi;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;

public class UserData extends AppCompatActivity {
    TextView txt1,txt2,txt3,txt4,txt5;
    DatabaseHelper db;
    Cursor mcursor,cursor1;
    ImageView img;
    public String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        img=findViewById(R.id.img_user);
        txt1=findViewById(R.id.u_txt);
        txt2=findViewById(R.id.e_txt);
        txt3=findViewById(R.id.d_txt);
        txt4=findViewById(R.id.p_txt);
        txt5=findViewById(R.id.id_value);
        Intent intent = getIntent();
        String data;
        db = new DatabaseHelper(this);
        data = intent.getStringExtra("FileName");
        try {
            mcursor = db.userdata(data);
            if (mcursor.moveToFirst()) {
                do {
                    String username = mcursor.getString(mcursor.getColumnIndex("username"));
                    String dateofbday = mcursor.getString(mcursor.getColumnIndex("dob"));
                    String phno = mcursor.getString(mcursor.getColumnIndex("Phonenum"));
                     email = mcursor.getString(mcursor.getColumnIndex("email"));
                    String empid = mcursor.getString(mcursor.getColumnIndex("EmpID"));
                    /*byte[] imagebyte = mcursor.getBlob(mcursor.getColumnIndex("image"));
                    if (imagebyte != null) {
                        ByteArrayInputStream inputStream = new ByteArrayInputStream(imagebyte);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        img.setImageBitmap(bitmap);
                    } else {
                        img.setBackgroundResource(R.drawable.ic_person_black_24dp);

                    }*/
                    txt1.setText(username);
                    txt2.setText(email);
                    txt3.setText(dateofbday);
                    txt4.setText(phno);
                    txt5.setText(empid);
                } while (mcursor.moveToNext());
            }

             cursor1=db.getimage(email);
             if((cursor1 != null) && (cursor1.getCount() > 0)) {
                if (cursor1.moveToFirst())
                {
                    do {
                    if (cursor1.getBlob(cursor1.getColumnIndex("image")) != null) {
                        byte[] imagebyte = cursor1.getBlob(cursor1.getColumnIndex("image"));
                         ByteArrayInputStream inputStream = new ByteArrayInputStream(imagebyte);
                         Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        img.setImageBitmap(bitmap);
                    }
                    else {
                        img.setBackgroundResource(R.drawable.ic_person_black_24dp);
                    }
                       } while (cursor1.moveToNext());
                }
              }
                else
                {
                    img.setBackgroundResource(R.drawable.ic_person_black_24dp);
                }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}

