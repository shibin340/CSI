package com.example.csi.ui.services;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.csi.ApplyLeave;
import com.example.csi.HelpDesk;
import com.example.csi.Laptop;
import com.example.csi.R;
import com.example.csi.TimeSheet;
import com.example.csi.csiDirectory;

public class ServicesFragment extends Fragment {

    private ServicesViewModel dashboardViewModel;
    private Button csi_directorybt,timesheetbt,leavebt,attendancebt,laptopbt,paymentbt,requestbt,claimbt,helpdeskbt;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(ServicesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_services, container, false);
        final Intent intent = getActivity().getIntent();
        final String msg = intent.getStringExtra("EXTRA_MESSAGE");
        csi_directorybt = root.findViewById(R.id.csi_directorybt);
        timesheetbt = root.findViewById(R.id.bttimesheet);
        leavebt = root.findViewById(R.id.btleave);
        attendancebt = root.findViewById(R.id.btattendance);
        laptopbt = root.findViewById(R.id.btlaptop);
        paymentbt = root.findViewById(R.id.btpayment);
        requestbt = root.findViewById(R.id.btrequest);
        claimbt = root.findViewById(R.id.btclaim);
        helpdeskbt = root.findViewById(R.id.bthelpdesk);
        csi_directorybt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), csiDirectory.class);
                startActivity(intent);
            }
        });
        timesheetbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity().getApplicationContext(), TimeSheet.class);
                intent1.putExtra("username",msg);
                startActivity(intent1);
            }
        });
        helpdeskbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HelpDesk.class);
                startActivity(intent);
            }
        });
        leavebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity().getApplicationContext(), ApplyLeave.class);
                intent1.putExtra("username",msg);
                startActivity(intent1);
            }
        });
        claimbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL  , new String[] { "ansarisaifulla7@gmail.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "Claim Request");
                intent.putExtra(Intent.EXTRA_TEXT, "Request of payment claim of RS<<Enter amount here>>.(Also attach the image of your bill)");
                startActivity(Intent.createChooser(intent, "Email via..."));
            }
        });
        requestbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL  , new String[] { "ansarisaifulla7@gmail.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "Document Request");
                intent.putExtra(Intent.EXTRA_TEXT, "<<Specify the documents required>>");
                startActivity(Intent.createChooser(intent, "Email via..."));
            }
        });
        paymentbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL  , new String[] { "ansarisaifulla7@gmail.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "Advance Payment Request");
                intent.putExtra(Intent.EXTRA_TEXT, "Request of advance payment of current month.(Also attach the image of your salary slip)");
                startActivity(Intent.createChooser(intent, "Email via..."));
            }
        });
        laptopbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity().getApplicationContext(), Laptop.class);
                intent1.putExtra("username",msg);
                startActivity(intent1);
            }
        });
        return root;
    }
}