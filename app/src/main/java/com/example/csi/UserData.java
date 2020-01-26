package com.example.csi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserData extends AppCompatActivity {
    TextView txt1,txt2,txt3,txt4;
    DatabaseHelper db;
    Cursor mcursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        txt1=findViewById(R.id.u_txt);
        txt2=findViewById(R.id.e_txt);
        txt3=findViewById(R.id.d_txt);
        txt4=findViewById(R.id.t_txt);
        Intent intent = getIntent();
        String data;
        db = new DatabaseHelper(this);
        data = intent.getStringExtra("FileName");
        mcursor = db.userdata(data);
        if (mcursor.moveToFirst()){
            do{
                String username = mcursor.getString(mcursor.getColumnIndex("username"));
                String dateofbday = mcursor.getString(mcursor.getColumnIndex("dob"));
                String task=mcursor.getString(mcursor.getColumnIndex("task"));
                String email=mcursor.getString(mcursor.getColumnIndex("email"));
                txt1.setText(username);
                txt3.setText(dateofbday);
                txt4.setText(task);
                txt2.setText(email);
            }while(mcursor.moveToNext());
        }
    }
}

