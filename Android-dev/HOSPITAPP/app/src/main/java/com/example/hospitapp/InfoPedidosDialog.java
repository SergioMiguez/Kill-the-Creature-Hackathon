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
        listOfProveedores.add(new Proveedor(0, "Proveedor", "Business", "email", "direccion", "telefono"));
        listOfProveedores.add(new Proveedor(0, "Proveedor", "Business", "email", "direccion", "telefono"));
        listOfProveedores.add(new Proveedor(0, "Proveedor", "Business", "email", "direccion", "telefono"));
        listOfProveedores.add(new Proveedor(0, "Proveedor", "Business", "email", "direccion", "telefono"));

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
                        JSONObject proveedor = array.getJSONObject(i);

                        listOfProveedores.add(new Proveedor(
                                proveedor.getInt("id"),
                                proveedor.getString("usuario"),
                                proveedor.getString("email"),
                                proveedor.getString("direccion"),
                                proveedor.getString("telefono"),
                                proveedor.getString("descripcion")
                        ));

                    }

                    ListProveedoresClass adapter = new ListProveedoresClass(listOfProveedores);
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

}
