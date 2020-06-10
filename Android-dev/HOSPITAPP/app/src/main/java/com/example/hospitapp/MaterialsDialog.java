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

import com.example.hospitapp.ui.ListMaterialsAdaptor;
import com.example.hospitapp.ui.notifications.NotificationsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Public class that creates the display which allows the Hospital to add a new Material and to see what materials are available.
 */
public class MaterialsDialog extends AppCompatDialogFragment {
    /**
     * Private field used to store the used materials in a recycleView.
     */
    private RecyclerView recyclerView;
    /**
     * Private field used to store the used materials in an ArrayList.
     */
    private ArrayList<Material> listOfMaterials;
    /**
     * Private field used to store the state of the dialog.
     */
    private String state;
    /**
     * Private field used to make a request to the server to obtain information and Update information of the database (materials).
     */
    private RequestQueue requestQueue;
    /**
     * Button that is used to add a send a request to the server to add a new Material.
     */
    private Button addMaterial;
    /**
     * Private EditText used to store the input of the user (the name of the new material).
     */
    private EditText materialInputText;
    /**
     * Private string used to store the information of the EditText.
     */
    private String material;


    /**
     * Method used to create the layout of the pop-up (dialog) and to initialize all the values.
     * This dialog allows the user to create a new Material and also see the materials that are already available.
     * @param  savedInstanceState saved data about the current app status used to create the pop-up.
     * @return the pop-up display.
     */
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
                .setTitle("Add Material")
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

    /**
     * Private method used to fill the list of materials, it calls the server with the method makeListRequest.
     */
    private void fillList() {

        makeListRequest(URLS.show_materials_url);
    }

    /**
     * Private Method tha is used when the dialog is initialized, it makes a request to the server to get a list (JsonArray) of all the materials available in the database,
     * it adds those materials to the ArrayList listOfMaterials.
     * @param URL given URL to make the server call
     */

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



        requestQueue= Volley.newRequestQueue(MainActivity.getContext());
        requestQueue.add(stringRequest);
    }
    /**
     * Private Method that is used when the the button addMaterial is pressed.
     * It gives information to the server to create a new Entry for a new Material in the database
     * @param URL given URL to make the server call
     */

    private void sendServerNewMaterial(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getBoolean("success")){
                        Toast.makeText(MainActivity.getContext(), "Success!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.getContext(), "Material couldn't be created. Possible duplication error.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.getContext(),"Material couldn't be created. Possible duplication error.", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.getContext(),"Error in connexion with the database", Toast.LENGTH_LONG).show();
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
