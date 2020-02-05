package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminAssignEmpID extends AppCompatActivity {
    EditText empid_email,emp_id_et;
    Button assign_emp_id;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_emp_id);
        empid_email = findViewById(R.id.emp_email);
        emp_id_et = findViewById(R.id.emp_id_et);
        assign_emp_id = findViewById(R.id.assign_emp_id);
        db = new DatabaseHelper(getApplicationContext());
        assign_emp_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(empid_email.getText().toString().trim().equals(""))
                    Toast.makeText(getApplicationContext(),"Enter email",Toast.LENGTH_SHORT).show();
                else if(emp_id_et.getText().toString().trim().equals(""))
                    Toast.makeText(getApplicationContext(),"Enter employee ID",Toast.LENGTH_SHORT).show();
                else
                {
                    if(!db.checkmail(empid_email.getText().toString().trim()) && db.checkapproval(empid_email.getText().toString().trim()))
                    {
                        boolean update = db.addEmpID(empid_email.getText().toString().trim(),emp_id_et.getText().toString().trim());
                        if(update)
                        {
                            Toast.makeText(getApplicationContext(),"Assigned successfully",Toast.LENGTH_SHORT).show();
                            empid_email.setText(null);
                            emp_id_et.setText(null);
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Assigned unsuccessfully",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Enter a vslid email",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
