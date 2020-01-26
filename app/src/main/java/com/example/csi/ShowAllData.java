package com.example.csi;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowAllData extends AppCompatActivity {
    Timesheet_Adapter ad;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_data);
        RecyclerView recyclerView=findViewById(R.id.rcv3);
        db = new DatabaseHelper(this);
        Cursor cursor=db.allData();
        if(cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"Empty data",Toast.LENGTH_SHORT).show();
        }
        else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ad = new Timesheet_Adapter(this, cursor);
            recyclerView.setAdapter(ad);
        }
    }
}