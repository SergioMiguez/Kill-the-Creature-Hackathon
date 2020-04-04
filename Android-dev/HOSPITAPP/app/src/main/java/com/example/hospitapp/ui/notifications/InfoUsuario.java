package com.example.hospitapp.ui.notifications;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hospitapp.R;

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

                Intent updateUserInfo = new Intent(InfoUsuario.this, NotificationsFragment.class);
                updateUserInfo.putExtra("key.name.edited", nameEditedStr);
                updateUserInfo.putExtra("key.name.hospital.edited", nameHospitalEditedStr);
                updateUserInfo.putExtra("key.name.street.edited", nameStreetEditedStr);
                updateUserInfo.putExtra("key.street.number.edited", streetNumberEditedStr);
                updateUserInfo.putExtra("key.CP.edited", CPEditedStr);
                updateUserInfo.putExtra("key.city.edited", cityEditedStr);
                updateUserInfo.putExtra("key.email.edited", emailEditedStr);
                updateUserInfo.putExtra("key.telephone.edited", streetNumberEditedStr);
                startActivity(updateUserInfo);

            }
        });

    }
}
