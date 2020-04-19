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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitapp.ui.ListClassAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    private RequestQueue requestQueue;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_proveedores, null, false);
        buscarIdButton = view.findViewById(R.id.buscarButton);
        enlazarButton = view.findViewById(R.id.enlazarButton);
        idInput = view.findViewById(R.id.idInput);
        enlazarInput = view.findViewById(R.id.enlazarInput);

        listOfProveedores = new ArrayList<>();

        recyclerView =  view.findViewById(R.id.recyclerProveedores);
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
                    sendIdProveedor(URLS.connect_orders_url);
                }
            }
        });

        ListClassAdapter adapter = new ListClassAdapter(listOfProveedores);

        recyclerView.setAdapter(adapter);

        builder.setView(view)
                .setTitle("List of providers")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }

    private void fillList() {

        /* TODO CAMBIAR EL URL AL QUE SEA CONVENIENTE */
        makeListRequest(URLS.show_potential_providers_url);
    }

    private void makeListRequest (String URL) {
        //Toast.makeText(MainActivity.getContext(), "entra dentro del request", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
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
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("usuario", LoginActivity.userName);
                parameters.put("id_pedido", id);
                return parameters;
            }
        };

        /*  TODO CHECK IF CONTEXT WORKS  (MainActivity.getContext() */
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    private boolean fullEdit(EditText editText) {
        String empty = "";
        return !editText.getText().toString().trim().equals(empty);
    }


    private void sendIdProveedor (String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getBoolean("success")){
                        Toast.makeText(MainActivity.getContext(), "Success!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.getContext(), "No se pudo crear el usuario, posible error de duplicacion", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(), "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("usuario", LoginActivity.userName);
                parameters.put("id_proveedor", idProveedor);
                parameters.put("id", id);
                return parameters;
            }
        };
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
