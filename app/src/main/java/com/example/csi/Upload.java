package com.example.csi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Upload extends AppCompatActivity {

    Button eve_bt,task_bt,gal_add_bt,assign_doj,assign_empid;
    DatabaseHelper db;
    TextView admin_permit_heading;
    Permission_Adapter pa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        eve_bt=findViewById(R.id.event_add_bt);
        task_bt=findViewById(R.id.task_add_bt);
        gal_add_bt=findViewById(R.id.gal_add_bt);
        assign_doj=findViewById(R.id.add_doj_bt);
        assign_empid=findViewById(R.id.add_empid_bt);
        final RecyclerView recyclerView=findViewById(R.id.admin_rcv);
        admin_permit_heading=findViewById(R.id.admin_permit_heading);
        db = new DatabaseHelper(this);
        Cursor cursor=db.getRequestApproval();
        if(cursor.getCount()!=0)
        {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            pa=new Permission_Adapter(this,cursor);
            recyclerView.setAdapter(pa);
            recyclerView.addItemDecoration(new DividerItemDecoration(this,
                    DividerItemDecoration.VERTICAL));
        }
        else
        {
            admin_permit_heading.setText("No Permission Seekers!");
            recyclerView.setVisibility(View.GONE);
        }
        //
        eve_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminAddEvent.class));
            }
        });
        task_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminAssignTask.class));
            }
        });
        gal_add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Upload_gallery.class));
            }
        });
        assign_doj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminAssignDOJ.class));
            }
        });
        assign_empid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminAssignEmpID.class));
            }
        });
    }
}
