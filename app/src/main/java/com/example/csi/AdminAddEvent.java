package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AdminAddEvent extends AppCompatActivity {
    EditText eve_title,eve_desc;
    TextView eve_date;
    Button eve_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_event);
        eve_bt=findViewById(R.id.eve_bt);
        eve_date=findViewById(R.id.eve_date);
        eve_title=findViewById(R.id.eve_title);
        eve_desc=findViewById(R.id.eve_desc);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        eve_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(AdminAddEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        eve_date.setText(date);
                        eve_date.setTextColor(getResources().getColor(R.color.BLACK));
                    }
                }, year, month, day);
                datePickerDialog1.show();
            }
        });
        eve_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eve_title.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Title", Toast.LENGTH_SHORT).show();
                }
                else if(eve_desc.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Description", Toast.LENGTH_SHORT).show();
                }
                else if(eve_date.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter a Date",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    EventDatabaseHelper ed;
                    ed=new EventDatabaseHelper(getApplicationContext());
                    if(ed.chkevent(eve_title.getText().toString().trim())) {
                        if (ed.addEvents(eve_title.getText().toString().trim(), eve_desc.getText().toString().trim(), eve_date.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Inserted successfully ", Toast.LENGTH_SHORT).show();
                            eve_title.setText(null);
                            eve_date.setText("Date");
                            eve_date.setTextColor(getResources().getColor(R.color.hintColor));
                            eve_desc.setText(null);
                        } else {
                            Toast.makeText(getApplicationContext(), "Insertion failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Event already uploaded!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
