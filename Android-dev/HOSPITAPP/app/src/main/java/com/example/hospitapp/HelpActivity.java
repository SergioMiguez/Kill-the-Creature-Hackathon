package com.example.hospitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        String hospitappIntroText = "Welcome to Hospitapp, an app developed to connect businesses and individuals with medical centers. The aim is to facilitate the provision and donation of sanitary material, such as face masks, gloves and respirators.";
        String hospitappIntroText2 = "Hospitapp is organised in 3 simple windows: Home, Linked and Profile. Information is distributed amongst these, including user information, orders that have been made and the orders that have been received.";

        TextView hospitappIntro = findViewById(R.id.hospitappIntro);
        hospitappIntro.setText(hospitappIntroText);

        TextView hospitappIntro2 = findViewById(R.id.hospitappIntro2);
        hospitappIntro2.setText(hospitappIntroText2);

        Button backMain = findViewById(R.id.backMain);
        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMain = new Intent(v.getContext(), MainActivity.class);
                startActivity(goMain);
            }
        });

    }
}
