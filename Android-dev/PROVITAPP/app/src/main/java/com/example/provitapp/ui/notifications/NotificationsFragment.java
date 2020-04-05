package com.example.provitapp.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.provitapp.R;

public class NotificationsFragment extends Fragment {

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

    SharedPreferences myPrefs;

    String def = "ERROR";

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        nameAdded = root.findViewById(R.id.nameAdded);
        nameHospitalAdded = root.findViewById(R.id.nameHospitalAdded);
        nameStreetAdded = root.findViewById(R.id.nameStreetAdded);
        streetNumberAdded = root.findViewById(R.id.streetNumberAdded);
        CPAdded = root.findViewById(R.id.CPAdded);
        cityAdded = root.findViewById(R.id.cityAdded);
        emailAdded = root.findViewById(R.id.emailAdded);
        telephoneAdded = root.findViewById(R.id.telephoneAdded);

        myPrefs = getActivity().getSharedPreferences("prefID", Context.MODE_PRIVATE);

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

        Button saveUserBtn = root.findViewById(R.id.saveUserBtn);
        saveUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameEdited = root.findViewById(R.id.nameEdited);
                nameHospitalEdited = root.findViewById(R.id.nameHospitalEdited);
                nameStreetEdited = root.findViewById(R.id.nameStreetEdited);
                streetNumberEdited = root.findViewById(R.id.streetNumberEdited);
                CPEdited = root.findViewById(R.id.CPEdited);
                cityEdited = root.findViewById(R.id.cityEdited);
                emailEdited = root.findViewById(R.id.emailEdited);
                telephoneEdited = root.findViewById(R.id.telephoneEdited);

                String nameEditedStr = nameEdited.getText().toString();
                String nameHospitalEditedStr = nameHospitalEdited.getText().toString();
                String nameStreetEditedStr = nameStreetEdited.getText().toString();
                String streetNumberEditedStr = streetNumberEdited.getText().toString();
                String CPEditedStr = CPEdited.getText().toString();
                String cityEditedStr = cityEdited.getText().toString();
                String emailEditedStr = emailEdited.getText().toString();
                String telephoneEditedStr = telephoneEdited.getText().toString();

                myPrefs = getActivity().getSharedPreferences("prefID", Context.MODE_PRIVATE);
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

                boolean allFilled = fullEdit(nameEdited) && fullEdit(nameHospitalEdited) && fullEdit(nameStreetEdited) &&
                        fullEdit(emailEdited) && fullEdit(telephoneEdited);

                if (allFilled) {
                    nameAdded.setText(name);
                    nameHospitalAdded.setText(hospital);
                    nameStreetAdded.setText(street);
                    streetNumberAdded.setText(street_number);
                    CPAdded.setText(CP);
                    cityAdded.setText(city);
                    emailAdded.setText(email);
                    telephoneAdded.setText(telephone);

                    nameEdited.getText().clear();
                    nameHospitalEdited.getText().clear();
                    nameStreetEdited.getText().clear();
                    streetNumberEdited.getText().clear();
                    CPEdited.getText().clear();
                    cityEdited.getText().clear();
                    emailEdited.getText().clear();
                    telephoneEdited.getText().clear();
                }
                else {
                    Toast.makeText(getContext(), "Rellene todos los campos para continuar!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }

    private boolean fullEdit(EditText editText) {
        String empty = "";
        return !editText.getText().toString().trim().equals("");
    }






}
