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

import com.example.hospitapp.Order;
import com.example.hospitapp.R;
import com.example.hospitapp.ui.ListClassAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    RecyclerView recyclerView;
    ArrayList<Order> listOfOrders;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listOfOrders = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fillList();

        ListClassAdapter adapter = new ListClassAdapter(listOfOrders);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void fillList() {
        listOfOrders.add(new Order("Object","VN","Pending","Reference"));
        listOfOrders.add(new Order("Object","VN","Pending","Reference"));
        listOfOrders.add(new Order("Object","VN","Pending","Reference"));
        listOfOrders.add(new Order("Object","VN","Pending","Reference"));
        listOfOrders.add(new Order("Object","VN","Pending","Reference"));
        listOfOrders.add(new Order("Object","VN","Pending","Reference"));
        listOfOrders.add(new Order("Object","VN","Pending","Reference"));

    }
}
