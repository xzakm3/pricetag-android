package com.example.pricetag.templates.basket;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.interfaces.ItemToCalculatable;
import com.example.pricetag.data.interfaces.Itemable;
import com.example.pricetag.data.model.Item;
import com.example.pricetag.data.model.ItemToCalculate;
import com.example.pricetag.data.repositories.product.ProductRepository;
import com.example.pricetag.templates.ActionController;
import com.example.pricetag.utils.Preferences;
import com.example.pricetag.utils.ProductToItemToCalculateMapper;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsForBasketFragment extends Fragment implements ItemCallbacks {

    @BindView(R.id.headingTextView)
    TextView headingTextView;

//    @BindView(R.id.searchEditText)
//    EditText searchEditText;

//    @BindView(R.id.searchButton)
//    Button searchButton;

    @BindView(R.id.confirmButton)
    Button cofirmButton;

    @BindView(R.id.basketCancelButton)
    Button cancelButton;

    @BindView(R.id.itemsToBasketRecyclerView)
    RecyclerView itemsToBasketRecyclerView;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter itemsForBasketAdapter;
    ItemsForBasketTemplate template;
    private List<ItemToCalculatable> data;


    public ItemsForBasketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_items_for_basket, container, false);
        ButterKnife.bind(this, view);


        loadContent(getArguments());
        loadData();
        setButtonListeners();

        return view;
    }

    void loadData() {
        ProductRepository.getProducts(this);
    }

    void loadContent(Bundle bundle) {
        String url = bundle.getString("url");
        template = new ItemsForBasketTemplate(url);

        loadHeading();
    }

    void loadHeading() {
        this.headingTextView.setText(template.getHeadingTextViewContent());
    }

    void loadRecyclerView() {
        itemsToBasketRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        itemsToBasketRecyclerView.setLayoutManager(layoutManager);
        itemsForBasketAdapter = new ItemsForBasketAdapter(getActivity(),this.data, this);
        itemsToBasketRecyclerView.setAdapter(itemsForBasketAdapter);
    }

    void setButtonListeners() {
        this.cofirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ItemToCalculatable> checkedItems = ((ItemsForBasketAdapter) itemsForBasketAdapter).getCheckedItems();
                Preferences.addItemsForBasket(checkedItems);
                toHomeAction(view);
            }
        });

        this.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHomeAction(view);
            }
        });
    }

    private void toHomeAction(View view) {
        ActionController.executeBasketProductsToHomeAction(view);
    }

    @Override
    public void setItemData(List<? extends Item> data) {
        this.data = ProductToItemToCalculateMapper.map(data);
        loadRecyclerView();
    }

    @Override
    public void afterCreate(View view) {

    }

    @Override
    public void afterItemLoad(Itemable item) {

    }

    @Override
    public void afterUpdate(View view) {

    }

}
