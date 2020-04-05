package com.example.hospitapp.ui.dashboard;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;

import com.example.hospitapp.AddDialog;
import com.example.hospitapp.Order;
import com.example.hospitapp.R;
import com.example.hospitapp.ui.ListClassAdapter;


import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private ArrayList<Order> listOfOrders;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        listOfOrders = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerDashboard);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fillList();

        ListClassAdapter adapter = new ListClassAdapter(listOfOrders, "COMPLETADOS");


        recyclerView.setAdapter(adapter);

        return view;
    }



    private void fillList() {
        listOfOrders.add(new Order(0,0,1000,20, 0,"5/04/2020", "mi casa"));
        listOfOrders.add(new Order(1,1,20,15, 1, "5/04/2020", "tu casa"));


    }
}
