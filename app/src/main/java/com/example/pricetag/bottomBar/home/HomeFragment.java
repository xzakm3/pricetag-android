package com.example.pricetag.bottomBar.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.interfaces.CalculateCallbacks;
import com.example.pricetag.data.interfaces.ItemToCalculatable;
import com.example.pricetag.data.model.CalculateProduct;
import com.example.pricetag.data.model.Item;
import com.example.pricetag.data.model.ItemToCalculate;
import com.example.pricetag.data.repositories.ItemRepository;
import com.example.pricetag.data.requests.CalculateItemResponse;
import com.example.pricetag.data.requests.CalculateItemsRequest;
import com.example.pricetag.templates.ActionController;
import com.example.pricetag.templates.calculate.HomeActionAdapter;
import com.example.pricetag.utils.Preferences;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Action;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements CalculateCallbacks {

    @BindView(R.id.addProductButton)
    Button addProductButton;

    @BindView(R.id.addListButton)
    Button addListButton;

    @BindView(R.id.calculateButton)
    Button calculateButton;

    @BindView(R.id.itemsToCalculateRecyclerView)
    RecyclerView itemsToCalculateRecyclerView;

    private RecyclerView.Adapter calculateActionAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ItemToCalculatable> data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        loadData();
        loadRecyclewView();
        setButtonListeners();

        return view;
    }

    void loadData() {
        this.data = Preferences.getBasketItems();
    }

    void loadRecyclewView() {
        itemsToCalculateRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        itemsToCalculateRecyclerView.setLayoutManager(layoutManager);
        calculateActionAdapter = new HomeActionAdapter(getActivity(),this.data, this);
        itemsToCalculateRecyclerView.setAdapter(calculateActionAdapter);
    }

    void setButtonListeners() {
        addProductButton.setOnClickListener(view -> {
            Bundle data = new Bundle();
            data.putString("url", "products");
            ActionController.executeHomeToBasketProductsAction(view, data);
        });

        addListButton.setOnClickListener(view -> {
            Bundle data = new Bundle();
            data.putString("url", "shopping_lists");
            ActionController.executeHomeToBasketProductsAction(view, data);
        });

        calculateButton.setOnClickListener(view -> {
            List<CalculateProduct> calculateProductList = new ArrayList<>();

            for(ItemToCalculatable item : data) {
                calculateProductList.add(new CalculateProduct(item.getId(), item.getQuantity()));
            }

            CalculateItemsRequest request = new CalculateItemsRequest(calculateProductList);

            if (request.validateRequest()) {
                ItemRepository.calculate(request, this, view);
            }
        });


    }

    public void removeAt(int position) {
        ItemToCalculatable item = this.data.get(position);
        this.data.remove(position);
        calculateActionAdapter.notifyItemRemoved(position);
        calculateActionAdapter.notifyItemRangeChanged(position, this.data.size());
        removeFromStorage(item);
    }

    public void removeFromStorage(ItemToCalculatable item) {
        Preferences.removeItemFromBasket(item);
    }

    @Override
    public void setCalculateData(List<CalculateItemResponse> results, View view) {
        System.out.println("test");
        Bundle bundleData = new Bundle();
        bundleData.putSerializable("data", (Serializable) results);

        ActionController.executeBasketoToResultsAction(view, bundleData);
    }
}