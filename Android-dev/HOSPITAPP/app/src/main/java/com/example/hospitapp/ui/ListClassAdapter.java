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

/**
 * ListClassAdaptor Class which set the Recycle View configuration for the HomeFragment, DashboardFragment
 * and OrdersInformationDialog Lists. It used a item as a model which is used repeatedly in which the
 * information is substituted as a way of improving performance in the creation of list
 */
public class ListClassAdapter extends RecyclerView.Adapter<ListClassAdapter.OrderViewHolder>{

    /**
     * ArrayList which contains the list of all the orders which will be displayed
     */
    ArrayList<Order> listOfOrders;
    /**
     * Context variable which gets the context from the app
     */
    Context mContext;
    /**
     * String indicating the state (Used only in some cases) to determine different items xml references
     * and differentiate between both types
     */
    private String state = "TO BE ACCEPTED";

    /**
     * Constructor which sets the list of orders which will be displayed
     *
     * @param listOfOrders ArrayList of orders to be displayed
     */
    public ListClassAdapter(ArrayList<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    /**
     * Constructor which stets the list of orders which will be displayed
     *
     * @param listsOfOrders ArrayList of orders to be displayed
     * @param state the state to use a different item xml reference
     * @param mContext get the context of the fragment from where it is called
     */
    public ListClassAdapter(ArrayList<Order> listsOfOrders, String state, Context mContext) {
        this.listOfOrders = listsOfOrders;
        this.state = state;
        this.mContext = mContext;
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
         * TextVIew which will be substituted with the providers name when is required
         */
        private TextView textProviderName;

        /**
         * Function which links all variables with their elements of the reference item
         *
         * @param itemView gets the corresponding view for the element of the list which is being linked
         */
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            if (!state.equals("TO BE ACCEPTED")) {

                textObject = this.itemView.findViewById(R.id.ObjectName);
                textVolumeNumber = this.itemView.findViewById(R.id.VolumeNumber);
                textDate = this.itemView.findViewById(R.id.dateNumber);
                textState = this.itemView.findViewById(R.id.State);
                textProviderId = this.itemView.findViewById(R.id.providerNum);
                textReferenceID = this.itemView.findViewById(R.id.ReferenceIdNum);

            } else {
                textProviderName = itemView.findViewById(R.id.ParticularName);
                textProviderId = itemView.findViewById(R.id.providerNum);

            }
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
        View view;
        if (!state.equals("TO BE ACCEPTED")){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        } else {
            view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_provider_list,null,false);
        }

        OrderViewHolder evh = new OrderViewHolder(view);
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
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        if (state.equals("TO BE ACCEPTED")) {
            holder.textProviderId.setText("" + listOfOrders.get(position).getId_provider());
            holder.textProviderName.setText("" + listOfOrders.get(position).getObject_name());

        } else {
            if (!listOfOrders.get(position).isSent() && !listOfOrders.get(position).isReceived()) {
                holder.textObject.setText("" + listOfOrders.get(position).getObject_name());
                holder.textVolumeNumber.setText("" + listOfOrders.get(position).getQuantity());
                holder.textDate.setText(listOfOrders.get(position).getDate());
                holder.textProviderId.setText("" + listOfOrders.get(position).getId_provider());
                holder.textState.setText("LINKED");
                holder.textReferenceID.setText("" +listOfOrders.get(position).getId());
            }

            if (listOfOrders.get(position).isSent() && !listOfOrders.get(position).isReceived()) {
                holder.textObject.setText("" + listOfOrders.get(position).getObject_name());
                holder.textVolumeNumber.setText("" + listOfOrders.get(position).getQuantity());
                holder.textDate.setText(listOfOrders.get(position).getDate());
                holder.textProviderId.setText("" + listOfOrders.get(position).getId_provider());
                holder.textState.setText("SENT");
                holder.textReferenceID.setText("" + listOfOrders.get(position).getId());
            }

            if (listOfOrders.get(position).isSent() && listOfOrders.get(position).isReceived()) {
                holder.textObject.setText("" + listOfOrders.get(position).getObject_name());
                holder.textVolumeNumber.setText("" + listOfOrders.get(position).getQuantity());
                holder.textDate.setText(listOfOrders.get(position).getDate());
                holder.textProviderId.setText("" + listOfOrders.get(position).getId_provider());
                holder.textState.setText("RECEIVED");
                holder.textReferenceID.setText("" +listOfOrders.get(position).getId());

            }
        }
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
