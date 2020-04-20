package com.example.hospitapp.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitapp.LoginActivity;
import com.example.hospitapp.Order;
import com.example.hospitapp.R;
import com.example.hospitapp.ReceivedDialog;
import com.example.hospitapp.URLS;
import com.example.hospitapp.ui.ListClassAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/** Creates the display of orders connected with a producer */
public class DashboardFragment extends Fragment {

    /** The list of orders connected with a producer */
    private ArrayList<Order> listOfOrders;
    /** The display used to show the list of orders connected with a producer */
    private RecyclerView recyclerView;
    /** The constant used to indicate that an order has been linked with a producer */
    private final String stateLinked = "LINKED";
    /** The constant used to indicate that an order has been received by the hospital */
    private final String stateCompleted = "RECEIVED";
    /** Stores the current status of the app */
    private Context mContext;
    /** Button used to mark a particular order as received. */
    private Button receivedButton;
    /** The call to the server */
    private RequestQueue requestQueue;

    /**
     * Creates the display of orders connected with a producer.
     * @param inflater object used to decompress other views.
     * @param container the outside container which holds the display of orders.
     * @param savedInstanceState saved data about the current app status used to create the window.
     * @return the display of orders connected with a producer.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        listOfOrders = new ArrayList<>();

        receivedButton = view.findViewById(R.id.receivedButton);
        openReceived();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerDashboard);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fillList();

        ListClassAdapter adapter = new ListClassAdapter(listOfOrders, stateLinked, mContext);

        recyclerView.setAdapter(adapter);

        return view;
    }

    /**
     * Opens the dialogue used to mark an Order as received when the 'Received' button is pressed.
     */
    private void openReceived() {
        receivedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReceivedDialog newDialog = new ReceivedDialog();
                newDialog.show(getActivity().getSupportFragmentManager(), "Mark as received");
            }
        });
    }

    /**
     * Creates a link between the fragment and the context passed.
     * @param context the context of the app.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    /**
     * Destroys the link between the fragment and the context.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    /**
     * Calls the server to obtain the necessary data used to fill the list of linked orders.
     */
    private void fillList() {
        makeListRequest(URLS.display_connected_orders_url);
    }


    /**
     * Makes a server call to obtain the orders placed by the hospital that have been connected with a producer.
     * @param URL The url to the appropriate web service.
     */
    private void makeListRequest (String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject order = array.getJSONObject(i);

                        listOfOrders.add(new Order(
                                order.getInt("id"),
                                order.getInt("id_objeto"),
                                order.getInt("cantidad"),
                                order.getInt("id_proveedor"),
                                order.getInt("id_hospital"),
                                order.getString("fecha"),
                                order.getString("direccion_envio"),
                                order.getString("nombre_objeto"),
                                order.getInt("enviado"),
                                order.getInt("recibido")

                        ));

                    }

                    ListClassAdapter adapter = new ListClassAdapter(listOfOrders, stateLinked, mContext);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mContext != null) {
                    Toast.makeText(mContext, "Unable to connect to the database", Toast.LENGTH_SHORT).show();
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


        /**
         * if (mContext != null) {
         *             Volley.newRequestQueue(mContext).add(stringRequest);
         *         }
         */

        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
