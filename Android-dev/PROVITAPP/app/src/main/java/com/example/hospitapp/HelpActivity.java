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

        String hospitappIntroText = "Welcome to PROVITAPP, an app aimed at businesses and individuals, who can provide sanitary material for hospitals. The aim is to facilitate the provision and donation of sanitary material, such as face masks, gloves and respirators.";
        String hospitappIntroText2 = "PROVITAPP is organised in 3 simple windows: Home, Linked and Profile. Information is distributed amongst these, including user information, orders that need to be manufactured and the orders that have been delivered. ";
        String hospitappNavBtnIntroText = "There is a circular button at the bottom right of all windows, which can be used to access a key functionality of the app. Once clicked, a set of 3 buttons appear.";
        //String hospitappNavBtnAddText = "The first button (with an \"add\" symbol) is used to place an order. You simply need to input the type of material you need, the number of units and click \"Accept\". It's that simple! You also have the option to change the delivery address in this window.";
        String hospitappNavBtnBandText = "The button with a \"band aid\" symbol is used to add material to our database. Certain medical centers might require some specific orders. By pressing this button, a user can add a new material (so that it can be ordered), and check the ones which are already in the database.";
        String hospitappHomeIntroText = "The Home window contains orders from hospitals looking for a producer. You can click the \"Filter\" button to filter the orders based on material and then select the ID of an order you wish to fulfill. ";
        String hospitappLinkedIntroText ="The Linked window contains orders which a hospital has approved to be manufactured by the producer. You can click the Completed button to change the status of an order from 'Linked' to 'Completed', which means that the materials have been manufactured. You can also click the \"Order Sent\" button to change the status of an order from 'Completed' to 'Sent', which means the materials have been sent to the hospital. ";
        String hospitappProfileIntroText ="In profile, the user can easily check their account information, which is what is used when accepting an order. This window also allows the user to change this information.";


        TextView hospitappIntro = findViewById(R.id.hospitappIntro);
        hospitappIntro.setText(hospitappIntroText);

        TextView hospitappIntro2 = findViewById(R.id.hospitappIntro2);
        hospitappIntro2.setText(hospitappIntroText2);

        TextView hospitappNavBtnIntro = findViewById(R.id.hospitappNavBtnIntro);
        hospitappNavBtnIntro.setText(hospitappNavBtnIntroText);

        //TextView hospitappNavBtnAdd = findViewById(R.id.hospitappNavBtnAdd);
        //hospitappNavBtnAdd.setText(hospitappNavBtnAddText);

        TextView hospitappNavBtnBand = findViewById(R.id.hospitappNavBtnBand);
        hospitappNavBtnBand.setText(hospitappNavBtnBandText);

        TextView hospitappHomeIntro = findViewById(R.id.hospitappHomeIntroduction);
        hospitappHomeIntro.setText(hospitappHomeIntroText);

        TextView hospitappLinkedIntro = findViewById(R.id.hospitappLinkedIntroduction);
        hospitappLinkedIntro.setText(hospitappLinkedIntroText);

        TextView hospitappProfileIntro= findViewById(R.id.hospitappProfileIntroduction);
        hospitappProfileIntro.setText(hospitappProfileIntroText);


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
