package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelpDesk extends AppCompatActivity {
    TextView csi_number,hardware_number,network_number,mail_id;
    String csinum,hardnum,netnum,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk);
        csi_number = findViewById(R.id.csi_contact_no);
        hardware_number = findViewById(R.id.hardware_contact_no);
        network_number = findViewById(R.id.network_contact_no);
        mail_id = findViewById(R.id.mail_contact_id);
        csinum = csi_number.getText().toString();
        hardnum = hardware_number.getText().toString();
        netnum = network_number.getText().toString();
        email = mail_id.getText().toString();
        csi_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", csinum, null)));
            }
        });
        hardware_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", hardnum, null)));
            }
        });
        network_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", netnum, null)));
            }
        });
        mail_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL  , new String[] { email });
                intent.putExtra(Intent.EXTRA_SUBJECT, "HelpDesk Query: <<Enter your query here>>");
                startActivity(Intent.createChooser(intent, "Email via..."));
            }
        });
    }
}
