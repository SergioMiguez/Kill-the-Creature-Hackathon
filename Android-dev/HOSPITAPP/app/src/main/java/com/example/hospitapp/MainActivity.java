package com.example.hospitapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

/**
 *  MainActivity class contains the activity which initializes the three main fragments that are
 *  displayed in the app (HomeFragment, DashboardFragment and NotificationsFragment). It also contains
 *  the elements which are common to all the app (FabButtons)
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Floating button which is in charge of showing the rest of the three floating buttons
     */
    private FloatingActionButton fabThree;
    /**
     * Floating button which is in charge of opening a PopUp where orders can be made
     */
    private FloatingActionButton fabAdd;
    /**
     * Floating button which is in charge of opening a PopUp where new materials can be added
     */
    private FloatingActionButton fabMaterial;
    /**
     * Floating button which is in charge of opening a PopUp where all the help material is contained
     */
    private FloatingActionButton fabHelp;
    /**
     * Variable which storage the frame context to be used in all the rest of Fragments.
     */
    private static Context mContext;

    /**
     * Function which determines how the MainActivity is displayed when it is created.
     * It contains a BottomNavigationView where it is set the three Fragments of the Activity
     * and functions which determine how the Floating buttons react when being clicked
     * @param savedInstanceState is a parameter which contains information about the version the
     *                           app will use to set the BottomNavigationView characteristics
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        mContext = getApplicationContext();

        openThree();
        constraintClick();
        openAdd();
        openMaterial();
        openHelp();

    }

    /**
     * Function which link the button "fabThree" to its corresponding FloatingButton
     * It also determines what happens when you click on it
     */
    public void openThree() {
        fabThree = findViewById(R.id.threeFabButton);
        fabThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    hideFab(fabThree);
                    showFab(fabAdd);
                    showFab(fabMaterial);
                    showFab(fabHelp);
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    /**
     * This function closes the group of buttons when you click elsewhere
     */
    public void constraintClick() {
        ConstraintLayout container = findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showFab(fabThree);
                    hideFab(fabAdd);
                    hideFab(fabMaterial);
                    hideFab(fabHelp);
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    /**
     * Function which displays the FloatingButton which is input as parameter
     * @param b FloatingButton which will be displayed
     */
    public void showFab (FloatingActionButton b) {
        b.show();
        b.setEnabled(true);
    }

    /**
     * Function which hides the FloatingButton which is input as parameter
     * @param b FloatingButton which will be hidden
     */
    public void hideFab (final FloatingActionButton b) {
        b.hide();
        b.setEnabled(false);
    }

    /**
     * Function which links the FloatingButton to the corresponding AddDialog Pop UP and determines
     * what happens when it is pressed
     */
    public void openAdd() {

        fabAdd = findViewById(R.id.addFabButton);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogAdd();
            }
        });
    }

    /**
     * Function which is in charge of opening the Pop Up Dialog where new orders are made
     */
    public void onDialogAdd() {
        AddDialog addDialog = new AddDialog();
        addDialog.show(getSupportFragmentManager(), "Add Dialog");
    }

    /**
     * Function which links the FloatingButton to the MaterialsDialog Pop Up where new material can
     * be added to the database
     */
    public void openMaterial(){
        fabMaterial = findViewById(R.id.addMaterialButton);
        fabMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogMaterials();
            }
        });
    }

    /**
     * Functions which is in charge of opening the MaterialsDialog Pop Up where new materials are added to
     * the database
     */
    public void onDialogMaterials() {
        MaterialsDialog materialsDialog = new MaterialsDialog();
        materialsDialog.show(getSupportFragmentManager(), "Add Materials");
    }

    /**
     * Functions which links the FloatingButton to the HelpDialog Pop Up and opens it when clicked
     */
    public void openHelp() {
        fabHelp = findViewById(R.id.helpFabButton);
        fabHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openHelp = new Intent(getContext(), HelpActivity.class);
                startActivity(openHelp);
            }
        });
    }

    /**
     * Function which stets the variable mContext, it is used in the fragments to ge a common context
     * @return the context of the main activity
     */
    public static Context getContext(){
        return mContext;
    }

}
