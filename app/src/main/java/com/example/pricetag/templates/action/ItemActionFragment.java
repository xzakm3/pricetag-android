package com.example.pricetag.templates.action;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.interfaces.Itemable;
import com.example.pricetag.data.model.ActionFragmentItemable;
import com.example.pricetag.data.model.Item;
import com.example.pricetag.data.model.ProductInShop;
import com.example.pricetag.data.model.ProductInShopBuilder;
import com.example.pricetag.data.repositories.product.ProductRepository;
import com.example.pricetag.data.repositories.shop.ShopRepository;
import com.example.pricetag.templates.ActionParameters;
import com.example.pricetag.utils.ItemType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemActionFragment extends Fragment implements ItemCallbacks {
    @BindView(R.id.headingTextView)
    TextView headingTextView;

    @BindView(R.id.nameTextView)
    TextView nameTextView;

    @BindView(R.id.addressTextView)
    TextView addressTextView;

    @BindView(R.id.itemToChooseTextView)
    TextView itemToChooseTextView;

    @BindView(R.id.numberEditText)
    EditText numberEditText;

    @BindView(R.id.nameEditText)
    EditText nameEditText;

    @BindView(R.id.addressEditText)
    EditText addressEditText;

    @BindView(R.id.itemToChooseSpinner)
    Spinner itemToChooseSpinner;

    @BindView(R.id.addActionButton)
    FloatingActionButton addActionButton;

    @BindView(R.id.addressLinearLayout)
    LinearLayout addressLinearLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerView.Adapter itemActionAdapter;
    private RecyclerView.LayoutManager layoutManager;

    protected List<ActionFragmentItemable> data = new ArrayList<>();
    protected int headingText;

    ItemActionTemplate itemActionTemplate;
    ActionParameters params;

    List<Itemable> spinnerFetchedData;
    List<Itemable> spinnerCurrentData;
    ArrayAdapter<Itemable> spinnerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_action, container, false);
        ButterKnife.bind(this, view);

        loadData(getArguments());
        loadContent();
        setButtonListeners();


        return view;
    }

    private void loadData(Bundle bundle) {
        int id = bundle.getInt("id");
        ItemType itemType = (ItemType) bundle.getSerializable("itemType");
        String action = bundle.getString("action");
        params = new ActionParameters(id, itemType, action);
        itemActionTemplate = new ItemActionTemplate(params.getHeadingText(), "Name", itemType);

        switch (itemType) {
            case SHOP:
            case SHOPPINGLIST:
                ProductRepository.getProducts(this);
                break;
            case PRODUCT:
                ShopRepository.getShops(this);
                break;
            default:
                break;
        }
    }

    private void loadContent() {
        loadTemplate();
        loadRecyclerView();
    }

    private void loadTemplate() {
        this.headingTextView.setText(itemActionTemplate.getHeadingText());
        this.nameTextView.setText(itemActionTemplate.getNameTextViewContent());
        this.itemToChooseTextView.setText(itemActionTemplate.getItemToChooseContent());
        this.numberEditText.setHint(itemActionTemplate.getNumberEditTextContent());
        this.addressLinearLayout.setVisibility(itemActionTemplate.getVisibility());
    }

    private void loadRecyclerView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        itemActionAdapter = new ItemActionAdapter(getActivity(), data, this);
        recyclerView.setAdapter(itemActionAdapter);
    }

    private void setButtonListeners() {
        addActionButton.setOnClickListener(this::handleAddItemClickEvent);
    }

    private void handleAddItemClickEvent(View view) {
        if (spinnerCurrentData.size() == 0 || numberEditText.getText().toString().equals("")) {
            return;
        }

        float number = Float.parseFloat(numberEditText.getText().toString());
        Itemable entity = (Itemable)itemToChooseSpinner.getSelectedItem();

        ProductInShopBuilder pBuilder = (new ProductInShopBuilder()).setPrice(number);

        if (params.getItemType() == ItemType.PRODUCT) {
            pBuilder = pBuilder.setShopId(entity.getId()).setShopName(entity.getName());
        } else if (params.getItemType() == ItemType.SHOP) {
            pBuilder = pBuilder.setProductId(entity.getId()).setProductName(entity.getName());
        } else { }



        ProductInShop product = pBuilder.build();
        data.add(product);
        itemActionAdapter.notifyDataSetChanged();
        afterRecyclerAdd(product);
    }

    private void afterRecyclerAdd(ProductInShop productInShop) {
        int idToFind = -1;

        if (params.getItemType() == ItemType.PRODUCT) {
            idToFind = productInShop.getShopId();
        } else if (params.getItemType() == ItemType.SHOP){
            idToFind = productInShop.getProductId();
        }

        int toRemove = 0;

        for (int i = 0; i < spinnerCurrentData.size(); i++) {
            if (spinnerCurrentData.get(i).getId() == idToFind) {
                toRemove = i;
                break;
            }
        }

        spinnerCurrentData.remove(toRemove);

        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setItemData(List<? extends Item> data) {
        spinnerCurrentData = new ArrayList<>(data);
        spinnerFetchedData = new ArrayList<>(data);

        spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, spinnerCurrentData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemToChooseSpinner.setAdapter(spinnerAdapter);
    }

    public void deleteData(int position) {
        ProductInShop productInShop = (ProductInShop) data.get(position);
        int idToFind = productInShop.getDependant(params);




        for (Itemable item : spinnerFetchedData) {
            if (item.getId() == idToFind) {
                spinnerCurrentData.add(item);
                break;
            }
        }

        data.remove(position);
        itemActionAdapter.notifyDataSetChanged();
        spinnerAdapter.notifyDataSetChanged();
    }

}
