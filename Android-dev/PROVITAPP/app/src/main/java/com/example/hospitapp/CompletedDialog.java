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
import com.example.hospitapp.ui.ListCompletedAdapter;
import com.example.hospitapp.ui.ListFilterAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompletedDialog extends AppCompatDialogFragment {

    private RecyclerView recyclerView;
    private ArrayList<Order> listOfOrders;
    private RequestQueue requestQueue;
    private Context mContext;
    private Button markAsCompletedButton;
    private EditText idInput;
    private String inputIdReceived;
    private String fecha;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_completed, null, false);
        mContext = this.getContext();
        listOfOrders = new ArrayList<>();

        markAsCompletedButton = view.findViewById(R.id.markAsCompleted);
        idInput = view.findViewById(R.id.idInput);
        markAsCompleted();

        recyclerView = view.findViewById(R.id.recyclerCompleted);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fillList();


        builder.setView(view)
                .setTitle("List orders linked to be completed:")
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

    private void markAsCompleted() {
        markAsCompletedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputIdReceived = idInput.getText().toString();
                if (!inputIdReceived.equals("")) {
                    /* TODO CHANGE URL TO COMPLETED ORDERS*/
                    sendReceivedRequest("URL");
                    fillList();
                }
            }
        });
    }

    private void fillList() {
        makeListRequest(URLS.display_connected_orders_url);
    }

    private void makeListRequest (String URL) {
        //Toast.makeText(MainActivity.getContext(), "entra dentro del request", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    ArrayList<Order> listOfOrders = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject order = array.getJSONObject(i);

                        listOfOrders.add(new Order(
                                order.getInt("id"),
                                order.getInt("id_objeto"),
                                order.getInt("cantidad"),
                                order.getInt("id_proveedor"),
                                order.getInt("id_hospital"),
                                order.getString("fecha"),
                                order.getString("direccion_envio"),
                                order.getString("nombre_objeto"),
                                order.getInt("enviado"),
                                order.getInt("recibido")
                        ));

                    }

                    ArrayList<Order> onlyLinked = new ArrayList<>();
                    for (Order order : listOfOrders) {
                        if (!(order.isEnviado() || order.isRecibido())) {
                            onlyLinked.add(order);
                        }
                    }

                    ListCompletedAdapter adapter = new ListCompletedAdapter(onlyLinked);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.getContext(), "ERROR ORDERS", Toast.LENGTH_SHORT).show();
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

    private void sendReceivedRequest(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getBoolean("success")){
                        Toast.makeText(MainActivity.getContext(), "Success!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.getContext(), "It was not possible to create the user, possible error of duplication", Toast.LENGTH_SHORT).show();
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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("usuario", LoginActivity.userName);
                parameters.put("fecha_recibido", getDate());
                parameters.put("id_pedido", inputIdReceived);
                return parameters;
            }
        };
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getDate () {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}

