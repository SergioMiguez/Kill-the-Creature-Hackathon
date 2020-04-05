package com.example.hospitapp.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.hospitapp.InfoPedidosDialog;
import com.example.hospitapp.Order;
import com.example.hospitapp.R;
import com.example.hospitapp.ui.ListClassAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

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

        ListClassAdapter adapter = new ListClassAdapter(listOfOrders, "PENDIENTES");

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


        //makeListRequest("http://URLCALL");

    }

    /*
    private List<Order> makeListRequest (String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject order = array.getJSONObject(i);

                        // listOfOrders.add(new Order( order.getInt("id_objeto"), order.getInt("cantidad"), "Pending", order.getInt("id"));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        })
    }

     */
}
