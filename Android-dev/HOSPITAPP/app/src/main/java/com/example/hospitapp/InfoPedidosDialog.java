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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitapp.ui.ListClassAdapter;
import com.example.hospitapp.ui.ListProveedoresClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.ToDoubleBiFunction;

public class InfoPedidosDialog extends AppCompatDialogFragment {

    RecyclerView recyclerView;
    ArrayList<Order> listOfProveedores;


    private TextView nameProviderInput;
    private TextView nameBussinesInput;
    private TextView emailInput;

    private EditText idInput;
    private EditText enlazarInput;

    private Button buscarIdButton;
    private Button enlazarButton;

    private String nameProvider;
    private String nameBussiness;
    private String email;
    private String id;
    private String idProveedor;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.activity_proveedores, null, false);
        buscarIdButton = view.findViewById(R.id.buscarButton);
        enlazarButton = view.findViewById(R.id.enlazarButton);
        idInput = view.findViewById(R.id.idInput);
        enlazarInput = view.findViewById(R.id.enlazarInput);


        listOfProveedores = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerProveedores);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        buscarIdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullEdit(idInput)) {
                    id = idInput.getText().toString();
                    fillList();
                }
            }
        });

        enlazarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullEdit(enlazarInput)){
                    idProveedor = enlazarInput.getText().toString();
                    sendIdProveedor("http:// url");
                }
            }
        });

        ListClassAdapter adapter = new ListClassAdapter(listOfProveedores);

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

        /* TODO CAMBIAR EL URL AL QUE SEA CONVENIENTE */
        makeListRequest("HTTP://URL DE LISTA DE PROVEEDORES PARA UN DETERMINADO PEDIDO");
    }

    private void makeListRequest (String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject order = array.getJSONObject(i);

                        listOfProveedores.add(new Order(
                                order.getInt("id"),
                                order.getInt("id_objeto"),
                                order.getInt("cantidad"),
                                order.getInt("id_proveedor"),
                                order.getInt("id_hospital"),
                                order.getString("fecha"),
                                order.getString("direccion_envio"),
                                order.getString("nombre_objeto")
                                /*
                                order.getInt("id"),
                                order.getString("usuario"),
                                order.getString("email"),
                                proveedor.getString("direccion"),
                                proveedor.getString("telefono"),
                                proveedor.getString("descripcion")*/
                        ));

                    }

                    ListClassAdapter adapter = new ListClassAdapter(listOfProveedores);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.getContext(), "ERROR PEDIDOS", Toast.LENGTH_SHORT).show();
            }
        });

        /*  TODO CHECK IF CONTEXT WORKS  (MainActivity.getContext() */
        Volley.newRequestQueue(MainActivity.getContext()).add(stringRequest);

    }

    private boolean fullEdit(EditText editText) {
        String empty = "";
        return !editText.getText().toString().trim().equals(empty);
    }

    private void sendIdProveedor(String URL) {

    }

}
