package com.example.hospitapp.ui.notifications;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
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
import com.example.hospitapp.LoginActivity;
import com.example.hospitapp.MainActivity;
import com.example.hospitapp.R;
import com.example.hospitapp.URLS;
import com.example.hospitapp.UsuarioHospital;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NotificationsFragment extends Fragment {

    private Button logOutButton;
    private RequestQueue requestInfoQueue;
    private RequestQueue requestEditQueue;
    private boolean editSuccess;

    //EditText nameEdited;
    EditText nameHospitalEdited;
    EditText nameStreetEdited;
    EditText streetNumberEdited;
    EditText CPEdited;
    EditText cityEdited;
    EditText emailEdited;
    EditText telephoneEdited;

    TextView nameAdded;
    TextView nameHospitalAdded;
    TextView nameStreetAdded;
    TextView streetNumberAdded;
    TextView CPAdded;
    TextView cityAdded;
    TextView emailAdded;
    TextView telephoneAdded;

    String defName;
    String defHospital;
    String defStreet;
    String defNumber;
    String defCP;
    String defCity;
    String defEmail;
    String defTelephone;

    private String edit_hospital;
    private String edit_street;
    private String edit_street_number;
    private String edit_CP;
    private String edit_city;
    private String edit_email;
    private String edit_telephone;


    SharedPreferences myPrefs;

    String def = "ERROR";

    String serverURL = URLS.profile_url;
    String editUrl  = URLS.update_profile_url;

    UsuarioHospital userHospital;

    private Context mContext;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        nameAdded = root.findViewById(R.id.nameAdded);
        nameHospitalAdded = root.findViewById(R.id.nameHospitalAdded);
        nameStreetAdded = root.findViewById(R.id.nameStreetAdded);
        streetNumberAdded = root.findViewById(R.id.streetNumberAdded);
        CPAdded = root.findViewById(R.id.CPAdded);
        cityAdded = root.findViewById(R.id.cityAdded);
        emailAdded = root.findViewById(R.id.emailAdded);
        telephoneAdded = root.findViewById(R.id.telephoneAdded);

        makeUserRequest(serverURL);

        Button saveUserBtn = root.findViewById(R.id.saveUserBtn);
        saveUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //nameEdited = root.findViewById(R.id.nameEdited);
                nameHospitalEdited = root.findViewById(R.id.nameHospitalEdited);
                nameStreetEdited = root.findViewById(R.id.nameStreetEdited);
                streetNumberEdited = root.findViewById(R.id.streetNumberEdited);
                CPEdited = root.findViewById(R.id.CPEdited);
                cityEdited = root.findViewById(R.id.cityEdited);
                emailEdited = root.findViewById(R.id.emailEdited);
                telephoneEdited = root.findViewById(R.id.telephoneEdited);

                //String nameEditedStr = nameEdited.getText().toString();
                String nameHospitalEditedStr = nameHospitalEdited.getText().toString();
                String nameStreetEditedStr = nameStreetEdited.getText().toString();
                String streetNumberEditedStr = streetNumberEdited.getText().toString();
                String CPEditedStr = CPEdited.getText().toString();
                String cityEditedStr = cityEdited.getText().toString();
                String emailEditedStr = emailEdited.getText().toString();
                String telephoneEditedStr = telephoneEdited.getText().toString();

                myPrefs = getActivity().getSharedPreferences("prefID", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();

                //editor.putString("name", nameEditedStr);
                editor.putString("hospital", nameHospitalEditedStr);
                editor.putString("street", nameStreetEditedStr);
                editor.putString("street_number", streetNumberEditedStr);
                editor.putString("CP", CPEditedStr);
                editor.putString("city", cityEditedStr);
                editor.putString("email", emailEditedStr);
                editor.putString("telephone", telephoneEditedStr);
                editor.apply();

                //String name = myPrefs.getString("name", def);
                edit_hospital = myPrefs.getString("hospital", defHospital);
                edit_street = myPrefs.getString("street", defStreet);
                edit_street_number = myPrefs.getString("street_number", defNumber);
                edit_CP = myPrefs.getString("CP", defCP);
                edit_city = myPrefs.getString("city", defCity);
                edit_email = myPrefs.getString("email", defEmail);
                edit_telephone = myPrefs.getString("telephone", defTelephone);

                boolean allFilled = fullEdit(nameHospitalEdited) && fullEdit(nameStreetEdited) &&
                        fullEdit(emailEdited) && fullEdit(telephoneEdited); // && fullEdit(nameEdited);

                if (allFilled) {
                    /**
                     * //nameAdded.setText(name);
                     *                     nameHospitalAdded.setText(edit_hospital);
                     *                     nameStreetAdded.setText(edit_street);
                     *                     streetNumberAdded.setText(edit_street_number);
                     *                     CPAdded.setText(edit_CP);
                     *                     cityAdded.setText(edit_city);
                     *                     emailAdded.setText(edit_email);
                     *                     telephoneAdded.setText(edit_telephone);
                     */

                    //nameEdited.getText().clear();

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    String infoTitle = "Are you sure?";
                    String infoMessage = "Once changes have been made to user information, this cannot be reversed. \n\"Continue anyways?\"";

                    builder.setTitle(infoTitle)
                            .setMessage(infoMessage)
                            .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getContext(), "Changes Accepted", Toast.LENGTH_SHORT).show();
                                    nameHospitalEdited.getText().clear();
                                    nameStreetEdited.getText().clear();
                                    streetNumberEdited.getText().clear();
                                    CPEdited.getText().clear();
                                    cityEdited.getText().clear();
                                    emailEdited.getText().clear();
                                    telephoneEdited.getText().clear();
                                    makeEditRequest(editUrl);
                                }
                            })
                            .setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getContext(), "Changes Declined", Toast.LENGTH_SHORT).show();
                                }
                            });
                    builder.show();

                    //Toast.makeText(getContext(), "Datos de perfil actualizados correctamente!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Fill in all the fields to continue!", Toast.LENGTH_LONG).show();
                }
            }
        });

        logOutButton = root.findViewById(R.id.logoutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.userName = null;
                Intent logInActivity = new Intent(getContext(), LoginActivity.class);
                startActivity(logInActivity);
            }
        });

        return root;
    }

    public static boolean fullEdit(EditText editText) {
        String empty = "";
        return !editText.getText().toString().trim().equals(empty);
    }

    private void makeUserRequest(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(MainActivity.getContext(), response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray array = new JSONArray(response);

                    JSONObject user = array.getJSONObject(0);


                    String[] directionInfo = user.getString("direccion").split("\\Q$\\E");

                    userHospital = new UsuarioHospital(
                            user.getInt("id"),
                            user.getString("nombre"),
                            user.getString("usuario"),
                            directionInfo[0], //street name
                            user.getString("email"),
                            user.getString("telefono"),
                            directionInfo[1], //street number
                            directionInfo[2], //zip code
                            directionInfo[3] //city/province
                    );

                    defName = userHospital.getUsuario();
                    defHospital = userHospital.getNombre();
                    defStreet = userHospital.getDireccion();
                    defNumber = userHospital.getNumberAddress();
                    defCP = userHospital.getZipCode();
                    defCity = userHospital.getCity();
                    defEmail = userHospital.getEmail();
                    defTelephone = userHospital.getTelefono();


                    myPrefs = getActivity().getSharedPreferences("prefID", Context.MODE_PRIVATE);

                    nameAdded.setText(defName); //TODO change to defname
                    nameHospitalAdded.setText(defHospital);
                    nameStreetAdded.setText(defStreet);
                    streetNumberAdded.setText(defNumber);
                    CPAdded.setText(defCP);
                    cityAdded.setText(defCity);
                    emailAdded.setText(defEmail);
                    telephoneAdded.setText(defTelephone);


                } catch (JSONException e) {
                    Toast.makeText(MainActivity.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mContext != null) {
                    Toast.makeText(mContext, "ERROR USER", Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            // not used yet...
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("usuario", LoginActivity.userName);
                return parameters;
            }
        };


        /**
         * if (mContext != null) {
         *             Volley.newRequestQueue(mContext).add(stringRequest);
         *         }
         */

        requestInfoQueue= Volley.newRequestQueue(getContext());
        requestInfoQueue.add(stringRequest);

    }

    private void makeEditRequest (String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(MainActivity.getContext(), response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getBoolean("success")){
                        Toast.makeText(MainActivity.getContext(), "Success!", Toast.LENGTH_SHORT).show();
                        editSuccess = true;
                    } else {
                        Toast.makeText(MainActivity.getContext(), "No se pudo editar el usuario. Posible error de duplicidad", Toast.LENGTH_SHORT).show();
                        editSuccess = false;
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.getContext(), "Estes es el catch", Toast.LENGTH_SHORT).show();
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
                parameters.put("nombre", edit_hospital);
                parameters.put("usuario", LoginActivity.userName);
                parameters.put("direccion", toStringAddress(edit_street, edit_street_number, edit_CP, edit_city));
                parameters.put("telefono", edit_telephone);
                parameters.put("email", edit_email);
                return parameters;
            }
        };
        requestEditQueue=Volley.newRequestQueue(getContext());
        requestEditQueue.add(stringRequest);
    }

    private String toStringAddress(String addressName, String numAddress, String zipCode, String city) {
        return addressName.toUpperCase() + "$" + numAddress + "$" + zipCode + "$" + city.toUpperCase();
    }

    private void logOut() {

    }
}
            
