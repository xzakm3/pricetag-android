package com.example.pricetag.templates.calculate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.bottomBar.home.HomeFragment;
import com.example.pricetag.data.interfaces.ItemToCalculatable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeActionAdapter extends RecyclerView.Adapter<HomeActionAdapter.ViewHolder> {
    private List<ItemToCalculatable> items;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private HomeFragment fragment;

    // data is passed into the constructor
    public HomeActionAdapter(Context context, List<ItemToCalculatable> data, HomeFragment fragment) {
        this.mInflater = LayoutInflater.from(context);
        this.items = data;
        this.fragment = fragment;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_action_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int productId = items.get(position).getId();
        holder.setProductId(productId);

        String name = items.get(position).getName();
        holder.nameTextView.setText(name);

        int quantity = items.get(position).getQuantity();
        holder.setQuantity(quantity);
        holder.numberTextView.setText(""+quantity);

        holder.deleteActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = 0;
                for (int i = 0; i < items.size(); i++) {
                    ItemToCalculatable item = items.get(i);
                    if(holder.getProductId() == item.getId()) {
                        position = i;
                        break;
                    }
                }
                fragment.removeAt(position);

            }
        });

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return items.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int productId;
        int quantity;
        TextView nameTextView;
        TextView numberTextView;
        FloatingActionButton deleteActionButton;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            numberTextView = itemView.findViewById(R.id.numberTextView);
            deleteActionButton = itemView.findViewById(R.id.deletefloatingActionButton);
        }

        int getQuantity() {
            return this.quantity;
        }

        void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        int getProductId() {
            return this.productId;
        }

        void setProductId(int productId) {
            this.productId = productId;
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return items.get(id).getName();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

