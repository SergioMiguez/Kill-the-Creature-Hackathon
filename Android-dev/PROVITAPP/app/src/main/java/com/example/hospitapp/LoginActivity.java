package com.example.hospitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Class that is responsible for handling the login activity.*/
public class LoginActivity extends AppCompatActivity {

    /** Field that represents the sign in button*/
    private Button singInButton;
    /** Field that represents the register button*/
    private Button registerButton;

    /** Field that represents the username text*/
    private TextView userNameInput;
    /** Field that represents the password text*/
    private TextView passwordInput;

    /** Field that represents the username string*/
    public static String userName;
    /** Field that represents the password string*/
    private String password;

    /** Boolean that represents whether the login was successful or not*/
    private boolean loginSuccess;
    /** Object of the class RequestQueue*/
    private RequestQueue requestQueue;

    /**
     * Creates the window use to display useful information about how to use the app.
     * It informs about the type of data displayed in each window as well as the functionality of each button.
     * @param savedInstanceState saved data about the current app status used to create the window.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        singInButton = findViewById(R.id.singInButton);
        registerButton = findViewById(R.id.registerButton);

        userNameInput = findViewById(R.id.inputUsuario);
        passwordInput = findViewById(R.id.inputPassword);

        singInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Objects.requireNonNull(userNameInput.getText().toString());
                Objects.requireNonNull(passwordInput.getText().toString());

                userName = userNameInput.getText().toString();
                password = passwordInput.getText().toString();

                makeCall(URLS.provider_login_url);

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent loadRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(loadRegister);
            }
        });

    }

    /**
     * Makes a server call to place an order with the input data using the login data.
     * @param URL The url to the appropriate web service.
     */
    private void makeCall (String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    loginSuccess = !response.isEmpty();

                    if (loginSuccess) {
                        Toast.makeText(getApplicationContext(), "Successful Login", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent loadMenu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(loadMenu);
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("usuario", userName);
                parameters.put("password", password);
                return parameters;
            }
        };
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
