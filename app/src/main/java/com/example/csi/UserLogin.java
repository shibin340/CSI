package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends AppCompatActivity {
    EditText ed_email, ed_password;
    DatabaseHelper db;
    TextView admin_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        db = new DatabaseHelper(this);
        ed_email = findViewById(R.id.email_et);
        ed_password = findViewById(R.id.password_et);
        admin_tv = findViewById(R.id.admin_tv);
        admin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginAdmin.class);
                startActivity(intent);
            }
        });
    }

    public void Login(View view) {

        if (ed_email.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        } else if (ed_password.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else {
            boolean check = db.chkmailpass(ed_email.getText().toString(), ed_password.getText().toString());
            boolean check1=db.checkapproval(ed_email.getText().toString());
            if (check && check1) {
                Toast.makeText(this, "Logged in.", Toast.LENGTH_SHORT).show();
                String message = ed_email.getText().toString().trim();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("EXTRA_MESSAGE", message);
                startActivity(intent);
            } else
                Toast.makeText(this, "Incorrect credentials.", Toast.LENGTH_SHORT).show();
        }
    }

    public void moveToSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), SignUp.class));
        //finish();
    }
}
