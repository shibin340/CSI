package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminAssignTask extends AppCompatActivity {
    EditText task_email,task_desc;
    Button task_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_task);
        task_email=findViewById(R.id.tsk_email);
        task_desc=findViewById(R.id.tsk_detail);
        task_bt=findViewById(R.id.task_bt);
        task_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(task_email.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_SHORT).show();
                }
                else if(task_desc.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Description", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DatabaseHelper ed;
                    ed=new DatabaseHelper(getApplicationContext());
                    if(!ed.checkmail(task_email.getText().toString().trim())) {
                        if (ed.assignTask(task_email.getText().toString().trim(), task_desc.getText().toString().trim())) {
                            Toast.makeText(getApplicationContext(), "Assigned successfully ", Toast.LENGTH_SHORT).show();
                            task_email.setText(null);
                            task_desc.setText(null);
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
