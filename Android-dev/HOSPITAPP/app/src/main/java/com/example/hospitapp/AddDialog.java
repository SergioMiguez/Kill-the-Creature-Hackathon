package com.example.hospitapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitapp.ui.home.HomeFragment;

import org.json.JSONObject;

import java.security.cert.Certificate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.Inflater;

public class AddDialog extends AppCompatDialogFragment {

    private String object;
    private String volumeNumber;
    private Switch keepAddress;
    private String newAddress;

    private EditText objectInput;
    private EditText volumeInput;

    private EditText nameHospitalEdited;
    private EditText nameStreetEdited;
    private EditText CPEdited;
    private EditText streetNumberEdited;
    private EditText cityEdited;
    private EditText emailEdited;
    private EditText telephoneEdited;

    private String nameHospital, nameStreet, CP, streetNumber, city, email, telephone;


    private boolean orderSuccess;
    private RequestQueue requestQueue;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_dialog, null);

        objectInput = view.findViewById(R.id.objectInput);
        volumeInput = view.findViewById(R.id.inputVN);
        nameHospitalEdited = view.findViewById(R.id.nameHospitalEdited);
        nameStreetEdited = view.findViewById(R.id.nameStreetEdited);
        CPEdited = view.findViewById(R.id.CPEdited);
        streetNumberEdited = view.findViewById(R.id.streetNumberEdited);
        cityEdited = view.findViewById(R.id.cityEdited);
        emailEdited = view.findViewById(R.id.emailEdited);
        telephoneEdited = view.findViewById(R.id.telephoneEdited);

        keepAddress = view.findViewById(R.id.switch1);


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

                            Objects.requireNonNull(objectInput);
                            Objects.requireNonNull(volumeInput);


                            object = objectInput.getText().toString();
                            volumeNumber = volumeInput.getText().toString();
                           // newAddress = addressInput.getText().toString();

                            if (!keepAddress.isChecked()) {
                                Objects.requireNonNull(nameHospitalEdited);
                                Objects.requireNonNull(nameStreetEdited);
                                Objects.requireNonNull(CPEdited);
                                Objects.requireNonNull(streetNumberEdited);
                                Objects.requireNonNull(cityEdited);
                                Objects.requireNonNull(emailEdited);
                                Objects.requireNonNull(telephoneEdited);

                                nameHospital = nameHospitalEdited.getText().toString();
                                nameStreet = nameStreetEdited.getText().toString();
                                CP = CPEdited.getText().toString();
                                streetNumber = streetNumberEdited.getText().toString();
                                city = cityEdited.getText().toString();
                                email = emailEdited.getText().toString();
                                telephone = telephoneEdited.getText().toString();

                                /*  TODO REVISAR SI LA DIRECCION ES CORRECTA, ES NECESARIO EL RESTO DE INFORMACION NO USADA*/
                                newAddress = generateNewAdd(nameStreet, streetNumber, CP, city);
                                makeServerCallNewAddress("http://192.168.1.86:80/matalbicho/nuevo_pedido_nueva_direccion.php");
                            } else {
                                makeServerCallDefaultAddress("http://192.168.1.86:80/matalbicho/nuevo_pedido_con_direccion.php");
                            }


                    }
                });

        return builder.create();
    }

    public void makeServerCallDefaultAddress(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("RESPONSE: " + response);
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getBoolean("success")){
                        Toast.makeText(MainActivity.getContext(), "Success!", Toast.LENGTH_SHORT).show();
                        orderSuccess = true;
                    } else {
                        Toast.makeText(MainActivity.getContext(), "No se pudo procesar el pedido", Toast.LENGTH_SHORT).show();
                        orderSuccess = false;
                    }

                } catch (Exception e) {
                    System.out.println(e);
                    Toast.makeText(MainActivity.getContext(), "No existe el objeto", Toast.LENGTH_SHORT).show();
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
                parameters.put("nombre_del_objeto", object);
                parameters.put("cantidad", volumeNumber);
                parameters.put("usuario", LoginActivity.userName);
                parameters.put("fecha", getDate());
                return parameters;
            }
        };
        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


    public void makeServerCallNewAddress(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("RESPONSE: " + response);
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getBoolean("success")){
                        Toast.makeText(MainActivity.getContext(), "Success!", Toast.LENGTH_SHORT).show();
                        orderSuccess = true;
                    } else {
                        Toast.makeText(MainActivity.getContext(), "No se pudo procesar el pedido", Toast.LENGTH_SHORT).show();
                        orderSuccess = false;
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.getContext(), "No existe el objeto", Toast.LENGTH_SHORT).show();
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
                parameters.put("nombre_del_objeto", object);
                parameters.put("cantidad", volumeNumber);
                parameters.put("usuario", LoginActivity.userName);
                parameters.put("fecha", getDate());
                parameters.put("direccion", newAddress);
                return parameters;
            }
        };
        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getDate () {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private String generateNewAdd(String nameStreetEdited, String streetNumberEdited, String CPEdited, String cityEdited) {
        return nameStreetEdited.toUpperCase() + "$" + streetNumberEdited + "$" + CPEdited + "$" + cityEdited.toUpperCase();
    }

}
