package com.example.pricetag.templates.action;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.interfaces.Itemable;
import com.example.pricetag.data.model.ActionFragmentItemable;
import com.example.pricetag.data.model.Item;
import com.example.pricetag.data.model.Product;
import com.example.pricetag.data.model.ProductInShop;
import com.example.pricetag.data.model.ProductInShopBuilder;
import com.example.pricetag.data.model.Shop;
import com.example.pricetag.data.repositories.product.ProductRepository;
import com.example.pricetag.data.repositories.shop.ShopRepository;
import com.example.pricetag.data.requests.ProductRequest;
import com.example.pricetag.data.requests.ShopRequest;
import com.example.pricetag.templates.ActionController;
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

    @BindView(R.id.confirmButton)
    Button confirmButton;

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
        confirmButton.setOnClickListener(this::handleConfirmButton);
    }

    private void handleAddItemClickEvent(View view) {
        if (spinnerCurrentData.size() == 0 || numberEditText.getText().toString().equals("")) {
            return;
        }

        float number = Float.parseFloat(numberEditText.getText().toString());
        Itemable entity = (Itemable) itemToChooseSpinner.getSelectedItem();

        ProductInShopBuilder pBuilder = (new ProductInShopBuilder()).setPrice(number);

        if (params.getItemType() == ItemType.PRODUCT) {
            pBuilder = pBuilder.setShopId(entity.getId()).setShopName(entity.getName());
        } else if (params.getItemType() == ItemType.SHOP) {
            pBuilder = pBuilder.setProductId(entity.getId()).setProductName(entity.getName());
        } else {
        }


        ProductInShop product = pBuilder.build();
        data.add(product);
        itemActionAdapter.notifyDataSetChanged();
        afterRecyclerAdd(product);
    }

    private void afterRecyclerAdd(ProductInShop productInShop) {
        Integer idToFind = null;

        if (params.getItemType() == ItemType.PRODUCT) {
            idToFind = productInShop.getShopId();
        } else if (params.getItemType() == ItemType.SHOP) {
            idToFind = productInShop.getProductId();
        }

        int toRemove = 0;

        for (int i = 0; i < spinnerCurrentData.size(); i++) {
            if (spinnerCurrentData.get(i).getId().equals(idToFind)) {
                toRemove = i;
                break;
            }
        }

        spinnerCurrentData.remove(toRemove);

        spinnerAdapter.notifyDataSetChanged();

        numberEditText.setText("");
    }

    @Override
    public void setItemData(List<? extends Item> data) {
        spinnerCurrentData = new ArrayList<>(data);
        spinnerFetchedData = new ArrayList<>(data);

        spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, spinnerCurrentData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemToChooseSpinner.setAdapter(spinnerAdapter);

        if (params.getTypeOfAction().equals("edit")) {
            initializeEditData();
        }
    }

    @Override
    public void afterCreate(View view) {
        ActionController.executeFromItemAction(view, params);
    }

    @Override
    public void afterItemLoad(Itemable item) {
        if (params.getItemType() == ItemType.PRODUCT) {
            Product product = (Product) item;
            initializeProductEditData(product);
        } else if (params.getItemType() == ItemType.SHOP) {
            Shop shop = (Shop) item;
            initializeShopEditData(shop);
        } else {

        }
    }

    @Override
    public void afterUpdate(View view) { ActionController.executeFromItemAction(view, params); }

    public void initializeProductEditData(Product product) {
        if (product != null) {
            nameEditText.setText(product.getName());

            // Initialize recycler view
            for (ProductInShop productInShop : product.getProductInShopAttributes()) {
                productInShop.syncShop();
                data.add(productInShop);
                itemActionAdapter.notifyDataSetChanged();
                afterRecyclerAdd(productInShop);
            }
        }
    }

    public void initializeShopEditData(Shop shop) {
        if (shop != null) {
            nameEditText.setText(shop.getName());
            addressEditText.setText(shop.getAddress());

            // Initialize recycler view
            for (ProductInShop productInShop : shop.getProductInShopAttributes()) {
                productInShop.syncProduct();
                data.add(productInShop);
                itemActionAdapter.notifyDataSetChanged();
                afterRecyclerAdd(productInShop);
            }
        }
    }

    /**
     * Deletes data by position from the recycler and also handles
     * spinner add or removal of deleted item.
     * Made by ANDREJ SCANSY
     * @param position position of item in the recycler view
     */
    void deleteData(int position) {
        // TODO - delete the item
        // TODO - mark the destroy flag as 1
        // TODO - hide from the interface


        ProductInShop productInShop = (ProductInShop) data.get(position);
        int idToFind = productInShop.getDependant(params);


        // Spinner all data
        for (Itemable item : spinnerFetchedData) {
            // Find shop or product
            if (item.getId() == idToFind) {
                // Add it back to the spinner
                spinnerCurrentData.add(item);
                break;
            }
        }

        if(productInShop.getId() == null) {
            data.remove(position);
        } else {
            productInShop.setDestroy(1);
        }

        itemActionAdapter.notifyDataSetChanged();
        spinnerAdapter.notifyDataSetChanged();
    }

    private void handleConfirmButton(View view) {
        if (params.getTypeOfAction().equals("edit") ) {
            handleUpdateItem(view);
        } else if (params.getTypeOfAction().equals("create")) {
            handleCreateItem(view);
        } else {

        }
    }

    private void handleCreateItem(View view) {
        if (params.getItemType() == ItemType.PRODUCT) {
            this.handleProductCreate(view);
        } else if (params.getItemType() == ItemType.SHOP) {
            this.handleShopCreate(view);
        } else {

        }
    }


    private void handleProductCreate(View view) {
        ProductRequest productRequest = getProductPayload();
        if (productRequest.validateRequest()) {
            ProductRepository.createProduct(productRequest,  this, view);
        }
    }

    private ProductRequest getProductPayload() {
        Product product = new Product(null, nameEditText.getText().toString());

        for (ActionFragmentItemable itemable : data) {
            ProductInShop productInShop = (ProductInShop) itemable;
            product.getProductInShopAttributes().add(productInShop);
        }

        return new ProductRequest(product);
    }

    private ShopRequest getShopPayload() {
        Shop shop = new Shop(null, nameEditText.getText().toString(), addressEditText.getText().toString());

        for (ActionFragmentItemable itemable : data) {
            ProductInShop productInShop = (ProductInShop) itemable;
            shop.getProductInShopAttributes().add(productInShop);
        }

        return new ShopRequest(shop);
    }

    private void handleShopCreate(View view) {
        ShopRequest shopRequest = getShopPayload();

        if (shopRequest.validateRequest()) {
            ShopRepository.createShop(shopRequest, this, view);
        }
    }


    private void handleUpdateItem(View view) {
        if (params.getItemType() == ItemType.PRODUCT) {
            this.handleProductUpdate(view);
        } else if (params.getItemType() == ItemType.SHOP) {
            this.handleShopUpdate(view);
        } else {

        }
    }

    private void handleProductUpdate(View view) {
        ProductRequest productRequest = getProductPayload();

        if (productRequest.validateRequest()) {
            ProductRepository.updateProduct(params.getId(), productRequest, this, view);
        }
    }

    private void handleShopUpdate(View view) {
        ShopRequest shopRequest = getShopPayload();

        if (shopRequest.validateRequest()) {
            ShopRepository.updateShop(params.getId(), shopRequest, this, view);
        }
    }



    private void initializeEditData() {
        if (params.getItemType() == ItemType.PRODUCT) {
            ProductRepository.getProduct(params.getId(), this);
        } else if (params.getItemType() == ItemType.SHOP) {
            ShopRepository.getShop(params.getId(), this);
        } else {
            // TODO - IDGAF
        }
    }
}
