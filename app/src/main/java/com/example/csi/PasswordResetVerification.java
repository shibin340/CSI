package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordResetVerification extends AppCompatActivity {
    EditText new_pass,confirm_pass;
    Button reset;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset_verification);
        final Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        new_pass = findViewById(R.id.et_new_password);
        confirm_pass = findViewById(R.id.et_confirm_new_password);
        reset = findViewById(R.id.reset_confirm);
        db = new DatabaseHelper(getApplicationContext());
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(new_pass.getText().toString().trim().equals("") || confirm_pass.getText().toString().trim().equals(""))
                    Toast.makeText(getApplicationContext(),"Enter your password",Toast.LENGTH_SHORT).show();
                else if(!new_pass.getText().toString().trim().equals(confirm_pass.getText().toString().trim()))
                    Toast.makeText(getApplicationContext(),"Please enter matching password",Toast.LENGTH_SHORT).show();
                else{
                    boolean reset_pass = db.resetPassword(email,new_pass.getText().toString().trim());
                    if(reset_pass) {
                        Toast.makeText(getApplicationContext(), "Reset successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),UserLogin.class));
                        finish();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Password reset failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
