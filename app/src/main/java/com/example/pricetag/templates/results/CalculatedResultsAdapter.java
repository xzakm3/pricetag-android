package com.example.pricetag.templates.results;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.requests.CalculateItemResponse;

import java.util.List;

public class CalculatedResultsAdapter extends RecyclerView.Adapter<CalculatedResultsAdapter.ViewHolder> {
    private List<CalculateItemResponse> items;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public CalculatedResultsAdapter(Context context, List<CalculateItemResponse> items) {
        this.mInflater = LayoutInflater.from(context);
        this.items = items;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.result_item_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //items.get(position).setQuantity(0);
        holder.itemTextView.setText(items.get(position).getName());
        holder.priceTextView.setText("Total Price: "+Float.toString(items.get(position).getTotal()));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return items.size();
    }


    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemTextView;
        TextView quantityTextView;
        TextView priceTextView;
        ViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.itemTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
        }

        @Override
        public void onClick(View view) {

        }
    }
    // convenience method for getting data at click position
    String getItem(int id) {
        return items.get(id).getName();
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
