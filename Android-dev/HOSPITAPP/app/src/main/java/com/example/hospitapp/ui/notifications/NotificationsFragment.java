package com.example.hospitapp.ui.notifications;

import android.content.Intent;
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

import com.example.hospitapp.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        final TextView nameAdded = root.findViewById(R.id.nameAdded);
        final TextView nameHospitalAdded = root.findViewById(R.id.nameHospitalAdded);
        final TextView nameStreetAdded = root.findViewById(R.id.nameStreetAdded);
        final TextView streetNumberAdded = root.findViewById(R.id.streetNumberAdded);
        final TextView CPAdded = root.findViewById(R.id.CPAdded);
        final TextView cityAdded = root.findViewById(R.id.cityAdded);
        final TextView emailAdded = root.findViewById(R.id.emailAdded);
        final TextView telephoneAdded = root.findViewById(R.id.telephoneAdded);

        final EditText nameEdited = root.findViewById(R.id.nameEdited);
        final EditText nameHospitalEdited = root.findViewById(R.id.nameHospitalEdited);
        final EditText nameStreetEdited = root.findViewById(R.id.nameStreetEdited);
        final EditText streetNumberEdited = root.findViewById(R.id.streetNumberEdited);
        final EditText CPEdited = root.findViewById(R.id.CPEdited);
        final EditText cityEdited = root.findViewById(R.id.cityEdited);
        final EditText emailEdited = root.findViewById(R.id.emailEdited);
        final EditText telephoneEdited = root.findViewById(R.id.telephoneEdited);

        Button saveUserBtn = root.findViewById(R.id.saveUserBtn);
        saveUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTextView(nameAdded, nameEdited);
                updateTextView(nameHospitalAdded, nameHospitalEdited);
                updateTextView(nameStreetAdded, nameStreetEdited);
                updateTextView(streetNumberAdded, streetNumberEdited);
                updateTextView(CPAdded, CPEdited);
                updateTextView(cityAdded, cityEdited);
                updateTextView(emailAdded, emailEdited);
                updateTextView(telephoneAdded, telephoneEdited);
            }
        });

        return root;
    }

    private void updateTextView(TextView textView, EditText editText) {
        textView.setText(editText.getText().toString());
        editText.getText().clear();
    }






}
