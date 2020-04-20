package com.example.hospitapp.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hospitapp.R;

/** Class that is responsible for the handling of the user info*/
public class InfoUsuario extends AppCompatActivity {

    /** EditText parameter that is responsible for the name input*/
    EditText nameEdited;
    /** EditText parameter that is responsible for the hospital name input*/
    EditText nameHospitalEdited;
    /** EditText parameter that is responsible for the street name input*/
    EditText nameStreetEdited;
    /** EditText parameter that is responsible for the street number input*/
    EditText streetNumberEdited;
    /** EditText parameter that is responsible for the postal code input*/
    EditText CPEdited;
    /** EditText parameter that is responsible for the city input*/
    EditText cityEdited;
    /** EditText parameter that is responsible for the email input*/
    EditText emailEdited;
    /** EditText parameter that is responsible for the telephone input*/
    EditText telephoneEdited;

    /** TextView parameter that represents the name text*/
    TextView nameAdded;
    /** TextView parameter that represents the hospital name text*/
    TextView nameHospitalAdded;
    /** TextView parameter that represents the street name text*/
    TextView nameStreetAdded;
    /** TextView parameter that represents the street number text*/
    TextView streetNumberAdded;
    /** TextView parameter that represents the postal code text*/
    TextView CPAdded;
    /** TextView parameter that represents the city text*/
    TextView cityAdded;
    /** TextView parameter that represents the email text*/
    TextView emailAdded;
    /** TextView parameter that represents the telephone text*/
    TextView telephoneAdded;

    /** Object of the class SharedPreferences*/
    SharedPreferences myPrefs;

    String def = "nada";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View userEntries = layoutInflater.inflate(R.layout.fragment_notifications, null, false);

        nameAdded = userEntries.findViewById(R.id.nameAdded);
        nameHospitalAdded = userEntries.findViewById(R.id.nameHospitalAdded);
        nameStreetAdded = userEntries.findViewById(R.id.nameStreetAdded);
        streetNumberAdded = userEntries.findViewById(R.id.streetNumberAdded);
        CPAdded = userEntries.findViewById(R.id.CPAdded);
        cityAdded = userEntries.findViewById(R.id.cityAdded);
        emailAdded = userEntries.findViewById(R.id.emailAdded);
        telephoneAdded = userEntries.findViewById(R.id.telephoneAdded);

        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);

        String name = myPrefs.getString("name", def);
        String hospital = myPrefs.getString("hospital", def);
        String street = myPrefs.getString("street", def);
        String street_number = myPrefs.getString("street_number", def);
        String CP = myPrefs.getString("CP", def);
        String city = myPrefs.getString("city", def);
        String email = myPrefs.getString("email", def);
        String telephone = myPrefs.getString("telephone", def);

        nameAdded.setText(name);
        nameHospitalAdded.setText(hospital);
        nameStreetAdded.setText(street);
        streetNumberAdded.setText(street_number);
        CPAdded.setText(CP);
        cityAdded.setText(city);
        emailAdded.setText(email);
        telephoneAdded.setText(telephone);

        Button saveUserBtn = findViewById(R.id.saveUserBtn);
        saveUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameEdited = findViewById(R.id.nameEdited);
                nameHospitalEdited = findViewById(R.id.nameHospitalEdited);
                nameStreetEdited = findViewById(R.id.nameStreetEdited);
                streetNumberEdited = findViewById(R.id.streetNumberEdited);
                CPEdited = findViewById(R.id.CPEdited);
                cityEdited = findViewById(R.id.cityEdited);
                emailEdited = findViewById(R.id.emailEdited);
                telephoneEdited = findViewById(R.id.telephoneEdited);

                String nameEditedStr = nameEdited.getText().toString();
                String nameHospitalEditedStr = nameHospitalEdited.getText().toString();
                String nameStreetEditedStr = nameStreetEdited.getText().toString();
                String streetNumberEditedStr = streetNumberEdited.getText().toString();
                String CPEditedStr = CPEdited.getText().toString();
                String cityEditedStr = cityEdited.getText().toString();
                String emailEditedStr = emailEdited.getText().toString();
                String telephoneEditedStr = telephoneEdited.getText().toString();

                myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();

                editor.putString("name", nameEditedStr);
                editor.putString("hospital", nameHospitalEditedStr);
                editor.putString("street", nameStreetEditedStr);
                editor.putString("street_number", streetNumberEditedStr);
                editor.putString("CP", CPEditedStr);
                editor.putString("city", cityEditedStr);
                editor.putString("email", emailEditedStr);
                editor.putString("telephone", telephoneEditedStr);
                editor.apply();

                String name = myPrefs.getString("name", def);
                String hospital = myPrefs.getString("hospital", def);
                String street = myPrefs.getString("street", def);
                String street_number = myPrefs.getString("street_number", def);
                String CP = myPrefs.getString("CP", def);
                String city = myPrefs.getString("city", def);
                String email = myPrefs.getString("email", def);
                String telephone = myPrefs.getString("telephone", def);

                nameAdded.setText(name);
                nameHospitalAdded.setText(hospital);
                nameStreetAdded.setText(street);
                streetNumberAdded.setText(street_number);
                CPAdded.setText(CP);
                cityAdded.setText(city);
                emailAdded.setText(email);
                telephoneAdded.setText(telephone);

                Intent updateUserInfo = new Intent(InfoUsuario.this, NotificationsFragment.class);
                startActivity(updateUserInfo);

                finish();
            }
        });

    }


}


