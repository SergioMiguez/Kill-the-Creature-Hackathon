package com.example.hospitapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hospitapp.Order;
import com.example.hospitapp.R;

import java.util.ArrayList;

public class ListClassAdapter extends RecyclerView.Adapter<ListClassAdapter.OrderViewHolder>{

    ArrayList<Order> listOfOrders;

    public ListClassAdapter(ArrayList<Order> listsOfOrders) {
        this.listOfOrders = listsOfOrders;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView textObject, textVolumeNumber,
                textState, textReferenceID;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textObject = (TextView) itemView.findViewById(R.id.ObjectName);
            textVolumeNumber = (TextView) itemView.findViewById(R.id.VolumeNumber);
            textState = (TextView) itemView.findViewById(R.id.State);
            textReferenceID = (TextView) itemView.findViewById(R.id.ReferenceId);
        }

    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.textObject.setText(listOfOrders.get(position).getObject());
        holder.textVolumeNumber.setText(listOfOrders.get(position).getVolumeNumber());
        holder.textState.setText(listOfOrders.get(position).getState());
        holder.textReferenceID.setText(listOfOrders.get(position).getReferenceID());
    }

    @Override
    public int getItemCount() {
        return listOfOrders.size();
    }




}
