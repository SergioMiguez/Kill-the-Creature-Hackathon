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

/** Class that is responsible for the handling of the profile tab*/
public class NotificationsFragment extends Fragment {

    /** The log out button*/
    private Button logOutButton;
    /** The call tot the server */
    private RequestQueue requestInfoQueue;
    /** The call tot the server */
    private RequestQueue requestEditQueue;
    /** Boolean that represents the success of the operation*/
    private boolean editSuccess;

    //EditText nameEdited;
    /** EditText parameter that is responsible for the hospital name input*/
    EditText nameHospitalEdited;
    /** EditText parameter that is responsible for the street name input*/
    EditText nameStreetEdited;
    /** EditText parameter that is responsible for the street number input*/
    EditText streetNumberEdited;
    /** EditText parameter that is responsible for the postal code input*/
    EditText CPEdited;
    /** EditText parameter that is responsible for the city input*/
    EditText cityEdited;
    /** EditText parameter that is responsible for the email input*/
    EditText emailEdited;
    /** EditText parameter that is responsible for the telephone input*/
    EditText telephoneEdited;

    /** TextView parameter that represents the name text*/
    TextView nameAdded;
    /** TextView parameter that represents the hospital name text*/
    TextView nameHospitalAdded;
    /** TextView parameter that represents the street name text*/
    TextView nameStreetAdded;
    /** TextView parameter that represents the street number text*/
    TextView streetNumberAdded;
    /** TextView parameter that represents the postal code text*/
    TextView CPAdded;
    /** TextView parameter that represents the city text*/
    TextView cityAdded;
    /** TextView parameter that represents the email text*/
    TextView emailAdded;
    /** TextView parameter that represents the telephone text*/
    TextView telephoneAdded;

    /** String that defines the name*/
    String defName;
    /** String that defines the hospital*/
    String defHospital;
    /** String that defines the street*/
    String defStreet;
    /** String that defines the number*/
    String defNumber;
    /** String that defines the postal code*/
    String defCP;
    /** String that defines the city*/
    String defCity;
    /** String that defines the email*/
    String defEmail;
    /** String that defines the telephone*/
    String defTelephone;

    /** String that represents the hospital edited*/
    private String edit_hospital;
    /** String that represents the street edited*/
    private String edit_street;
    /** String that represents the street number edited*/
    private String edit_street_number;
    /** String that represents the postal code edited*/
    private String edit_CP;
    /** String that represents the city edited*/
    private String edit_city;
    /** String that represents the email edited*/
    private String edit_email;
    /** String that represents the telephone edited*/
    private String edit_telephone;


    /** Object of the class SharedPreferences*/
    SharedPreferences myPrefs;

    /** The URL of the server*/
    String serverURL = URLS.profile_url;
    /** The URL edited*/
    String editUrl  = URLS.update_profile_url;

    /** Object of the class userHospital*/
    UsuarioHospital userHospital;

    /** Stores the current status of the app */
    private Context mContext;


    /**
     * Creates the display of orders connected with a producer.
     * @param inflater object used to decompress other views.
     * @param container the outside container which holds the display of orders.
     * @param savedInstanceState saved data about the current app status used to create the window.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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

    /**
     * Boolean that determines whether a editText is empty or not
     * @param editText EditText that is being analyzed
     * @return true if it is empty, false otherwise.
     */
    public static boolean fullEdit(EditText editText) {
        String empty = "";
        return !editText.getText().toString().trim().equals(empty);
    }

    /**
     * Makes a server call to obtain the information of the user.
     * @param URL The url to the appropriate web service.
     */
    private void makeUserRequest(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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

                    defName = userHospital.getUsername();
                    defHospital = userHospital.getName();
                    defStreet = userHospital.getDirection();
                    defNumber = userHospital.getNumberAddress();
                    defCP = userHospital.getZipCode();
                    defCity = userHospital.getCity();
                    defEmail = userHospital.getEmail();
                    defTelephone = userHospital.getPhoneNumber();


                    myPrefs = getActivity().getSharedPreferences("prefID", Context.MODE_PRIVATE);

                    nameAdded.setText(defName);
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

        requestInfoQueue= Volley.newRequestQueue(getContext());
        requestInfoQueue.add(stringRequest);

    }

    /**
     * Makes a server call to request the profile info
     * @param URL The url to the appropriate web service.
     */
    private void makeEditRequest (String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getBoolean("success")){
                        Toast.makeText(MainActivity.getContext(), "Success!", Toast.LENGTH_SHORT).show();
                        editSuccess = true;
                    } else {
                        Toast.makeText(MainActivity.getContext(), "Could not edit the user. Possible duplicity error", Toast.LENGTH_SHORT).show();
                        editSuccess = false;
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.getContext(), "This is the catch", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
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

    /**
     * Method that sets the address to a single string
     * @param addressName the address name
     * @param numAddress the address number
     * @param zipCode the zip code string
     * @param city the city string
     * @return the address as a single string
     */
    private String toStringAddress(String addressName, String numAddress, String zipCode, String city) {
        return addressName.toUpperCase() + "$" + numAddress + "$" + zipCode + "$" + city.toUpperCase();
    }

}
            
