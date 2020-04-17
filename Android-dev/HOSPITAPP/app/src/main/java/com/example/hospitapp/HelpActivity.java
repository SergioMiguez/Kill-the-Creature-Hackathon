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
        String hospitappNavBtnIntroText = "There is a circular button at the bottom right of all windows, which can be used to access 2 key functionalities of the app. Once clicked, a set of 3 buttons appear.";
        String hospitappNavBtnAddText = "The first button (with an \"add\" symbol) is used to place an order. You simply need to input the type of material you need, the number of units and click \"Accept\". It's that simple! You also have the option to change the delivery address in this window.";
        String hospitappNavBtnBandText = "The second button (with a \"band aid\" symbol) is used to add material to our database. Certain medical centers might require some specific orders. By pressing this button, a user can add a new material (so that it can be ordered), and check the ones which are already in the database.";
        String hospitappHomeIntroText = "Home provides a simple interface for the user to check what orders have been placed, and have not yet been linked to a provider. By pressing the button \"Providers\", one can access the list of all providers and their IDs. Using your order ID, you can then link it to a certain provider of your choice. After that, the order will be moved to Linked.";
        String hospitappLinkedIntroText ="In Linked, the user can check 2 things: the orders that have been processed (that is, an order that has already been linked), and the orders that have been received. These can be easily differentiated by their colour, for easier understanding.";
        String hospitappProfileIntroText ="In profile, the user can easily check their account information, which is what is used when placing an order. This window also allows the user to change this information.";


        TextView hospitappIntro = findViewById(R.id.hospitappIntro);
        hospitappIntro.setText(hospitappIntroText);

        TextView hospitappIntro2 = findViewById(R.id.hospitappIntro2);
        hospitappIntro2.setText(hospitappIntroText2);

        TextView hospitappNavBtnIntro = findViewById(R.id.hospitappNavBtnIntro);
        hospitappNavBtnIntro.setText(hospitappNavBtnIntroText);

        TextView hospitappNavBtnAdd = findViewById(R.id.hospitappNavBtnAdd);
        hospitappNavBtnAdd.setText(hospitappNavBtnAddText);

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
