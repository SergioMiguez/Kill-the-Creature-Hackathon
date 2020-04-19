package com.example.hospitapp.ui;


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hospitapp.MainActivity;
import com.example.hospitapp.Material;
import com.example.hospitapp.R;

import java.util.ArrayList;

public class ListMaterialsAdaptor extends RecyclerView.Adapter<ListMaterialsAdaptor.OrderViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private ArrayList<Material> listOfMaterials;
    private OnItemClickListener mListemer;



    public ListMaterialsAdaptor(ArrayList<Material> listOfMaterials) {
        this.listOfMaterials = listOfMaterials;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(MainActivity.getContext()).inflate(R.layout.item_material, null, false);

        OrderViewHolder orderViewHolder = new OrderViewHolder(view, mListemer);
        return orderViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.textID.setText("" + listOfMaterials.get(position).getMaterialId());
        holder.textMaterialName.setText(listOfMaterials.get(position).getMaterialName());
    }

    @Override
    public int getItemCount() {
        return listOfMaterials.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    private interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }

    public void setOnItemClickListener(ListMaterialsAdaptor.OnItemClickListener listener) {
        mListemer = listener;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView textID, textMaterialName;

        public OrderViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            textID = itemView.findViewById(R.id.identificator);
            textMaterialName = itemView.findViewById(R.id.materialName);

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
}
