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

    TextView edit_profile,user_email_tv;
    ImageView image_profile;
    DatabaseHelper db;
    private ProfileViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        edit_profile=root.findViewById(R.id.edit_profile);
        image_profile=root.findViewById(R.id.image_profile);
        user_email_tv = root.findViewById(R.id.user_email_tv);
        db= new DatabaseHelper(getActivity().getApplicationContext());
        Cursor mcursor;
        final Intent intent = getActivity().getIntent();
        final String msg = intent.getStringExtra("EXTRA_MESSAGE");
        mcursor=db.getimage(msg);
        user_email_tv.setText(msg);
        getprofile(mcursor);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(), upload_profile.class);
                intent.putExtra("email",msg);
                startActivity(intent);
            }
        });
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
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