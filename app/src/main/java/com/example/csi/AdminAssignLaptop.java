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

public class AdminAssignLaptop extends AppCompatActivity {
    EditText email_laptop,laptop_id;
    TextView issue_date;
    Button assign_laptop;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_laptop);
        email_laptop=findViewById(R.id.laptop_email);
        laptop_id=findViewById(R.id.laptop_id_et);
        issue_date=findViewById(R.id.laptop_issue_date);
        assign_laptop=findViewById(R.id.assign_laptop);
        db=new DatabaseHelper(getApplicationContext());
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        issue_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AdminAssignLaptop.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        issue_date.setText(date);
                        issue_date.setTextColor(getResources().getColor(R.color.BLACK));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        assign_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringTokenizer s_arr = new StringTokenizer(issue_date.getText().toString().trim(),"/",false);
                if(email_laptop.getText().toString().trim().equals(""))
                    Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_SHORT).show();
                else if(s_arr.countTokens()!=3)
                    Toast.makeText(getApplicationContext(),"Please enter a DOJ",Toast.LENGTH_SHORT).show();
                else if(laptop_id.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter a Laptop ID",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!db.checkmail(email_laptop.getText().toString().trim()) && db.checkapproval(email_laptop.getText().toString().trim()))
                    {
                        boolean update = db.addLaptop(email_laptop.getText().toString().trim(),laptop_id.getText().toString().trim(),issue_date.getText().toString().trim());
                        if(update)
                        {
                            Toast.makeText(getApplicationContext(),"Assigned successfully",Toast.LENGTH_SHORT).show();
                            email_laptop.setText(null);
                            issue_date.setText("Issue date");
                            issue_date.setTextColor(getResources().getColor(R.color.hintColor));
                            laptop_id.setText(null);
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
