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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitapp.ui.ListMaterialsAdaptor;
import com.example.hospitapp.ui.home.HomeFragment;
import com.example.hospitapp.ui.notifications.NotificationsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.security.cert.Certificate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.Inflater;
/** Creates the display which allows the hospital to place a new order */
public class AddDialog extends AppCompatDialogFragment {

    private String object;
    private String volumeNumber;
    private Switch keepAddress;
    private String newAddress;

    private Spinner objectInput;
    private EditText volumeInput;

    private ArrayList<Material> listOfMaterials;
    private ArrayList<String> listOfMaterialName;

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

    /**
     * Creates the pop-up display used to place an order.
     * It prompts the hospital for the material being ordered and the quantity of the material.
     * The hospital is allowed to send the order to their address or to a different one.
     * @param savedInstanceState saved data about the current app status used to create the pop-up.
     * @return the pop-up display.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_dialog, null);

        listOfMaterials = new ArrayList<>();
        listOfMaterialName = new ArrayList<>();

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

        makeMaterialListRequest(URLS.show_materials_url);


        builder.setView(view)
                .setTitle("Place Order")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        boolean nonEmptyOrder = //NotificationsFragment.fullEdit(objectInput) &&
                                NotificationsFragment.fullEdit(volumeInput);

                        if (nonEmptyOrder) {
                            object = objectInput.getSelectedItem().toString();
                            volumeNumber = volumeInput.getText().toString();
                            // newAddress = addressInput.getText().toString();

                            if (!keepAddress.isChecked()) {

                                boolean nonEmptyChangeOrder = NotificationsFragment.fullEdit(nameHospitalEdited) &&
                                        NotificationsFragment.fullEdit(nameStreetEdited) &&
                                        NotificationsFragment.fullEdit(CPEdited) &&
                                        NotificationsFragment.fullEdit(streetNumberEdited) &&
                                        NotificationsFragment.fullEdit(cityEdited) &&
                                        NotificationsFragment.fullEdit(emailEdited) &&
                                        NotificationsFragment.fullEdit(telephoneEdited);

                                if (nonEmptyChangeOrder) {

                                    nameHospital = nameHospitalEdited.getText().toString();
                                    nameStreet = nameStreetEdited.getText().toString();
                                    CP = CPEdited.getText().toString();
                                    streetNumber = streetNumberEdited.getText().toString();
                                    city = cityEdited.getText().toString();
                                    email = emailEdited.getText().toString();
                                    telephone = telephoneEdited.getText().toString();

                                    /*  TODO REVISAR SI LA DIRECCION ES CORRECTA, ES NECESARIO EL RESTO DE INFORMACION NO USADA*/
                                    newAddress = generateNewAdd(nameStreet, streetNumber, CP, city);
                                    makeServerCallNewAddress(URLS.new_order_with_new_address_url);
                                    dialog.dismiss();
                                }
                            } else {
                                makeServerCallDefaultAddress(URLS.new_order_with_same_address_url);
                                dialog.dismiss();
                            }
                        }
                        else {
                            Toast.makeText(getContext(), "No order made. You need to fill in the required fields!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return builder.create();
    }

    /**
     * Makes a server call to place an order with the input data using the default hospital address.
     * @param URL The url to the appropriate web service.
     */
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
                        Toast.makeText(MainActivity.getContext(), "Order could not be processed.", Toast.LENGTH_SHORT).show();
                        orderSuccess = false;
                    }

                } catch (Exception e) {
                    System.out.println(e);
                    Toast.makeText(MainActivity.getContext(), "The object doesn't exist.", Toast.LENGTH_SHORT).show();
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

    /**
     * Makes a server call to place an order with the input data using a new hospital address.
     * @param URL The url to the appropriate web service.
     */
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
                        Toast.makeText(MainActivity.getContext(), "Order could not be processed.", Toast.LENGTH_SHORT).show();
                        orderSuccess = false;
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.getContext(), "The object doesn't exist.", Toast.LENGTH_SHORT).show();
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

    /**
     * Creates a useful string representation of the current date.
     * @return A useful string representation of the current date.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getDate () {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Turns all the new inputted address data into a single string separated by '$'.
     * @param nameStreetEdited The street name
     * @param streetNumberEdited The building number
     * @param CPEdited The postal code
     * @param cityEdited The city name
     * @return A string containing address data separated by '$'
     */
    private String generateNewAdd(String nameStreetEdited, String streetNumberEdited, String CPEdited, String cityEdited) {
        return nameStreetEdited.toUpperCase() + "$" + streetNumberEdited + "$" + CPEdited + "$" + cityEdited.toUpperCase();
    }

    /**
     * Makes a server call to request the list of accepted materials
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

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.custom_spinner_item,listOfMaterialName);

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

}
