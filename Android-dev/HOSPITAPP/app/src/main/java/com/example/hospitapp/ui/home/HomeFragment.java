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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitapp.InfoPedidosDialog;
import com.example.hospitapp.Order;
import com.example.hospitapp.R;
import com.example.hospitapp.ui.ListClassAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private final String state = "Pendientes";
    private Context mContext;


    RecyclerView recyclerView;
    ArrayList<Order> listOfOrders;

    RequestQueue requestQueue;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listOfOrders = new ArrayList<>();


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fillList();

        ListClassAdapter adapter = new ListClassAdapter(listOfOrders, state);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInformationPedidoDialog();
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    private void openInformationPedidoDialog(){
        InfoPedidosDialog info = new InfoPedidosDialog();
        info.show(getActivity().getSupportFragmentManager(), "Add Dialog");
    }

    private void fillList() {
        listOfOrders.add(new Order(0,0,1000,20, 0,"5/04/2020", "mi casa"));
        listOfOrders.add(new Order(1,1,20,15, 1, "5/04/2020", "tu casa"));

        makeListRequest("http://URLCALL");

    }


    private void makeListRequest (String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
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
                                order.getString("direccion_envio")
                        ));

                    }

                    ListClassAdapter adapter = new ListClassAdapter(listOfOrders, state);
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
        });

        /*  TODO CHECK IF CONTEXT WORKS  (mContext) */
        if (mContext != null) {
            Volley.newRequestQueue(mContext).add(stringRequest);
        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }


}
