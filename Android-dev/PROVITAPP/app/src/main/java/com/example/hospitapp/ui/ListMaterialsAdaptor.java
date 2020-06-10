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

/**
 * ListMaterialsAdaptor Class which set the Recycle View configuration for the MaterialsDialog
 * Lists. It used a item as a model which is used repeatedly in which the
 * information is substituted as a way of improving performance in the creation of list
 */
public class ListMaterialsAdaptor extends RecyclerView.Adapter<ListMaterialsAdaptor.OrderViewHolder> {

    /**
     * ArrayList which contains the list of all the materials which will be displayed
     */
    private ArrayList<Material> listOfMaterials;

    /**
     * Constructor which sets the list of materials which will be displayed
     *
     * @param listOfMaterials ArrayList of materials to be displayed
     */
    public ListMaterialsAdaptor(ArrayList<Material> listOfMaterials) {
        this.listOfMaterials = listOfMaterials;
    }

    /**
     * Function which determines the link needed to the reference item xml and creates the needed ListViewHolder
     * to make the Recycler View Holder work. Depending on the state it will use one reference item xml or other one
     *
     * @param viewGroup is a container which contains other views (do not influence in the onCreateViewHolder)
     * @param i is int indicating an index parameter (do not influence in the onCreateViewHolder)
     * @return the ListViewHolder which will be used later in the list
     */
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(MainActivity.getContext()).inflate(R.layout.item_material, null, false);

        OrderViewHolder orderViewHolder = new OrderViewHolder(view);
        return orderViewHolder;
    }

    /**
     * Function where the information is replaced in the item model with the information received from the server's database
     *
     * @param holder gets the children view which is used to connect the elements to the reference objects
     * @param position indicates the index in the ArrayList of order to get the information
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.textID.setText("" + listOfMaterials.get(position).getMaterialId());
        holder.textMaterialName.setText(listOfMaterials.get(position).getMaterialName());
    }

    /**
     * Static class which is used to set the connections of each individual item to the variable to
     * so that the information can be later on substituted.
     */
    public class OrderViewHolder extends RecyclerView.ViewHolder {

        /**
         * TextView which will be substituted with the Id information of the material
         */
        TextView textID;
        /**
         * TextView which will be substituted with the name of the material
         */
        TextView textMaterialName;

        /**
         * Function which links all variables with their elements of the reference item
         *
         * @param itemView gets the corresponding view for the element of the list which is being linked
         */
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            textID = itemView.findViewById(R.id.identificator);
            textMaterialName = itemView.findViewById(R.id.materialName);

        }
    }
    /**
     * Function which returns the number of elements of the ArrayList of Orders
     *
     * @return he number of elements in listOfOrders
     */
    @Override
    public int getItemCount() {
        return listOfMaterials.size();
    }

}
