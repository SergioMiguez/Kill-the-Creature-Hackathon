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

public class ListSentAdapter extends RecyclerView.Adapter<ListSentAdapter.OrderViewHolder> {

    /**
     * ArrayList which contains the list of all the orders to be sent which will be displayed
     */
    ArrayList<Order> listOfOrdersSent;

    /**
     * Constructor which sets the list of orders sent which will be displayed
     *
     * @param listOfOrdersSent ArrayList of orders sent to be displayed
     */
    public ListSentAdapter (ArrayList<Order> listOfOrdersSent) {
        this.listOfOrdersSent = listOfOrdersSent;
    }

    /**
     * Static class which is used to set the connections of each individual item to the variable to
     * so that the information can be later on substituted.
     */
    public class OrderViewHolder extends RecyclerView.ViewHolder {

        /**
         * TextView which will be substituted with the name of the object
         */
        private TextView textObject;
        /**
         * TextView which will be substituted with the number of item of the order
         */
        private TextView textVolumeNumber;
        /**
         * TextView which will be substituted with the corresponding id of the provider
         */
        private TextView textProviderId;
        /**
         * TextView which will be substituted with the corresponding state of the order
         */
        private TextView textState;
        /**
         * TextView which will be substituted with the corresponding reference id of the order
         */
        private TextView textReferenceID;
        /**
         * TextView which will be substituted with the date of the order
         */
        private TextView textDate;

        /**
         * Function which links all variables with their elements of the reference item
         *
         * @param itemView gets the corresponding view for the element of the list which is being linked
         */
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textObject = itemView.findViewById(R.id.ObjectNameItem);
            textVolumeNumber = itemView.findViewById(R.id.VolumeNumberItem);
            textDate = itemView.findViewById(R.id.dateNumberItem);
            textState = itemView.findViewById(R.id.StateItem);
            textProviderId = itemView.findViewById(R.id.proveedorNumItem);
            textReferenceID = itemView.findViewById(R.id.ReferenceIdNumItem);
        }

    }

    /**
     * Function which determines the link needed to the reference item xml and creates the needed ListViewHolder
     * to make the Recycler View Holder work. Depending on the state it will use one reference item xml or other one
     *
     * @param parent is a container which contains other views
     * @param i is int indicating an index parameter (do not influence in the onCreateViewHolder)
     * @return the ListViewHolder which will be used later in the list
     */
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_received, null, false);
        return new OrderViewHolder(view);
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
        holder.textObject.setText("" + listOfOrdersSent.get(position).getObject_name());
        holder.textVolumeNumber.setText("" + listOfOrdersSent.get(position).getQuantity());
        holder.textDate.setText(listOfOrdersSent.get(position).getDate());
        holder.textProviderId.setText("" + listOfOrdersSent.get(position).getId_provider());
        holder.textState.setText("COMPLETED");
        holder.textReferenceID.setText("" + listOfOrdersSent.get(position).getId());
    }

    /**
     * Function which returns the number of elements of the ArrayList of Orders
     *
     * @return he number of elements in listOfOrders
     */
    @Override
    public int getItemCount() {
        return listOfOrdersSent.size();
    }

}
