package com.example.provitapp;

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

public class LoginActivity extends AppCompatActivity {

    private Button singInButton;
    private Button registerButton;

    private TextView userNameInput;
    private TextView passwordInput;

    public static String userName;
    private String password;

    private boolean loginSuccess;
    private RequestQueue requestQueue;

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

                makeCall("http://192.168.1.86:80/matalbicho/login.php");

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

    private void makeCall (String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    loginSuccess = !response.isEmpty();

                    if (loginSuccess) {
                        Toast.makeText(getApplicationContext(), "Login Exitoso", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent loadMenu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(loadMenu);
                    } else {
                        Toast.makeText(getApplicationContext(), "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();

                /**         TODO ELIMINATE AT THE END           */
                if (userName.equals("email") && (password.equals("pass"))) {
                    Toast.makeText(getApplicationContext(), "Login Exitoso", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent loadMenu = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loadMenu);
                }
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
