package com.example.pricetag.templates.index;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.interfaces.Itemable;
import com.example.pricetag.data.model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {
    private List<Itemable> items;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public IndexAdapter(Context context, List<Itemable> data) {
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

        int id = items.get(position).getId();
        holder.setId(id);

        holder.editActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // int itemId = holder.getId();
                Navigation.findNavController(v).navigate(R.id.action_navigation_products_to_item_action_fragment);
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

