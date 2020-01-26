package com.example.csi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class SignUp extends AppCompatActivity {
    DatabaseHelper db;
    EditText ed_firstname,ed_lastname,ed_email,ed_password,ed_confirm,phone_no;
    Button button1;
    TextView ed_dob,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = new DatabaseHelper(this);
        ed_email = findViewById(R.id.et_email);
        ed_firstname = findViewById(R.id.et_firstname);
        ed_lastname = findViewById(R.id.et_lastname);
        ed_password = findViewById(R.id.et_password);
        ed_confirm = findViewById(R.id.et_confirm);
        phone_no = findViewById(R.id.et_contact_no);
        ed_dob=findViewById(R.id.et_dob);
        button1 = findViewById(R.id.sign_up_bt);
        button2 = findViewById(R.id.sign_in_tv);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        ed_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        ed_dob.setText(date);
                        ed_dob.setTextColor(getResources().getColor(R.color.BLACK));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_firstname.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter First name", Toast.LENGTH_SHORT).show();
                }
                else if(ed_lastname.getText().toString().trim().equals("") ){
                    Toast.makeText(getApplicationContext(), "Enter Last name", Toast.LENGTH_SHORT).show();
                }
                else if(ed_email.getText().toString().trim().equals("") ){
                    Toast.makeText(getApplicationContext(), "Enter valid mail", Toast.LENGTH_SHORT).show();
                }
                else if(ed_password.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(ed_dob.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Incorrect Dob",Toast.LENGTH_SHORT).show();
                }
                else if(ed_confirm.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Confirm Password", Toast.LENGTH_SHORT).show();
                }
                else if(!ed_password.getText().toString().trim().equals(ed_confirm.getText().toString().trim()))
                {
                    Toast.makeText(getApplicationContext(),"Password is not matching",Toast.LENGTH_SHORT).show();
                }
                else if(phone_no.getText().toString().trim().equals("") || phone_no.getText().toString().length() != 10)
                {
                    Toast.makeText(getApplicationContext(),"Enter valid contact number",Toast.LENGTH_SHORT).show();
                }
                else if(!ed_email.getText().toString().trim().isEmpty()){
                    boolean check = db.checkmail(ed_email.getText().toString());
                    if(check==true)
                    {

                        Resources res = getResources();
                        String tasklist[]= res.getStringArray(R.array.tasks);
                        int size=tasklist.length-1;
                        int index=(int)(Math.random() * size) +1;
                        boolean insert = db.insert((ed_firstname.getText().toString().trim()+" "+ed_lastname.getText().toString().trim()),ed_email.getText().toString(),ed_password.getText().toString(),ed_dob.getText().toString(),tasklist[index]);
                        if(insert==true) {
                            Toast.makeText(getApplicationContext(), "Registered successfully!", Toast.LENGTH_SHORT).show();
                            ed_confirm.setText(null);
                            ed_dob.setText(null);
                            ed_dob.setText("Date");
                            ed_dob.setTextColor(getResources().getColor(R.color.hintColor));
                            ed_email.setText(null);
                            ed_password.setText(null);
                            ed_firstname.setText(null);
                            ed_lastname.setText(null);
                            phone_no.setText(null);
                            startActivity(new Intent(getApplicationContext(),UserLogin.class));
                            //finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Registration failed!",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Email already registered.",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Registration Click", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserLogin.class));
                //finish();
            }
        });
    }
    /*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/
}
