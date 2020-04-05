package com.example.hospitapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Objects;
import java.util.function.ToDoubleBiFunction;

public class InfoPedidosDialog extends AppCompatDialogFragment {

    private String object;
    private String volumeNumber;
    private Switch keepAdress;
    private String newAdress;

    private EditText objectInput;
    private EditText volumeInput;
    private EditText adressInput;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.information_pedidos__dialog, null);

        objectInput = view.findViewById(R.id.objectInput);
        volumeInput = view.findViewById(R.id.inputVN);
        adressInput = view.findViewById(R.id.changeAddress);

        keepAdress = view.findViewById(R.id.switch1);


        builder.setView(view)
                .setTitle("Esto es una prueba")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        object = objectInput.getText().toString();
                        volumeNumber = volumeInput.getText().toString();

                        if (!keepAdress.isChecked()) {
                            newAdress = adressInput.getText().toString();
                        } else {
                            /** TODO  change newAdress to the Default Adress*/
                            newAdress = adressInput.getText().toString();
                        }

                        Objects.requireNonNull(object);
                        Objects.requireNonNull(volumeNumber);
                        Objects.requireNonNull(newAdress);

                        makeServerCall(object, volumeNumber, newAdress);
                    }
                });

        return builder.create();
    }

    /**     TODO    */
    public boolean makeServerCall(String object, String volumeNumber, String newAdress) {

        return false;
    }

}
