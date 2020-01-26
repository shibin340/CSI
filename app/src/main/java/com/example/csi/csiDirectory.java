package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class csiDirectory extends AppCompatActivity {
    DatabaseHelper db;
    Data_Adapter mAdapter;
    EditText etdata;
    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csi_directory);
        etdata = findViewById(R.id.et_search);
        search = findViewById(R.id.btsearch);
        db = new DatabaseHelper(getApplicationContext());
        final RecyclerView recyclerView= findViewById(R.id.rcv1);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etdata.getText().toString().trim().equals(""))
                {
                    Cursor cursor = db.allData();
                    Toast.makeText(getApplicationContext(),"Enter data to search",Toast.LENGTH_SHORT).show();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    mAdapter=new Data_Adapter(getApplicationContext(),cursor);
                    recyclerView.setAdapter(mAdapter);
                }
                else
                {
                    String search = etdata.getText().toString().trim();
                    Cursor cursor = db.searchData(search);
                    if(cursor.getCount()==0)
                    {
                        Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        mAdapter=new Data_Adapter(getApplicationContext(),cursor);
                        recyclerView.setAdapter(mAdapter);
                    }
                }
            }
        });
    }
}
