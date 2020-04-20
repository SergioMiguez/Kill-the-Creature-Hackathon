package com.example.hospitapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitapp.ui.ListFilterAdapter;
import com.example.hospitapp.ui.ListSentAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/** It is the class responsible for filtering the orders.*/
public class FilterDialog extends AppCompatDialogFragment {

    /** Private field used to store the orders that have been linked in a recycleView.*/
    private static RecyclerView recyclerView;
    /** RequestQueue used to make a call to the server to get and Update information.*/
    private RequestQueue requestQueue;
    /** Used to get the situation of the app to display the messages.*/
    private Context mContext;

    /** Drop-down menu*/
    private Spinner objectInput;
    /** String used to store the material of the selected spinner.*/
    private String materialSelectedSpinner;

    /** ArrayList of Materials with all the materials*/
    private ArrayList<Material> listOfMaterials;
    /** ArrayList of strings with the name of the materials*/
    private ArrayList<String> listOfMaterialName;

    /** Button that is responsible for the ID linking*/
    private Button linkButton;
    /** EditText used to store the input of the ID.*/
    private EditText inputIDLink;
    /** String that represents the ID number that is inputed*/
    private String inputIDText;

    /**
     * Creates the pop-up display used to introduce the ID by which it is going to be filtered.
     * @param savedInstanceState saved data about the current app status used to create the pop-up.
     * @return the pop-up display used to place an order.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_filter, null, false);

        mContext = MainActivity.getContext();
        ArrayList<Order> listOfOrders = new ArrayList<>();

        listOfMaterials = new ArrayList<>();
        listOfMaterialName = new ArrayList<>();

        objectInput = view.findViewById(R.id.objectInput);
        makeMaterialListRequest(URLS.show_materials_url);

        linkButton = view.findViewById(R.id.linkButton);
        inputIDLink = view.findViewById(R.id.inputIDfilter);
        linkButtonPressed();

        recyclerView = view.findViewById(R.id.recyclerSentOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        updateFilter();

        builder.setView(view)
                .setTitle("Your list of orders")
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
     * Sets the action when the button link ID is pressed.
     */
    private void linkButtonPressed() {
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputIDText = inputIDLink.getText().toString();
                if (!inputIDText.equals("")) {
                    sendIdServerLinkedOrder(URLS.provider_link_order_request_url);
                }
            }
        });
    }


    /**
     * Method that is responsible for making the list where all the materials are filtered as desired
     * @param URL The url to the appropriate web service.
     */
    private void makeMaterialListRequest(String URL) {
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

                    for (int i = 0; i < listOfMaterials.size(); i++) {
                        listOfMaterialName.add(listOfMaterials.get(i).getMaterialName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.item_custom_spinner,listOfMaterialName);

                    objectInput.setAdapter(adapter);

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
     * Updates the filter when introduced the required parameters
     */
    private void updateFilter() {
        objectInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                makeListRequestFiltered(URLS.display_all_orders_url);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        makeListRequestFiltered(URLS.display_orders_url);
    }

    /**
     * Makes a server call to request the list of the filtered list
     * @param URL The url to the appropriate web service.
     */
    private void makeListRequestFiltered (String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array = new JSONArray(response);

                    ArrayList<Order> listOfProveedores = new ArrayList<>();
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
                    materialSelectedSpinner = objectInput.getSelectedItem().toString();
                    ArrayList<Order> filteredArray = new ArrayList<>();
                    for (Order order : listOfProveedores) {
                        if (order.getObject_name().equals(materialSelectedSpinner)) {
                            filteredArray.add(order);
                        }
                    }

                    ListFilterAdapter adapter = new ListFilterAdapter(filteredArray, materialSelectedSpinner);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    Toast.makeText(MainActivity.getContext(), "CONNECTION ERROR", Toast.LENGTH_SHORT).show();
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
                return parameters;
            }
        };
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    /**
     * Sends the ID to the server in order to link the order.
     * @param URL The url to the appropriate web service.
     */
    private void sendIdServerLinkedOrder(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(MainActivity.getContext(), response, Toast.LENGTH_SHORT).show();
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getBoolean("success")){
                        Toast.makeText(MainActivity.getContext(), "Success!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.getContext(), "Could not create a user, possible user duplication issue.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("username", LoginActivity.userName);
                parameters.put("id_pedido", inputIDText);
                return parameters;
            }
        };
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}

