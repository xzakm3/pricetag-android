package com.example.pricetag.templates.action;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.model.ActionFragmentItemable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ItemActionAdapter extends RecyclerView.Adapter<ItemActionAdapter.ViewHolder> {
    private List<ActionFragmentItemable> items;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ItemActionFragment fragment;

    // data is passed into the constructor
    public ItemActionAdapter(Context context, List<ActionFragmentItemable> data, ItemActionFragment fragment) {
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
        float number = items.get(position).getNumber();
        holder.numberTextView.setText(String.valueOf(number));

        String name = items.get(position).getName();
        holder.nameTextView.setText(name);

        holder.deleteActionButton.setOnClickListener(v -> {
            // TODO - delete the item
            // TODO - mark the destroy flag as 1
            // TODO - hide from the interface
            fragment.deleteData(position);
        });

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return items.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int number;
        TextView nameTextView;
        TextView numberTextView;
        FloatingActionButton deleteActionButton;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            numberTextView = itemView.findViewById(R.id.numberTextView);
            deleteActionButton = itemView.findViewById(R.id.deletefloatingActionButton);
        }

        void setNumber(int number) {
            this.number = number;
        }

        int getNumber() {
            return this.number;
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
