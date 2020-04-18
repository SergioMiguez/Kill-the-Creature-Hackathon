package com.example.provitapp;

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
import com.example.provitapp.ui.ListClassAdapter;
import com.example.provitapp.ui.ListSentAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompletedDialog extends AppCompatDialogFragment {

    private static ArrayList<Order> listOfOrders;
    private static RecyclerView recyclerView;
    private final String stateLinked = "LINKED";
    private RequestQueue requestQueue;
    private Context mContext;
    private Button markAsReceived;
    private EditText idInput;
    private String inputIdReceived;
    private String fecha;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.received_dialog, null, false);

        mContext = MainActivity.getContext();
        listOfOrders = new ArrayList<>();

        markAsReceived = view.findViewById(R.id.markAsDeliveredButton);
        idInput = view.findViewById(R.id.idInput);
        markAsReceivedButton();

        recyclerView = view.findViewById(R.id.recyclerSentOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        fillList();


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

    private void markAsReceivedButton() {
        markAsReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputIdReceived = idInput.getText().toString();
                if (!inputIdReceived.equals("")) {
                    //sendReceivedRequest(URLS.confirm_received_url);
                }
            }
        });
    }

    private void fillList() {
        makeListRequest(URLS.display_orders_url);
    }

    private void makeListRequest (String URL) {
        //Toast.makeText(MainActivity.getContext(), "entra dentro del request", Toast.LENGTH_SHORT).show();
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

                    ListSentAdapter adapter = new ListSentAdapter(listOfProveedores);
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
                return parameters;
            }
        };

        /*  TODO CHECK IF CONTEXT WORKS  (MainActivity.getContext() */
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
}

