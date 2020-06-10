package com.example.hospitapp.ui;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.hospitapp.MainActivity;
import com.example.hospitapp.Order;
import com.example.hospitapp.R;
import java.util.ArrayList;

/**
 * ListAdaptor Class which set the Recycle View configuration for the HomeFragment List
 * It used a item as a model which is used repeatedly in which the information is substituted as a
 * way of improving performance in the creation of list
 */
public class ListAdaptor extends RecyclerView.Adapter<ListAdaptor.ListViewHolder> {

    /**
     * ArrayList which contains the list of all the orders which will be displayed
     */
    private ArrayList<Order> listOfOrders;
    /**
     * String which contains the state that the order has
     */
    private String state;

    /**
     * Constructor of the class ListAdaptor. It initialises the parameters which will be needed to
     * make the class work.
     *
     * @param listOfOrders a possible existing array of Orders
     * @param state and the sate that the orders have as a group
     */
    public ListAdaptor(ArrayList<Order> listOfOrders, String state) {
        this.listOfOrders = listOfOrders;
        this.state = state;
    }

    /**
     * Static class which is used to set the connections of each individual item to the variable to
     * so that the information can be later on substituted.
     */
    public static class ListViewHolder extends RecyclerView.ViewHolder {

        /**
         * TextView which will be substituted with the name of the object that will be displayed
         */
        public TextView textObject;
        /**
         * TextView which will be substituted with the number of units requested in the order
         */
        public TextView textVolumeNumber;
        /**
         * TextView which will be substituted with the state of the order
         */
        public TextView textState;
        /**
         * TextView which will be substituted with the reference id of the order
         */
        public TextView textReferenceID;
        /**
         * TextView which will be substituted with the date of the order
         */
        public TextView textDate;

        /**
         * Function which links all variables with their elements of the reference item
         *
         * @param itemView gets the corresponding view for the element of the list which is being linked
         */
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            textObject = itemView.findViewById(R.id.ObjectName);
            textVolumeNumber = itemView.findViewById(R.id.VolumeNumber);
            textDate = itemView.findViewById(R.id.dateNumber);
            textState = itemView.findViewById(R.id.State);
            textReferenceID = itemView.findViewById(R.id.ReferenceIdNum);
        }

    }

    /**
     * Function which determines the link needed to the reference item xml and creates the needed ListViewHolder
     * to make the Recycler View Holder work
     *
     * @param viewGroup is a container which contains other views (do not influence in the onCreateViewHolder)
     * @param i is int indicating an index parameter (do not influence in the onCreateViewHolder)
     * @return the ListViewHolder which will be used later in the list
     */
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(MainActivity.getContext()).inflate(R.layout.item_list, null, false);
        ListViewHolder evh = new ListViewHolder(v);
        return evh;
    }

    /**
     * Function where the information is replaced in the item model with the information received from the server's database
     *
     * @param holder gets the children view which is used to connect the elements to the reference objects
     * @param position indicates the index in the ArrayList of order to get the information
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        Order currentItem = listOfOrders.get(position);

        holder.textObject.setText(currentItem.getObject_name());
        holder.textObject.setText("" + currentItem.getObject_name());
        holder.textVolumeNumber.setText("" + currentItem.getQuantity());
        holder.textDate.setText(currentItem.getDate());
        holder.textState.setText(state);
        holder.textReferenceID.setText("" + currentItem.getId());
    }

    /**
     * Function which returns the number of elements of the ArrayList of Orders
     *
     * @return he number of elements in listOfOrders
     */
    @Override
    public int getItemCount() {
        return listOfOrders.size();
    }
}
