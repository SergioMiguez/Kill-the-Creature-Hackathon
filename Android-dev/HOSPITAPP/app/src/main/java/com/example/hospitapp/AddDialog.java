package com.example.hospitapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Objects;

public class AddDialog extends AppCompatDialogFragment {

    private String object;
    private String volumeNumber;
    private Switch keepAdress;
    private String newAdress;

    private EditText objectInput;
    private EditText volumeInput;
    private EditText addressInput;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_dialog, null);

        objectInput = view.findViewById(R.id.objectInput);
        volumeInput = view.findViewById(R.id.inputVN);
        addressInput = view.findViewById(R.id.changeAddress);

        keepAdress = view.findViewById(R.id.switch1);


        builder.setView(view)
                .setTitle("Hacer pedido")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            Objects.requireNonNull(objectInput);
                            Objects.requireNonNull(volumeInput);
                            Objects.requireNonNull(addressInput);

                            object = objectInput.getText().toString();
                            volumeNumber = volumeInput.getText().toString();

                            if (!keepAdress.isChecked()) {
                                newAdress = addressInput.getText().toString();
                            } else {
                                /** TODO  change newAdress to the Default Adress*/
                                newAdress = addressInput.getText().toString();
                            }



                            makeServerCall("httpURL");
                    }
                });

        return builder.create();
    }

    /**     TODO    */
    public boolean makeServerCall(String URL) {

        return false;
    }

}
