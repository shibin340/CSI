package com.example.csi.ui.profile;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.csi.DatabaseHelper;
import com.example.csi.R;
import com.example.csi.upload_profile;

import java.io.ByteArrayInputStream;

public class ProfileFragment extends Fragment {

    TextView edit_profile,user_name,user_email_tv,user_number_tv,user_dob_tv,user_address_tv,user_nation_tv,user_uid_tv,user_pan_tv,user_language_tv,user_education_tv,user_doj_tv;
    ImageView image_profile;
    DatabaseHelper db;
    private ProfileViewModel notificationsViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final Intent intent = getActivity().getIntent();
        final String msg = intent.getStringExtra("EXTRA_MESSAGE");
        edit_profile=root.findViewById(R.id.edit_profile);
        image_profile=root.findViewById(R.id.image_profile);
        user_name=root.findViewById(R.id.user_name_tv);
        user_email_tv = root.findViewById(R.id.user_email_tv);
        user_number_tv = root.findViewById(R.id.user_number_tv);
        user_address_tv = root.findViewById(R.id.user_address);
        user_nation_tv = root.findViewById(R.id.user_nationality);
        user_uid_tv = root.findViewById(R.id.user_uid);
        user_pan_tv = root.findViewById(R.id.user_pan);
        user_language_tv = root.findViewById(R.id.user_language);
        user_doj_tv = root.findViewById(R.id.user_doj);
        user_dob_tv = root.findViewById(R.id.user_dob);
        user_education_tv = root.findViewById(R.id.user_education);
        db= new DatabaseHelper(getActivity().getApplicationContext());
        Cursor mcursor,cursor;
        cursor = db.getProfileData(msg);

        cursor.moveToFirst();
        String phno = cursor.getString(cursor.getColumnIndex("Phonenum"));
        user_number_tv.setText(phno);
        String address= cursor.getString(cursor.getColumnIndex("Address"));
        String nationality = cursor.getString(cursor.getColumnIndex("Nationality"));
        String aadhar = cursor.getString(cursor.getColumnIndex("UID"));
        String pancard = cursor.getString(cursor.getColumnIndex("PAN"));
        String dob = cursor.getString(cursor.getColumnIndex("dob"));
        user_dob_tv.setText(dob);
        String language = cursor.getString(cursor.getColumnIndex("Language"));
        String education = cursor.getString(cursor.getColumnIndex("Education"));
        String doj = cursor.getString(cursor.getColumnIndex("DOJ"));
        String username = cursor.getString(cursor.getColumnIndex("username"));
        user_name.setText(username);
        if(address != null)
            user_address_tv.setText(address);
        if(nationality!=null)
            user_nation_tv.setText(nationality);
        if(aadhar!=null)
            user_uid_tv.setText(aadhar);
        if(pancard!=null)
            user_pan_tv.setText(pancard);
        if(language!=null)
            user_language_tv.setText(language);
        if(education!=null)
            user_education_tv.setText(education);
        if(doj!=null)
            user_doj_tv.setText(doj);
        user_email_tv.setText(msg);
        try{
        mcursor=db.getimage(msg);
        getprofile(mcursor);
        }
        catch (Exception e){
            Toast.makeText(getActivity(),"Size too big",Toast.LENGTH_SHORT).show();
        }
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(), upload_profile.class);
                intent.putExtra("email",msg);
                intent.putExtra("number",user_number_tv.getText().toString().trim());
                intent.putExtra("dob",user_dob_tv.getText().toString().trim());
                intent.putExtra("address",user_address_tv.getText().toString().trim());
                intent.putExtra("nation",user_nation_tv.getText().toString().trim());
                intent.putExtra("uid",user_uid_tv.getText().toString().trim());
                intent.putExtra("pan",user_pan_tv.getText().toString().trim());
                intent.putExtra("language",user_language_tv.getText().toString().trim());
                intent.putExtra("education",user_education_tv.getText().toString().trim());
                intent.putExtra("doj",user_doj_tv.getText().toString().trim());
                startActivity(intent);
            }
        });
        return root;
    }

    private void getprofile(Cursor mcursor) {
        if((mcursor != null) && (mcursor.getCount() > 0)) {
            if (mcursor.moveToFirst()) {
                do {
                    if (mcursor.getBlob(mcursor.getColumnIndex("image")) != null) {
                        byte[] imagebyte = mcursor.getBlob(mcursor.getColumnIndex("image"));
                         ByteArrayInputStream inputStream = new ByteArrayInputStream(imagebyte);
                         Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                         image_profile.setImageBitmap(bitmap);
                    }
                    else {
                        image_profile.setBackgroundResource(R.drawable.ic_person_black_24dp);
                    }
                } while (mcursor.moveToNext());
            }
        }
        else
        {
            image_profile.setBackgroundResource(R.drawable.ic_person_black_24dp);
        }
    }
}