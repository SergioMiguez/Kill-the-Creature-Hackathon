package com.example.hospitapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hospitapp.Proveedor;
import com.example.hospitapp.R;

import java.util.ArrayList;

public class ListProveedoresClass extends RecyclerView.Adapter<ListProveedoresClass.OrderViewHolder> implements View.OnClickListener {

    ArrayList<Proveedor> listOfProveedores;
    private View.OnClickListener listener;

    public ListProveedoresClass(ArrayList<Proveedor> listOfProveedores) {
        this.listOfProveedores = listOfProveedores;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textBusinessName,
                textState, textEmail;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.ParticularName);
            textBusinessName = (TextView) itemView.findViewById(R.id.EnterpriseName);
            textEmail = (TextView) itemView.findViewById(R.id.ProveedorEmail);
        }

    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proveedor_list, null, false);

        view.setOnClickListener(this);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.textName.setText(listOfProveedores.get(position).getName());
        holder.textBusinessName.setText(listOfProveedores.get(position).getNameBusiness());
        holder.textEmail.setText(listOfProveedores.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return listOfProveedores.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }
}
