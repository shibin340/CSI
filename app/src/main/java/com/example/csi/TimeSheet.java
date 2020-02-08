package com.example.csi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TimeSheet extends AppCompatActivity {

    TextView t_txt;
    EditText t_edittxt;
    Button t_bt1,t_bt2;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_sheet);
        //SQLiteDatabase db= this.getWritableDatabase();
        t_txt=findViewById(R.id.t_txt);
        t_edittxt=findViewById(R.id.t_edittxt);
        db = new DatabaseHelper(this);
        final Intent intent = getIntent();
        final String userid = intent.getStringExtra("username");
        t_bt1=findViewById(R.id.t_bt1);
        t_bt2=findViewById(R.id.t_bt2);
        Cursor mcursor = db.getTask(userid);
        if (mcursor.moveToFirst()){
            do{
                String usertask=mcursor.getString(mcursor.getColumnIndex("task"));
                t_txt.setText(usertask);
            }while(mcursor.moveToNext());
        }
        final boolean status = db.isCompleted(userid);
        if(status)
        {
            t_bt1.setText("Submitted");
            t_bt1.setBackgroundColor(getResources().getColor(R.color.GRAY));
            t_bt1.setHeight(70);
            t_bt1.setWidth(600);
        }
        t_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status)
                {
                    t_bt1.setText("Submitted");
                    Toast.makeText(getApplicationContext(),"You have already submitted your task.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    t_bt1.setText("Submit");
                    // || !t_edittxt.getText().toString().trim().substring(0,25).equals("https://drive.google.com/")
                    if(t_edittxt.getText().toString().trim().equals("") || t_edittxt.getText().toString().trim().length() < 20)
                    {
                        Toast.makeText(getApplicationContext(),"Insert a valid link",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //if(status)
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"));
                        intent.putExtra(Intent.EXTRA_EMAIL  , new String[] { "contact@creditsystemsindia.com" });
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Assignment submission");
                        intent.putExtra(Intent.EXTRA_TEXT, t_edittxt.getText().toString().trim());
                        startActivity(Intent.createChooser(intent, "Email via..."));
                        t_bt1.setText("Submitted");
                        t_bt1.setBackgroundColor(getResources().getColor(R.color.GRAY));
                        t_bt1.setHeight(70);
                        t_bt1.setWidth(600);
                        if(db.insertstatus(userid))
                        {
                            Toast.makeText(getApplicationContext(),"successfully submitted",Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });
        t_bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ShowAllData.class);
                startActivity(intent);
            }
        });
    }
}