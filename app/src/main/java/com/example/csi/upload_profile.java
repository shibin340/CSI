package com.example.csi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

public class upload_profile extends AppCompatActivity {

    Button bt_choose,bt_upload,save_data;
    ImageView imag_upload;
    private static final int pick_image_request=100;
    Uri imgpath;
    Bitmap imagetostore;
    DatabaseHelper db;
    EditText edit_email,edit_number,edit_address,edit_nation,edit_uid,edit_pan,edit_language,edit_education;
    TextView edit_dob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile);
        bt_choose=findViewById(R.id.bt_choose);
        bt_upload=findViewById(R.id.bt_upload);
        imag_upload=findViewById(R.id.img_upload);
        db=new DatabaseHelper(this);
        edit_email = findViewById(R.id.edit_email);
        edit_number = findViewById(R.id.edit_phone);
        edit_dob = findViewById(R.id.edit_dob);
        edit_address = findViewById(R.id.edit_address);
        edit_nation = findViewById(R.id.edit_nationality);
        edit_uid = findViewById(R.id.edit_uid);
        edit_pan = findViewById(R.id.edit_pan);
        edit_language = findViewById(R.id.edit_language);
        edit_education = findViewById(R.id.edit_education);
        save_data = findViewById(R.id.save_edit_bt);
        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        final String number = intent.getStringExtra("number");
        final String dob = intent.getStringExtra("dob");
        final String address = intent.getStringExtra("address");
        final String nationality = intent.getStringExtra("nation");
        final String uid = intent.getStringExtra("uid");
        final String pan = intent.getStringExtra("pan");
        final String language = intent.getStringExtra("language");
        final String education = intent.getStringExtra("education");
        edit_email.setText(email);
        edit_number.setText(number);
        edit_dob.setText(dob);
        edit_address.setText(address);
        edit_nation.setText(nationality);
        edit_uid.setText(uid);
        edit_pan.setText(pan);
        edit_language.setText(language);
        edit_education.setText(education);
        bt_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,pick_image_request);
            }
        });
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        edit_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(upload_profile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        edit_dob.setText(date);
                        edit_dob.setTextColor(getResources().getColor(R.color.BLACK));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        bt_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imag_upload.getDrawable()!=null && !email.isEmpty()) {
                    if(db.storeimage(imagetostore,email))
                        Toast.makeText(getApplicationContext(),"inserted successfully ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"please select image ",Toast.LENGTH_SHORT).show();

                }
            }
        });
        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ins = db.update_profile(email,edit_email.getText().toString().trim(),edit_number.getText().toString().trim(),edit_dob.getText().toString().trim(),edit_address.getText().toString().trim(),edit_nation.getText().toString().trim(),edit_uid.getText().toString().trim(),edit_pan.getText().toString().trim(),edit_language.getText().toString().trim(),edit_education.getText().toString().trim());
                if(ins)
                    Toast.makeText(getApplicationContext(),"Profile updated successfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Profile update failed, please try again later :(",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode==pick_image_request && resultCode==RESULT_OK &&  data!=null && data.getData()!=null) {
                imgpath = data.getData();
                imagetostore = MediaStore.Images.Media.getBitmap(getContentResolver(), imgpath);
                imag_upload.setImageBitmap(imagetostore);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
