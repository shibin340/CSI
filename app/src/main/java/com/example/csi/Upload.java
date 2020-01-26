package com.example.csi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Upload extends AppCompatActivity {

    Button eve_bt,task_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        eve_bt=findViewById(R.id.event_add_bt);
        task_bt=findViewById(R.id.task_add_bt);
        eve_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminAddEvent.class));
            }
        });
        task_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminAssignTask.class));
            }
        });
    }
}
