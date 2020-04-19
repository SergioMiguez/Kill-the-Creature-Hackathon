package com.example.hospitapp.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hospitapp.Order;
import com.example.hospitapp.R;

import java.util.ArrayList;

public class ListClassAdapter extends RecyclerView.Adapter<ListClassAdapter.OrderViewHolder> implements View.OnClickListener{

    ArrayList<Order> listOfOrders;
    Context mContext;

    private View.OnClickListener listener;
    private String state = "POR ACEPTAR";
    private OnItemClickListener mListemer;

    public ListClassAdapter(ArrayList<Order> listsOfOrders, String state, Context mContext) {
        this.listOfOrders = listsOfOrders;
        this.state = state;
        this.mContext = mContext;
    }

    public ListClassAdapter(ArrayList<Order> listOfProveedores) {
        this.listOfOrders = listOfProveedores;
    }

    private interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListemer = listener;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView textObject, textVolumeNumber, textProveedorId,
                textState, textReferenceID, textFecha;

        TextView textProveedorName;


        public OrderViewHolder(@NonNull View intemView, final OnItemClickListener listener) {
            super(intemView);


            if (!state.equals("POR ACEPTAR")) {

                textObject = (TextView) itemView.findViewById(R.id.ObjectName);
                textVolumeNumber = (TextView) itemView.findViewById(R.id.VolumeNumber);
                textFecha = (TextView) itemView.findViewById(R.id.fechaNumber);
                textState = (TextView) itemView.findViewById(R.id.State);
                textProveedorId = (TextView) intemView.findViewById(R.id.proveedorNum);
                textReferenceID = (TextView) itemView.findViewById(R.id.ReferenceIdNum);

            } else {
                textProveedorName = (TextView) intemView.findViewById(R.id.ParticularName);
                textProveedorId = (TextView) intemView.findViewById(R.id.identificator);

            }



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

        }

    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        if (!state.equals("POR ACEPTAR")){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        } else {
            view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proveedor_list,null,false);
        }

        OrderViewHolder evh = new OrderViewHolder(view, mListemer);
        return evh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        if (state.equals("POR ACEPTAR")) {
            holder.textProveedorId.setText("" + listOfOrders.get(position).getId_provider());
            holder.textProveedorName.setText("" + listOfOrders.get(position).getObject_name());

        } else {
            if (!listOfOrders.get(position).isSent() && !listOfOrders.get(position).isReceived()) {
                holder.textObject.setText("" + listOfOrders.get(position).getId_object());
                holder.textVolumeNumber.setText("" + listOfOrders.get(position).getQuantity());
                holder.textFecha.setText(listOfOrders.get(position).getDate());
                holder.textProveedorId.setText("" + listOfOrders.get(position).getId_provider());
                holder.textState.setText("LINKED");
                holder.textReferenceID.setText("" +listOfOrders.get(position).getId());
            }


            if (listOfOrders.get(position).isSent() && !listOfOrders.get(position).isReceived()) {
                holder.textObject.setText("" + listOfOrders.get(position).getId_object());
                holder.textVolumeNumber.setText("" + listOfOrders.get(position).getQuantity());
                holder.textFecha.setText(listOfOrders.get(position).getDate());
                holder.textProveedorId.setText("" + listOfOrders.get(position).getId_provider());
                holder.textState.setText("SENT");
                holder.textReferenceID.setText("" + listOfOrders.get(position).getId());
            }


            if (listOfOrders.get(position).isSent() && listOfOrders.get(position).isReceived()) {
                holder.textObject.setText("" + listOfOrders.get(position).getId_object());
                holder.textVolumeNumber.setText("" + listOfOrders.get(position).getQuantity());
                holder.textFecha.setText(listOfOrders.get(position).getDate());
                holder.textProveedorId.setText("" + listOfOrders.get(position).getId_provider());
                holder.textState.setText("RECEIVED");
                holder.textReferenceID.setText("" +listOfOrders.get(position).getId());

            }

        }

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
