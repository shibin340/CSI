package com.example.csi;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.csi.ui.CSIfragment;
import com.example.csi.ui.NotificationsFragment;
import com.example.csi.ui.services.ServicesFragment;
import com.example.csi.ui.home.HomeFragment;
import com.example.csi.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {


    Bundle bundle=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        final Intent intent = getIntent();
        final String msg = intent.getStringExtra("EXTRA_MESSAGE");
        bundle.putString("name",msg);
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                //R.id.navigation_home, R.id.navigation_search, R.id.navigation_profile)
               // .build();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //NavigationUI.setupWithNavController(navView, navController);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    //selectedFragment.setArguments(bundle);
                    break;

                case R.id.navigation_services:
                    selectedFragment = new ServicesFragment();
                    selectedFragment.setArguments(bundle);
                    break;

                case R.id.navigation_profile:
                    selectedFragment = new ProfileFragment();
                    selectedFragment.setArguments(bundle);
                    break;

                case R.id.navigation_csi:
                    selectedFragment = new CSIfragment();
                    //selectedFragment.setArguments(bundle);
                    break;

                case R.id.navigation_notifications:
                    selectedFragment = new NotificationsFragment();
                    selectedFragment.setArguments(bundle);
                    break;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, selectedFragment)
                    .commit();
            return true;
        }
    };

}
