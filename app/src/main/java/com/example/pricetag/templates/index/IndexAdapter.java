package com.example.pricetag.templates.index;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.model.Item;
import com.example.pricetag.templates.ActionController;
import com.example.pricetag.utils.ItemType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {
    private List<? extends Item> items;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ItemType itemType;

    // data is passed into the constructor
    IndexAdapter(Context context, List<? extends Item> data) {
        this.mInflater = LayoutInflater.from(context);
        this.items = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.index_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = items.get(position).getName();
        holder.itemTextView.setText(name);

        Item item = items.get(0);
        holder.setItemType(item.getType());

        int id = items.get(position).getId();
        holder.setId(id);

        holder.editActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                holder.handleEditClickEvent(v);
            }
        });

        holder.deleteActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int itemId = holder.getId();
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
        int id;
        TextView itemTextView;
        FloatingActionButton editActionButton;
        FloatingActionButton deleteActionButton;
        ItemType itemType;

        ViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.nameTextView);
            editActionButton = itemView.findViewById(R.id.editButton);
            deleteActionButton = itemView.findViewById(R.id.deleteButton);
        }

        void setId(int id) {
            this.id = id;
        }

        int getId() {
            return this.id;
        }

        void setItemType(ItemType itemType) { this.itemType = itemType; }

        ItemType getItemType() { return this.itemType; }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

        void handleEditClickEvent(View v) {
            int itemId = this.getId();
            ItemType itemType = this.getItemType();
            String action = "edit";

            Bundle data = new Bundle();
            data.putInt("id", itemId);
            data.putSerializable("itemType", itemType);
            data.putString("action", action);

            ActionController.execute(v, itemType, data);
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

