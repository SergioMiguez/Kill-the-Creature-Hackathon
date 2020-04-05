package com.example.hospitapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.example.hospitapp.Order;
import com.example.hospitapp.R;

import java.util.ArrayList;

public class ListClassAdapter extends RecyclerView.Adapter<ListClassAdapter.OrderViewHolder> implements View.OnClickListener{

    ArrayList<Order> listOfOrders;
    Dialog proveedoresDialog;
    Context mContext;

    private View.OnClickListener listener;
    private String state;

    public ListClassAdapter(ArrayList<Order> listsOfOrders, String state, Context mContext) {
        this.listOfOrders = listsOfOrders;
        this.state = state;
        this.mContext = mContext;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout order_item;

        TextView textObject, textVolumeNumber,
                textState, textReferenceID, textFecha;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            order_item = (RelativeLayout) itemView.findViewById(R.id.order_item);

            textObject = (TextView) itemView.findViewById(R.id.ObjectName);
            textVolumeNumber = (TextView) itemView.findViewById(R.id.VolumeNumber);
            textFecha = (TextView) itemView.findViewById(R.id.fechaNumber);
            textState = (TextView) itemView.findViewById(R.id.State);
            textReferenceID = (TextView) itemView.findViewById(R.id.ReferenceIdNum);
        }

    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        final OrderViewHolder viewHolder = new OrderViewHolder(view);

        viewHolder.order_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Test Click " + String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();

            }
        });

        view.setOnClickListener(this);

        return new OrderViewHolder(view);
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
