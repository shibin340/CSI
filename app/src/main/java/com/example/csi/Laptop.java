package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class Laptop extends AppCompatActivity {
    TextView Lap_username,Lap_empid,Lap_id,Lap_date;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        Lap_username=findViewById(R.id.laptop_username);
        Lap_date=findViewById(R.id.laptop_issued_date);
        Lap_empid=findViewById(R.id.laptop_empid);
        Lap_id=findViewById(R.id.laptop_id);
        db = new DatabaseHelper(getApplicationContext());
        final Intent intent = getIntent();
        final String userid = intent.getStringExtra("username");
        Cursor cursor = db.getLaptopData(userid);
        cursor.moveToFirst();
        Lap_username.setText(userid);
        Lap_username.setTextColor(getResources().getColor(R.color.BLACK));
        String employee_id = cursor.getString(cursor.getColumnIndex("EmpID"));
        Lap_empid.setText(employee_id);
        Lap_empid.setTextColor(getResources().getColor(R.color.BLACK));
        String issue_date = cursor.getString(cursor.getColumnIndex("Laptop_issue_date"));
        Lap_date.setText(issue_date);
        Lap_date.setTextColor(getResources().getColor(R.color.BLACK));
        String laptop_id = cursor.getString(cursor.getColumnIndex("LaptopID"));
        Lap_id.setText(laptop_id);
        Lap_id.setTextColor(getResources().getColor(R.color.BLACK));
    }
}
