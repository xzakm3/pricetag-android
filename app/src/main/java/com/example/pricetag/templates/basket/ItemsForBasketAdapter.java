package com.example.pricetag.templates.basket;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.interfaces.ItemToCalculatable;

import java.util.ArrayList;
import java.util.List;

public class ItemsForBasketAdapter extends RecyclerView.Adapter<ItemsForBasketAdapter.ViewHolder> {
    private List<ItemToCalculatable> items;
    private List<Integer> checkedItemsPositions = new ArrayList<>();
    private LayoutInflater mInflater;
    private List<ItemToCalculatable> checkedItems;
    private static List<String> mEditTextValues = new ArrayList<>();
    private ItemsForBasketFragment fragment;

    // data is passed into the constructor
    public ItemsForBasketAdapter(Context context, List<ItemToCalculatable> data, ItemsForBasketFragment fragment) {
        this.mInflater = LayoutInflater.from(context);
        this.items = data;
        mEditTextValues.clear();
        for(int i=0;i<=items.size();i++){
            mEditTextValues.add("0");
        }
        this.fragment = fragment;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_to_basket_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        items.get(position).setQuantity(0);
        ItemToCalculatable itemToCalculatable = items.get(position);

        holder.setItem(itemToCalculatable);

        int productId = itemToCalculatable.getId();
        holder.setProductId(productId);

        String name = itemToCalculatable.getName();
        holder.nameTextView.setText(name);

        holder.checkBox.setChecked(itemToCalculatable.isChecked());

        holder.numberEditText.setTag(position);
        holder.numberEditText.setText(mEditTextValues.get(position));
        // holder.numberEditText.setText(""+items.get(holder.getAdapterPosition()).getQuantity());

        holder.setItemClickListener(new ViewHolder.ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox myCheckBox= (CheckBox) v;
                ItemToCalculatable currentItem=items.get(pos);

                if(myCheckBox.isChecked()) {
                    currentItem.setChecked(true);
                    checkedItemsPositions.add(pos);
                }
                else if(!myCheckBox.isChecked()) {
                    currentItem.setChecked(false);
                    int index;
                    while ((index = checkedItemsPositions.indexOf(pos)) >= 0) {
                        checkedItemsPositions.remove(index);
                    }
                }


            }
        });

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<ItemToCalculatable> getCheckedItems() {
        checkedItems = new ArrayList<>();
        checkedItemsPositions.forEach((position) -> checkedItems.add(items.get(position)));
        return checkedItems;
    }


    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int productId;
        int quantity;
        ItemToCalculatable item;
        TextView nameTextView;
        EditText numberEditText;
        CheckBox checkBox;
        ItemClickListener itemClickListener;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            numberEditText = itemView.findViewById(R.id.numberEditText);
            checkBox = itemView.findViewById(R.id.itemCheck);
            checkBox.setOnClickListener(this);
            //numberEditText.addTextChangedListener(myCustomEditTextListener);
            numberEditText.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                public void afterTextChanged(Editable editable) {}
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(numberEditText.getTag()!=null && !charSequence.toString().isEmpty()){
                        mEditTextValues.set((int)numberEditText.getTag(), charSequence.toString());
                        item.setQuantity(Integer.parseInt(charSequence.toString()));
                    }
                }
            });
        }

        void setItem(ItemToCalculatable item) {
            this.item = item;
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
            this.itemClickListener.onItemClick(view,getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener ic)
        {
            this.itemClickListener=ic;
        }

        interface ItemClickListener {

            void onItemClick(View v,int pos);
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

