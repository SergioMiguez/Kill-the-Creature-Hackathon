package com.example.hospitapp;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hospitapp.ui.home.HomeFragment;
import com.example.hospitapp.ui.notifications.NotificationsFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        openAdd();
        updateUserProfile();
    }

    public void openAdd() {

        fabAdd = findViewById(R.id.addFabButton);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogAdd();
            }
        });
    }

    public void onDialogAdd() {
        AddDialog addDialog = new AddDialog();
        addDialog.show(getSupportFragmentManager(), "Add Dialog");
    }

    public void updateUserProfile() {

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View userEntries = layoutInflater.inflate(R.layout.fragment_notifications, null, false);

        TextView nameAdded = userEntries.findViewById(R.id.nameAdded);
        TextView nameHospitalAdded = userEntries.findViewById(R.id.nameHospitalAdded);
        TextView nameStreetAdded = userEntries.findViewById(R.id.nameStreetAdded);
        TextView streetNumberAdded = userEntries.findViewById(R.id.streetNumberAdded);
        TextView CPAdded = userEntries.findViewById(R.id.CPAdded);
        TextView cityAdded = userEntries.findViewById(R.id.cityAdded);
        TextView emailAdded = userEntries.findViewById(R.id.emailAdded);
        TextView telephoneAdded = userEntries.findViewById(R.id.telephoneAdded);

        boolean doesItHaveExtra = getIntent().hasExtra("key.hospital.user.edited.data");

        Intent i = getIntent();


        if (i.hasExtra("key.hospital.user.edited.data")) {

            ArrayList<String> userInfoList = i.getStringArrayListExtra("key.hospital.user.edited.data");

            NotificationsFragment fragment = (NotificationsFragment) getSupportFragmentManager().findFragmentById(R.id.notification_fragment_id);

            fragment.updateTextView(nameAdded, userInfoList, 0);
            nameAdded.invalidate();

            //nameAdded.setText(userInfoList.get(0));
            nameHospitalAdded.setText(userInfoList.get(1));
            nameStreetAdded.setText(userInfoList.get(2));
            streetNumberAdded.setText(userInfoList.get(3));
            CPAdded.setText(userInfoList.get(4));
            cityAdded.setText(userInfoList.get(5));
            emailAdded.setText(userInfoList.get(6));
            telephoneAdded.setText(userInfoList.get(7));
        }

    }







}
