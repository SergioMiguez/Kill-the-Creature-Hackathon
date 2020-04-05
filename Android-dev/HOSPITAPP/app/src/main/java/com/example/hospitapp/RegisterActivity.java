package com.example.hospitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameRegisterInput, hospitalNameInput, adressInput, numberAdressInput, zipCodeInput, cityInput, emailInput, telefoneInput, FpswInput, SpswInput;
    private Button registerButton, singinButton;

    private String userName, hospitalName, addressName, numAddress, zipCode, city, email, telefone, Fpsw, Spsw;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        nameRegisterInput = findViewById(R.id.nameRegistered);
        hospitalNameInput = findViewById(R.id.nameHospitalRegistered);
        adressInput = findViewById(R.id.nameStreetRegistered);
        numberAdressInput = findViewById(R.id.numberStreetRegistered);
        zipCodeInput = findViewById(R.id.CPRegistered);
        cityInput = findViewById(R.id.cityRegistered);
        emailInput = findViewById(R.id.emailRegistered);
        telefoneInput = findViewById(R.id.telephoneRegistered);
        FpswInput = findViewById(R.id.passwordRegistered);
        SpswInput = findViewById(R.id.checkPasswordRegistered);

        registerButton = findViewById(R.id.saveUserBtn);
        singinButton = findViewById(R.id.singInButton);

        userName = getInfo(nameRegisterInput);
        hospitalName = getInfo(hospitalNameInput);
        addressName = getInfo(adressInput);
        numAddress = getInfo(numberAdressInput);
        zipCode = getInfo(zipCodeInput);
        city = getInfo(cityInput);
        email = getInfo(emailInput);
        telefone = getInfo(telefoneInput);
        Fpsw = getInfo(FpswInput);
        Spsw = getInfo(SpswInput);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Objects.requireNonNull(userName);
                Objects.requireNonNull(hospitalName);
                Objects.requireNonNull(addressName);
                Objects.requireNonNull(numAddress);
                Objects.requireNonNull(zipCode);
                Objects.requireNonNull(city);
                Objects.requireNonNull(email);
                Objects.requireNonNull(telefone);
                Objects.requireNonNull(Fpsw);
                Objects.requireNonNull(Spsw);

                if (Fpsw.equals(Spsw)) {
                    makeCallServerAddNewUser("http://URLCOMPLETAR");
                }

            }
        });

        singinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent backToSingIn = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(backToSingIn);
            }
        });

    }

    private String getInfo(EditText text) {
        return text.getText().toString();
    }

    private void makeCallServerAddNewUser (String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("username", userName);
                parameters.put("password", Fpsw);
                parameters.put("hospitalName", hospitalName);
                parameters.put("adressName", toStringAdress(addressName, numAddress,zipCode,city));
                return parameters;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void askServerUsername(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        nameRegisterInput.setText(jsonObject.getString("username"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private String toStringAdress( String addressName, String numAddress, String zipCode, String city) {
        return addressName.toUpperCase() + " " + numAddress + " " + zipCode + " " + city.toUpperCase();
    }

}
