package com.example.hospitapp.ui;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hospitapp.Order;
import com.example.hospitapp.R;

import java.util.ArrayList;

public class ListSentAdapter extends RecyclerView.Adapter<ListSentAdapter.OrderViewHolder> implements View.OnClickListener {

    ArrayList<Order> listOfOrdersSent;
    private View.OnClickListener listener;
    private OnItemClickListener mListemer;


    public ListSentAdapter (ArrayList<Order> listOfOrdersSent) {
        this.listOfOrdersSent = listOfOrdersSent;
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

        public OrderViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textObject = (TextView) itemView.findViewById(R.id.ObjectName);
            textVolumeNumber = (TextView) itemView.findViewById(R.id.VolumeNumber);
            textFecha = (TextView) itemView.findViewById(R.id.fechaNumber);
            textState = (TextView) itemView.findViewById(R.id.State);
            textProveedorId = (TextView) itemView.findViewById(R.id.proveedorNum);
            textReferenceID = (TextView) itemView.findViewById(R.id.ReferenceIdNum);

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new OrderViewHolder(view, mListemer);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.textObject.setText("" + listOfOrdersSent.get(position).getId_objeto());
        holder.textVolumeNumber.setText("" + listOfOrdersSent.get(position).getCantidad());
        holder.textFecha.setText(listOfOrdersSent.get(position).getFecha());
        holder.textProveedorId.setText("" + listOfOrdersSent.get(position).getId_proveedor());
        holder.textState.setText("LINKED");
        holder.textReferenceID.setText("" + listOfOrdersSent.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return listOfOrdersSent.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }
}
