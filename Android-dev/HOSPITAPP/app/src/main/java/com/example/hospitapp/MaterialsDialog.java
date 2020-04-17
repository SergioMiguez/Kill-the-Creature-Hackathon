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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitapp.ui.ListAdaptor;
import com.example.hospitapp.ui.ListClassAdapter;
import com.example.hospitapp.ui.ListMaterialsAdaptor;
import com.example.hospitapp.ui.notifications.NotificationsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MaterialsDialog extends AppCompatDialogFragment {

    private RecyclerView recyclerView;
    private ArrayList<Material> listOfMaterials;
    private String state;
    private RequestQueue requestQueue;
    private Button addMaterial;
    private EditText materialInputText;
    private String material;



    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.materials_dialog, null);

        state = "MATERIALS";

        addMaterial = view.findViewById(R.id.addMaterialButton);
        materialInputText = view.findViewById(R.id.newMaterialInput);
        addMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NotificationsFragment.fullEdit(materialInputText)) {
                    material = materialInputText.getText().toString();
                    sendServerNewMaterial(URLS.add_material_url);
                }
                else {
                    Toast.makeText(getContext(), "Fill in the required \"Type of Material\" field!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        recyclerView = view.findViewById(R.id.recyclerMaterials);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listOfMaterials = new ArrayList<>();

        fillList();


        ListMaterialsAdaptor adapter = new ListMaterialsAdaptor(listOfMaterials);

        recyclerView.setAdapter(adapter);

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

                    }
                });


        return builder.create();
    }

    private void fillList() {
        /*  TODO IMPLEMENT URL  */
        makeListRequest(URLS.show_materials_url);
    }

    private void makeListRequest(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject material = array.getJSONObject(i);

                        listOfMaterials.add(new Material(
                                material.getInt("id"),
                                material.getString("nombre")
                        ));

                    }

                    ListMaterialsAdaptor adapter = new ListMaterialsAdaptor(listOfMaterials);

                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (MainActivity.getContext() != null) {
                    Toast.makeText(MainActivity.getContext(), "ERROR HOME", Toast.LENGTH_SHORT).show();
                }

            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("usuario", LoginActivity.userName);
                return parameters;
            }
        };

        /**
         *
         *if (mContext != null) {
         *Volley.newRequestQueue(mContext).add(stringRequest);
         *}
         */

        requestQueue= Volley.newRequestQueue(MainActivity.getContext());
        requestQueue.add(stringRequest);
    }

    private void sendServerNewMaterial(String URL) {
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
                parameters.put("nombre_material", material);
                return parameters;
            }
        };
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


}
