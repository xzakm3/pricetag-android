package com.example.pricetag.templates.index;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.interfaces.Itemable;
import com.example.pricetag.data.model.Item;
import com.example.pricetag.templates.ActionController;
import com.example.pricetag.utils.ItemType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {
    private List<Itemable> items;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ItemType itemType;
    private Context context;
    private IndexFragment fragment;

    // data is passed into the constructor
    public IndexAdapter(Context context, List<Itemable> data, IndexFragment fragment) {
        this.mInflater = LayoutInflater.from(context);
        this.items = data;
        this.context = context;
        this.fragment = fragment;
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

        Itemable item = items.get(0);
        holder.setItemType(item.getType());

        int id = items.get(position).getId();
        holder.setId(id);

        holder.editActionButton.setOnClickListener(holder::handleEditClickEvent);

        holder.deleteActionButton.setOnClickListener(v -> {

            new AlertDialog.Builder(context)
                    .setTitle("Delete entry")
                    .setMessage("Are you sure you want to delete this entry?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        int itemId = holder.getId();
                        fragment.deleteData(itemId);
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

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

