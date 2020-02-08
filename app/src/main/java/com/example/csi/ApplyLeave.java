package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ApplyLeave extends AppCompatActivity {
    EditText show_days;
    Button add,subtract,apply_for_leave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave);
        show_days = findViewById(R.id.tv_show_days);
        add = findViewById(R.id.increment_days);
        subtract = findViewById(R.id.decrement_days);
        apply_for_leave = findViewById(R.id.bt_apply_leave);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final String date = day + "-" + month+1 + "-" + year;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num_of_days = show_days.getText().toString().trim();
                int days = Integer.parseInt(num_of_days);
                days = days + 1;
                show_days.setText(""+days);
            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num_of_days = show_days.getText().toString().trim();
                int days = Integer.parseInt(num_of_days);
                days = days - 1;
                show_days.setText(""+days);
            }
        });
        apply_for_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num_of_days = show_days.getText().toString().trim();
                int days = Integer.parseInt(num_of_days);
                if(days<1)
                {
                    Toast.makeText(getApplicationContext(),"Enter valid number of days.",Toast.LENGTH_SHORT).show();
                }
                else {
                    String email = "contact@creditsystemsindia.com";
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Leave request");
                    intent.putExtra(Intent.EXTRA_TEXT, "Leave application for " + days + " days starting from " + date + ".");
                    startActivity(Intent.createChooser(intent, "Email via..."));
                }
            }
        });
    }
}
