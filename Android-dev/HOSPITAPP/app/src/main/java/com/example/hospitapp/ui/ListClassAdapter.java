package com.example.hospitapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.example.hospitapp.Order;
import com.example.hospitapp.R;

import java.util.ArrayList;

public class ListClassAdapter extends RecyclerView.Adapter<ListClassAdapter.OrderViewHolder> implements View.OnClickListener{

    ArrayList<Order> listOfOrders;
    Context mContext;

    private View.OnClickListener listener;
    private String state;
    private OnItemClickListener mListemer;

    public ListClassAdapter(ArrayList<Order> listsOfOrders, String state, Context mContext) {
        this.listOfOrders = listsOfOrders;
        this.state = state;
        this.mContext = mContext;
    }

    private interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListemer = listener;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView textObject, textVolumeNumber,
                textState, textReferenceID, textFecha;

        Button proveedoresButton;


        public OrderViewHolder(@NonNull View intemView, final OnItemClickListener listener) {
            super(intemView);

            textObject = (TextView) itemView.findViewById(R.id.ObjectName);
            textVolumeNumber = (TextView) itemView.findViewById(R.id.VolumeNumber);
            textFecha = (TextView) itemView.findViewById(R.id.fechaNumber);
            textState = (TextView) itemView.findViewById(R.id.State);
            textReferenceID = (TextView) itemView.findViewById(R.id.ReferenceIdNum);

            proveedoresButton = intemView.findViewById(R.id.proveedoresButton);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            proveedoresButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        OrderViewHolder evh = new OrderViewHolder(view, mListemer);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.textObject.setText("" + listOfOrders.get(position).getId_objeto());
        holder.textVolumeNumber.setText("" + listOfOrders.get(position).getCantidad());
        holder.textFecha.setText(listOfOrders.get(position).getFecha());
        holder.textState.setText(state);
        holder.textReferenceID.setText("" +listOfOrders.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return listOfOrders.size();
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }




}
