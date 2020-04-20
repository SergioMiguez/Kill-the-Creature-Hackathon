package com.example.hospitapp.ui.home;

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
import com.example.hospitapp.FilterDialog;
import com.example.hospitapp.LoginActivity;
import com.example.hospitapp.Order;
import com.example.hospitapp.R;
import com.example.hospitapp.URLS;
import com.example.hospitapp.ui.ListAdaptor;
import com.example.hospitapp.ui.ListClassAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {


    /**
     * Private String used to store the state of the orders
     */
    private final String state = "Pendientes";
    /**
     * Used to get the situation of the app to display the messages.
     */
    private Context mContext;
    /**
     * RecyclerView used to store the not linked orders of a user.
     */
    private RecyclerView recyclerView;
    /**
     * RecyclerView used to store the not linked orders of a user.
     */
    private ArrayList<Order> listOfOrders;
    /**
     * Button used to open the filterDialog.
     */
    private Button filterButton;

    /**
     * Request to the server to get the list of all the not-linked orders of the user.
     */
    RequestQueue requestQueue;

    /**
     * Creates the display and functionality of the Fragment using all the needed information.
     * It allows the user to see the list of not-linked orders.
     * @param inflater object used to decompress other views.
     * @param container the outside container which holds the display of orders.
     * @param savedInstanceState saved data about the current app status used to create the window.
     * @return the display of the fragment.
     */
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listOfOrders = new ArrayList<>();


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fillList();

        ListClassAdapter adapter = new ListClassAdapter(listOfOrders, state, mContext);

        recyclerView.setAdapter(adapter);

        filterButton = view.findViewById(R.id.filterButton);
        openFilter();

        return view;
    }


    /**
     * Method used to call the server to get the list of the not linked-orders.
     */
    private void fillList() {
        makeListRequest(URLS.display_all_orders_url);
    }

    /**
     * Method that is used to make the actual call to the server, it adds all the collected orders to the ArrayList listOfOrders, and it allows the display of the recyclerView.
     * @param URL given URL to make the server call.
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
                                order.getString("nombre_objeto")
                        ));

                    }

                    ListAdaptor adapter = new ListAdaptor(listOfOrders, state);

                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mContext != null) {
                    Toast.makeText(mContext, "ERROR HOME", Toast.LENGTH_SHORT).show();
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

        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    /**
     * Method used to create a link between the context and the fragment
     * @param context given context of the activity.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    /**
     * Method used to destroy the link between the fragment and the context
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }
    /**
     * Opens the FilterDialog through the button FilterButton.
     */
    public void openFilter() {
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterDialog newDialog = new FilterDialog();
                newDialog.show(getActivity().getSupportFragmentManager(), "Filter");
            }
        });
    }


}
