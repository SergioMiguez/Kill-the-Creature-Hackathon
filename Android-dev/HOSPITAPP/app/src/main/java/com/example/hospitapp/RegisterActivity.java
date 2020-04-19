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

/**
 * Creates the Activity that allows the User to create a new User for the Application
 */
public class RegisterActivity extends AppCompatActivity {
    /**
     * EditText used to store the information about the user.
     */
    private EditText nameRegisterInput, hospitalNameInput, adressInput, numberAdressInput, zipCodeInput, cityInput, emailInput, telephoneInput, FpswInput, SpswInput;
    /**
     * Private fields (Strings) used to store all the information that the user provides in the EditTexts.
     */
    private String userName, hospitalName, addressName, numAddress, zipCode, city, email, telephone, Fpsw, Spsw;
    /**
     * Buttons used to register and to go back to the log in.
     */
    private Button registerButton, singinButton;


    /**
     * Private Field used to make a call to the server to make a new Entry and register a new User in the database.
     */
    RequestQueue requestQueue;

    /**
     *  Creates the activity-display used to register a new user.
     *  This activity allows the user to register in the application.
     *  @param savedInstanceState saved data about the current app status used to create activity.
     */
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
        telephoneInput = findViewById(R.id.telephoneRegistered);
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
                telephone = getInfo(telephoneInput);
                Fpsw = getInfo(FpswInput);
                Spsw = getInfo(SpswInput);

                boolean registerNonNull = fullString(userName) &&
                                          fullString(hospitalName) &&
                                          fullString(addressName) &&
                                          fullString(numAddress) &&
                                          fullString(zipCode) &&
                                          fullString(city) &&
                                          fullString(email) &&
                                          fullString(telephone) &&
                                          fullString(Fpsw) &&
                                          fullString(Spsw);

                if (registerNonNull) {
                    if (Fpsw.equals(Spsw)) {
                        makeCallServerAddNewUser(URLS.hospital_registry_url);
                    } else {
                        Toast.makeText(getApplicationContext(), "The passwords are not equal", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Fill in all the fields to continue!", Toast.LENGTH_SHORT).show();
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

    /**
     * Public method used to check if a the string Stored in an EditText (previously) is empty or not.
     * @param text given String
     * @return true if it is not empty and false otherwise.
     */
    public static boolean fullString(String text) {
        String empty = "";
        return !text.trim().equals(empty);
    }

    /**
     * Public method used to get the String Stored in an EditText (introduced by the user).
     * @param text given EditText that is going to be analysed.
     * @return String stored in the given EditText.
     */
    private String getInfo(EditText text) {
        return text.getText().toString().trim();
    }
    /**
     * Private Method that is used when the the button registerButton is pressed.
     * It gives information to the server to create a new Entry for a new User in the database.
     * @param URL given URL to make the server call.
     */
    private void makeCallServerAddNewUser (String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getBoolean("success")){
                        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "User couldn't be created. Possible duplication error.", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("nombre", hospitalName);
                parameters.put("usuario", userName);
                parameters.put("password", Fpsw);
                parameters.put("direccion", toStringAddress(addressName, numAddress, zipCode, city));
                parameters.put("telefono", telephone);
                parameters.put("email", email);

                return parameters;
            }
        };
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /**
     * Private method used to store the Address of the user in the correct format for its later display.
     * @param addressName Street name.
     * @param numAddress Street Number
     * @param zipCode ZipCode of the residence.
     * @param city Name of the city.
     * @return String with the appropriate format.
     */
    private String toStringAddress(String addressName, String numAddress, String zipCode, String city) {
        return addressName.toUpperCase() + "$" + numAddress + "$" + zipCode + "$" + city.toUpperCase();
    }

}