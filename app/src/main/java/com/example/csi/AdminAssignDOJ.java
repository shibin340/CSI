package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class AdminAssignDOJ extends AppCompatActivity {
    EditText doj_email;
    TextView doj_date;
    Button assign_doj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_doj);
        doj_email = findViewById(R.id.doj_email);
        doj_date = findViewById(R.id.doj_date);
        assign_doj = findViewById(R.id.assign_doj_bt);
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
                        String date = day + "/" + month + "/" + year;
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

            }
        });
    }
}
