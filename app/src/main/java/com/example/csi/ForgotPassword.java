package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {
    EditText user_mail,contact,answer;
    Button reset_password;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        user_mail = findViewById(R.id.forgot_password_email);
        contact = findViewById(R.id.forgot_password_contact);
        answer = findViewById(R.id.forgot_password_answer);
        reset_password = findViewById(R.id.reset_password);
        db = new DatabaseHelper(getApplicationContext());
        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = user_mail.getText().toString().trim();
                if(!db.checkmail(mail) && db.checkapproval(mail)) {
                    Cursor cursor1 = db.getProfileData(mail);
                    cursor1.moveToFirst();
                    String phonenum = cursor1.getString(cursor1.getColumnIndex("Phonenum"));
                    String secure = cursor1.getString(cursor1.getColumnIndex("Security_answer"));
                    if (user_mail.getText().toString().trim().equals(""))
                        Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    else if (contact.getText().toString().trim().equals(""))
                        Toast.makeText(getApplicationContext(), "Please enter contact number", Toast.LENGTH_SHORT).show();
                    else if (answer.getText().toString().trim().equals(""))
                        Toast.makeText(getApplicationContext(), "Please enter your answer", Toast.LENGTH_SHORT).show();
                    else if (!contact.getText().toString().trim().equals(phonenum) || !answer.getText().toString().trim().equals(secure))
                        Toast.makeText(getApplicationContext(), "Incorrect data", Toast.LENGTH_SHORT).show();
                    else {
                        if (!db.checkmail(user_mail.getText().toString().trim()) && db.checkapproval(user_mail.getText().toString().trim())) {
                            Intent intent1 = new Intent(getApplicationContext(), PasswordResetVerification.class);
                            intent1.putExtra("email", user_mail.getText().toString().trim());
                            startActivity(intent1);
                        } else {
                            Toast.makeText(getApplicationContext(), "Enter a valid email", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "Enter a valid email", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
