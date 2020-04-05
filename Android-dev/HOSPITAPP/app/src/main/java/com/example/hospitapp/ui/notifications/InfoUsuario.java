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

import com.example.hospitapp.MainActivity;
import com.example.hospitapp.R;
import com.example.hospitapp.UsuarioHospital;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class InfoUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);

        Button saveUserBtn = findViewById(R.id.saveUserBtn);
        saveUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameEdited = findViewById(R.id.nameEdited);
                EditText nameHospitalEdited = findViewById(R.id.nameHospitalEdited);
                EditText nameStreetEdited = findViewById(R.id.nameStreetEdited);
                EditText streetNumberEdited = findViewById(R.id.streetNumberEdited);
                EditText CPEdited = findViewById(R.id.CPEdited);
                EditText cityEdited = findViewById(R.id.cityEdited);
                EditText emailEdited = findViewById(R.id.emailEdited);
                EditText telephoneEdited = findViewById(R.id.telephoneEdited);

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


                Intent updateUserInfo = new Intent(InfoUsuario.this, MainActivity.class);
                updateUserInfo.putExtra("key.hospital.user.edited.data", userData);
                startActivity(updateUserInfo);

                finish();
            }
        });

    }

}


