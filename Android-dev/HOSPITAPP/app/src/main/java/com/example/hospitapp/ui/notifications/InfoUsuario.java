package com.example.hospitapp.ui.notifications;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hospitapp.MainActivity;
import com.example.hospitapp.R;
import com.example.hospitapp.UsuarioHospital;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class InfoUsuario extends AppCompatActivity {

    EditText nameEdited;
    EditText nameHospitalEdited;
    EditText nameStreetEdited;
    EditText streetNumberEdited;
    EditText CPEdited;
    EditText cityEdited;
    EditText emailEdited;
    EditText telephoneEdited;

    TextView nameAdded;
    TextView nameHospitalAdded;
    TextView nameStreetAdded;
    TextView streetNumberAdded;
    TextView CPAdded;
    TextView cityAdded;
    TextView emailAdded;
    TextView telephoneAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);

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

        ArrayList<String> userData = new ArrayList<>();
        userData.add(nameEditedStr);
        userData.add(nameHospitalEditedStr);
        userData.add(nameStreetEditedStr);
        userData.add(streetNumberEditedStr);
        userData.add(CPEditedStr);
        userData.add(cityEditedStr);
        userData.add(emailEditedStr);
        userData.add(telephoneEditedStr);

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View userEntries = layoutInflater.inflate(R.layout.fragment_notifications, null, false);

        nameAdded = findViewById(R.id.nameAdded);
        nameHospitalAdded = userEntries.findViewById(R.id.nameHospitalAdded);
        nameStreetAdded = userEntries.findViewById(R.id.nameStreetAdded);
        streetNumberAdded = userEntries.findViewById(R.id.streetNumberAdded);
        CPAdded = userEntries.findViewById(R.id.CPAdded);
        cityAdded = userEntries.findViewById(R.id.cityAdded);
        emailAdded = userEntries.findViewById(R.id.emailAdded);
        telephoneAdded = userEntries.findViewById(R.id.telephoneAdded);

        Button saveUserBtn = findViewById(R.id.saveUserBtn);
        saveUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateUserInfo = new Intent(InfoUsuario.this, NotificationsFragment.class);
                startActivity(updateUserInfo);

                finish();
            }
        });

    }


}


