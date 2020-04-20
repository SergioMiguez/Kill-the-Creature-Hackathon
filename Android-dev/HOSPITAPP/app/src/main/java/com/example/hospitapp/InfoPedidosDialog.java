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
import com.example.hospitapp.ui.ListClassAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/** Creates the display which allows the hospital to select the producer of a particular order. */
public class InfoPedidosDialog extends AppCompatDialogFragment {

    /** The display used to show the list of producers available to fulfill a particular order.*/
    RecyclerView recyclerView;
    /** The list of producers available to fulfill a particular order.*/
    ArrayList<Order> listOfProducers;

    /** The text box used to input the ID of the order you want to find a producer for. */
    private EditText idInput;
    /** The text box used to input the ID of the producer you choose to fulfill an order. */
    private EditText linkInput;

    /** The button clicked to store the data in {@link #idInput} */
    private Button searchIdButton;
    /** The button clicked to store the data in {@link #linkInput} */
    private Button linkButton;

    /** The ID of the order you want to find a producer for. */
    private String id;
    /** The ID of the producer you choose to fulfill an order. */
    private String idProducer;
    /** The call to the server. */
    private RequestQueue requestQueue;

    /**
     * Creates the pop-up display used to select the producer to manufacture an order.
     * The user first inputs the id of the order of interest.
     * Then, after clicking 'Search ID' the list of producers available to complete this order is displayed.
     * Then the user can input the id of the producer chosen.
     * Finally, the 'Link' button can be pressed to update the server with this new information.
     * @param savedInstanceState saved data about the current app status used to create the pop-up.
     * @return the pop-up display used to select the producer to manufacture an order.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_proveedores, null, false);
        searchIdButton = view.findViewById(R.id.buscarButton);
        linkButton = view.findViewById(R.id.enlazarButton);
        idInput = view.findViewById(R.id.idInput);
        linkInput = view.findViewById(R.id.enlazarInput);

        listOfProducers = new ArrayList<>();

        recyclerView =  view.findViewById(R.id.recyclerProveedores);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchIdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullEdit(idInput)) {
                    id = idInput.getText().toString();
                    fillList();
                }
            }
        });

        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullEdit(linkInput)){
                    idProducer = linkInput.getText().toString();
                    sendIdProveedor(URLS.connect_orders_url);
                }
            }
        });

        ListClassAdapter adapter = new ListClassAdapter(listOfProducers);

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

    /**
     * Displays the list of providers available to complete an order.
     */
    private void fillList() {

        makeListRequest(URLS.show_potential_providers_url);
    }

    /**
     * Makes a server call to get the list of potential providers for a given order.
     * @param URL The url to the appropriate web service.
     */
    private void makeListRequest (String URL) {
        //Toast.makeText(MainActivity.getContext(), "entra dentro del request", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject order = array.getJSONObject(i);


                        listOfProducers.add(new Order(
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

                    ListClassAdapter adapter = new ListClassAdapter(listOfProducers);
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

       
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    /**
     * Verifies that the user has filled a text box.
     * @param editText the text box of interest.
     * @return true if editText has data and false otherwise.
     */
    private boolean fullEdit(EditText editText) {
        String empty = "";
        return !editText.getText().toString().trim().equals(empty);
    }

    /**
     * Updates the server with producer chosen to complete the order.
     * @param URL The url to the appropriate web service.
     */
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
                    Toast.makeText(MainActivity.getContext(), "Unable to fulfil request", Toast.LENGTH_SHORT).show();
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
                parameters.put("id_proveedor", idProducer);
                parameters.put("id", id);
                return parameters;
            }
        };
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
