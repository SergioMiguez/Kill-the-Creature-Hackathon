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
import android.widget.TextView;

import com.example.hospitapp.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        /*final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

         */

        Button editUserBtn = root.findViewById(R.id.editUserBtn);
        editUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userInfo = new Intent(getContext(), InfoUsuario.class);
                startActivity(userInfo);
            }
        });

        TextView nameAdded = root.findViewById(R.id.nameAdded);
        TextView nameHospitalAdded = root.findViewById(R.id.nameHospitalAdded);
        TextView nameStreetAdded = root.findViewById(R.id.nameStreetAdded);
        TextView streetNumberAdded = root.findViewById(R.id.streetNumberAdded);
        TextView CPAdded = root.findViewById(R.id.CPAdded);
        TextView cityAdded = root.findViewById(R.id.cityAdded);
        TextView emailAdded = root.findViewById(R.id.emailAdded);
        TextView telephoneAdded = root.findViewById(R.id.telephoneAdded);


        if (getActivity().getIntent().hasExtra("key.name.edited")) {
            String nameAddedStr = getActivity().getIntent().getStringExtra("key.name.edited");
            nameAdded.setText(nameAddedStr);
        }
        else if (getActivity().getIntent().hasExtra("key.name.hospital.edited")) {
            String nameHospitalAddedStr = getActivity().getIntent().getStringExtra("key.name.hospital.edited");
            nameHospitalAdded.setText(nameHospitalAddedStr);
        }
        else if (getActivity().getIntent().hasExtra("key.name.street.edited")) {
            String nameStreetAddedStr = getActivity().getIntent().getStringExtra("key.name.street.edited");
            nameStreetAdded.setText(nameStreetAddedStr);
        }
        else if (getActivity().getIntent().hasExtra("key.street.number.edited")) {
            String streetNumberStr = getActivity().getIntent().getStringExtra("key.street.number.edited");
            streetNumberAdded.setText(streetNumberStr);
        }
        else if (getActivity().getIntent().hasExtra("key.CP.edited")) {
            String CPAddedStr = getActivity().getIntent().getStringExtra("key.CP.edited");
            CPAdded.setText(CPAddedStr);
        }
        else if (getActivity().getIntent().hasExtra("key.city.edited")) {
            String cityAddedStr = getActivity().getIntent().getStringExtra("key.city.edited");
            cityAdded.setText(cityAddedStr);
        }
        else if (getActivity().getIntent().hasExtra("key.email.edited")) {
            String emailAddedStr = getActivity().getIntent().getStringExtra("key.email.edited");
            emailAdded.setText(emailAddedStr);
        }
        else if (getActivity().getIntent().hasExtra("key.telephone.edited")) {
            String telephoneAddedStr = getActivity().getIntent().getStringExtra("key.telephone.edited");
            telephoneAdded.setText(telephoneAddedStr);
        }

        return root;
    }




}
