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

public class AdminAssignTask extends AppCompatActivity {
    EditText task_email,task_desc;
    TextView start_date,end_date;
    Button task_bt;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_task);
        task_email=findViewById(R.id.tsk_email);
        task_desc=findViewById(R.id.tsk_detail);
        task_bt=findViewById(R.id.task_bt);
        start_date=findViewById(R.id.task_start_date);
        end_date=findViewById(R.id.task_end_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AdminAssignTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        start_date.setText(date);
                        start_date.setTextColor(getResources().getColor(R.color.BLACK));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AdminAssignTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        end_date.setText(date);
                        end_date.setTextColor(getResources().getColor(R.color.BLACK));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        task_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(task_email.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_SHORT).show();
                }
                else if(task_desc.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Description", Toast.LENGTH_SHORT).show();
                }
                else if(start_date.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Start date of task", Toast.LENGTH_SHORT).show();
                }
                else if(end_date.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter End date of task", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DatabaseHelper ed;
                    ed=new DatabaseHelper(getApplicationContext());
                    if(!ed.checkmail(task_email.getText().toString().trim())) {
                        if (ed.assignTask(task_email.getText().toString().trim(), task_desc.getText().toString().trim(),end_date.getText().toString().trim())) {
                            Toast.makeText(getApplicationContext(), "Assigned successfully ", Toast.LENGTH_SHORT).show();
                            task_email.setText(null);
                            task_desc.setText(null);
                            start_date.setText("start-date");
                            start_date.setTextColor(getResources().getColor(R.color.hintColor));
                            end_date.setText("end-date");
                            end_date.setTextColor(getResources().getColor(R.color.hintColor));
                        } else {
                            Toast.makeText(getApplicationContext(), "Insertion failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Enter a registered email!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
