package com.example.csi;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginAdmin extends AppCompatActivity {

    EditText email_et,password_et;
    TextView admin_tv1;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        email_et=findViewById(R.id.email_et1);
        password_et=findViewById(R.id.password_et1);
        bt=findViewById(R.id.sign_in_bt1);
        admin_tv1=findViewById(R.id.admin_tv1);
        admin_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserLogin.class));
                //finish();
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(email_et.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                    }
                    else if(password_et.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(email_et.getText().toString().trim().equals("admin@gmail.com") && password_et.getText().toString().trim().equals("admin123"))
                        {
                                    Intent intent=new Intent(getApplicationContext(),Upload.class);
                                    startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Incorrect Credentials.", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });

    }

}
