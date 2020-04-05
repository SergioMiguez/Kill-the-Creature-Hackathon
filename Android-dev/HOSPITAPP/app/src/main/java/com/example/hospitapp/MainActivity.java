package com.example.hospitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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

        TextView nameAdded = findViewById(R.id.nameAdded);
        TextView nameHospitalAdded = findViewById(R.id.nameHospitalAdded);
        TextView nameStreetAdded = findViewById(R.id.nameStreetAdded);
        TextView streetNumberAdded = findViewById(R.id.streetNumberAdded);
        TextView CPAdded = findViewById(R.id.CPAdded);
        TextView cityAdded = findViewById(R.id.cityAdded);
        TextView emailAdded = findViewById(R.id.emailAdded);
        TextView telephoneAdded = findViewById(R.id.telephoneAdded);

        if (getIntent().hasExtra("key.name.edited")) {
            String nameAddedStr = getIntent().getStringExtra("key.name.edited");
            nameAdded.setText(nameAddedStr);
        }
        else if (getIntent().hasExtra("key.name.hospital.edited")) {
            String nameHospitalAddedStr = getIntent().getStringExtra("key.name.hospital.edited");
            nameHospitalAdded.setText(nameHospitalAddedStr);
        }
        else if (getIntent().hasExtra("key.name.street.edited")) {
            String nameStreetAddedStr = getIntent().getStringExtra("key.name.street.edited");
            nameStreetAdded.setText(nameStreetAddedStr);
        }
        else if (getIntent().hasExtra("key.street.number.edited")) {
            String streetNumberStr = getIntent().getStringExtra("key.street.number.edited");
            streetNumberAdded.setText(streetNumberStr);
        }
        else if (getIntent().hasExtra("key.CP.edited")) {
            String CPAddedStr = getIntent().getStringExtra("key.CP.edited");
            CPAdded.setText(CPAddedStr);
        }
        else if (getIntent().hasExtra("key.city.edited")) {
            String cityAddedStr = getIntent().getStringExtra("key.city.edited");
            cityAdded.setText(cityAddedStr);
        }
        else if (getIntent().hasExtra("key.email.edited")) {
            String emailAddedStr = getIntent().getStringExtra("key.email.edited");
            emailAdded.setText(emailAddedStr);
        }
        else if (getIntent().hasExtra("key.telephone.edited")) {
            String telephoneAddedStr = getIntent().getStringExtra("key.telephone.edited");
            telephoneAdded.setText(telephoneAddedStr);
        }
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


}
