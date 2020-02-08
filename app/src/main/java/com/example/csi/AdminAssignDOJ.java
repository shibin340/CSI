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
import java.util.StringTokenizer;

public class AdminAssignDOJ extends AppCompatActivity {
    EditText doj_email;
    TextView doj_date;
    Button assign_doj;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_doj);
        doj_email = findViewById(R.id.doj_email);
        doj_date = findViewById(R.id.doj_date);
        assign_doj = findViewById(R.id.assign_doj_bt);
        db = new DatabaseHelper(getApplicationContext());
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        doj_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AdminAssignDOJ.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        doj_date.setText(date);
                        doj_date.setTextColor(getResources().getColor(R.color.BLACK));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        assign_doj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringTokenizer s_arr = new StringTokenizer(doj_date.getText().toString().trim(),"/",false);
                if(doj_email.getText().toString().trim().equals(""))
                    Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_SHORT).show();
                else if(s_arr.countTokens()!=3)
                    Toast.makeText(getApplicationContext(),"Enter a DOJ",Toast.LENGTH_SHORT).show();
                else
                {
                    if(!db.checkmail(doj_email.getText().toString().trim()) && db.checkapproval(doj_email.getText().toString().trim()))
                    {
                        boolean update = db.addDateOfJoining(doj_email.getText().toString().trim(),doj_date.getText().toString().trim());
                        if(update)
                        {
                            Toast.makeText(getApplicationContext(),"Assigned successfully",Toast.LENGTH_SHORT).show();
                            doj_email.setText(null);
                            doj_date.setText("Date");
                            doj_date.setTextColor(getResources().getColor(R.color.hintColor));
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Assigned unsuccessfully",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Enter a valid email",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
