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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameRegisterInput, hospitalNameInput, adressInput, numberAdressInput, zipCodeInput, cityInput, emailInput, telefoneInput, FpswInput, SpswInput;
    private Button registerButton, singinButton;

    private String userName, hospitalName, addressName, numAddress, zipCode, city, email, telefone, Fpsw, Spsw;

    RequestQueue requestQueue;
    private boolean registerSuccess;


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



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                /**
                 * userName = "test10";
                 *                 hospitalName = "test10";
                 *                 addressName = "test10";
                 *                 numAddress = "test10";
                 *                 zipCode = "test10";
                 *                 city = "test10";
                 *                 email = "test10";
                 *                 telefone = "test10";
                 *                 Fpsw = "test10";
                 *                 Spsw = "test10";
                 */

                if (Fpsw.equals(Spsw)) {
                    makeCallServerAddNewUser ("http://192.168.1.86:80/matalbicho/registro_hospitales.php");
                } else {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getBoolean("success")){
                        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                        registerSuccess = true;
                    } else {
                        Toast.makeText(getApplicationContext(), "No se pudo crear el usuario, posible error de duplicacion", Toast.LENGTH_SHORT).show();
                        registerSuccess = false;
                    }

                } catch (Exception e) { // PABLO PLS DON'T KILL ME
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(), "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("nombre", hospitalName);
                parameters.put("usuario", userName);
                parameters.put("password", Fpsw);
                parameters.put("direccion", toStringAddress(addressName, numAddress, zipCode, city));
                parameters.put("telefono", telefone);
                parameters.put("email", email);
                return parameters;
            }
        };
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private String toStringAddress(String addressName, String numAddress, String zipCode, String city) {
        return addressName.toUpperCase() + " " + numAddress + " " + zipCode + " " + city.toUpperCase();
    }

}