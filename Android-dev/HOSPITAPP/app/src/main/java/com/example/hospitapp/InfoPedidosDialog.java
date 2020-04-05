package com.example.hospitapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hospitapp.ui.ListProveedoresClass;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.ToDoubleBiFunction;

public class InfoPedidosDialog extends AppCompatDialogFragment {

    RecyclerView recyclerView;
    ArrayList<Proveedor> listOfProveedores;

    private TextView nameProviderInput;
    private TextView nameBussinesInput;
    private TextView emailInput;

    private Button acceptButton;

    private String nameProvider;
    private String nameBussiness;
    private String email;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.information_pedidos__dialog, null, false);

        listOfProveedores = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerProveedores);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fillList();

        ListProveedoresClass adapter = new ListProveedoresClass(listOfProveedores);

        recyclerView.setAdapter(adapter);

        builder.setView(view)
                .setTitle("Lista de proveedores")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }

    private void fillList() {
        listOfProveedores.add(new Proveedor("Nombre", "Business", "email@", "usuario", "pass", "direccion", "telf", 0));
        listOfProveedores.add(new Proveedor("Nombre", "Business", "email@", "usuario", "pass", "direccion", "telf", 0));
        listOfProveedores.add(new Proveedor("Nombre", "Business", "email@", "usuario", "pass", "direccion", "telf", 0));
        listOfProveedores.add(new Proveedor("Nombre", "Business", "email@", "usuario", "pass", "direccion", "telf", 0));
        listOfProveedores.add(new Proveedor("Nombre", "Business", "email@", "usuario", "pass", "direccion", "telf", 0));
        listOfProveedores.add(new Proveedor("Nombre", "Business", "email@", "usuario", "pass", "direccion", "telf", 0));
        
    }

}
