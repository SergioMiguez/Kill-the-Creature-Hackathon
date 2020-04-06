package com.example.hospitapp.ui;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.hospitapp.MainActivity;
import com.example.hospitapp.Order;
import com.example.hospitapp.R;

import java.util.ArrayList;

public class ListAdaptor extends RecyclerView.Adapter<ListAdaptor.ListViewHolder> {

    private ArrayList<Order> listOfOrders;
    private OnItemClickListener mListener;
    private String state;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onButtonClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {

        public TextView textObject, textVolumeNumber, textState, textReferenceID, textFecha;
        public Button proveedoresButton;

        public ListViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            textObject = (TextView) itemView.findViewById(R.id.ObjectName);
            textVolumeNumber = (TextView) itemView.findViewById(R.id.VolumeNumber);
            textFecha = (TextView) itemView.findViewById(R.id.fechaNumber);
            textState = (TextView) itemView.findViewById(R.id.State);
            textReferenceID = (TextView) itemView.findViewById(R.id.ReferenceIdNum);

            proveedoresButton = (Button) itemView.findViewById(R.id.proveedoresButton);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onButtonClick(position);
                        }
                    }
                }
            });
            proveedoresButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }

    public ListAdaptor(ArrayList<Order> listOfOrders, String state) {
        this.listOfOrders = listOfOrders;
        this.state = state;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(MainActivity.getContext()).inflate(R.layout.item_list, null, false);
        ListViewHolder evh = new ListViewHolder(v, mListener);
        return evh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Order currentItem = listOfOrders.get(position);

        holder.textObject.setText(currentItem.getNombre_objeto());

        holder.textObject.setText("" + currentItem.getNombre_objeto());
        holder.textVolumeNumber.setText("" + currentItem.getCantidad());
        holder.textFecha.setText(currentItem.getFecha());
        holder.textState.setText(state);
        holder.textReferenceID.setText("" + currentItem.getId());
    }

    @Override
    public int getItemCount() {
        return listOfOrders.size();
    }
}
