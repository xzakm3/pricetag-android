package com.example.pricetag.templates.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.activities.ApplicationWrapper;
import com.example.pricetag.data.interfaces.Deletable;
import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.interfaces.Itemable;
import com.example.pricetag.data.model.Item;
import com.example.pricetag.templates.ActionController;
import com.example.pricetag.utils.ItemType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.pricetag.data.repositories.ItemRepository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

abstract public class IndexFragment extends Fragment implements Loadable, Deletable, ItemCallbacks {

    @BindView(R.id.headingTextView)
    TextView headingTextView;

    @BindView(R.id.searchTextView)
    TextView searchTextView;

    @BindView(R.id.searchButton)
    Button searchButton;

    @BindView(R.id.createActionButton)
    FloatingActionButton createActionButton;

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    private RecyclerView.Adapter indexAdapter;
    private RecyclerView.LayoutManager layoutManager;

    protected List<Itemable> data;
    private List<Itemable> fetchedData;

    protected int headingText;


    protected View onCreateView(@NonNull LayoutInflater inflater,
                                ViewGroup container, Bundle savedInstanceState, IndexFragment fragment) {

        View view = inflater.inflate(R.layout.fragment_index, container, false);
        ButterKnife.bind(this, view);


        fragment.loadData();
        loadContent();
        setButtonListeners();
        searchButton.setOnClickListener(this::handleSearchResults);

        return view;
    }

    private void setButtonListeners() {
        createActionButton.setOnClickListener(this::handleCreateClickButton);
    }

    private void loadContent() {
        loadHeading();
        loadRecyclerView();
    }

    private void loadHeading() {
        headingTextView.setText(headingText);
    }

    private void loadRecyclerView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void loadData() {
    }

    private void handleSearchResults(View view) {

        if (indexAdapter != null) {
            List<Itemable> searchResults = new ArrayList<>();

            String search = searchTextView.getText().toString();

            for (Itemable item : fetchedData) {
                if (item.getName().toLowerCase().contains(search.toLowerCase())) {
                    searchResults.add(item);
                }
            }

            resetRecyclerData(searchResults);

            if (searchResults.size() == 0) {
                Toasty.info(ApplicationWrapper.getAppContext(), "No results found", Toast.LENGTH_SHORT, true).show();
            }
        }
    }


    protected void setRecyclerData(List<? extends Item> items) {
        this.fetchedData = new ArrayList<>(items);
        this.data = new ArrayList<>(items);

        indexAdapter = new IndexAdapter(getActivity(), data, this);
        recyclerView.setAdapter(indexAdapter);
    }

    void deleteData(int id) {
        ItemRepository.deleteItem(id, data.get(0).getEntity(), this);
    }

    private void handleCreateClickButton(View v) {
        ItemType itemType = this.data.get(0).getType();
        String action = "create";

        Bundle data = new Bundle();
        data.putSerializable("itemType", itemType);
        data.putString("action", action);

        ActionController.executeCreateEditAction(v, itemType, data);
    }

    @Override
    public void afterDelete(int id) {
        Itemable toDelete = null;
        for(Itemable item : fetchedData) {
            if (item.getId() == id) {
                toDelete = item;
                break;
            }
        }

        if (toDelete != null) {
            fetchedData.remove(toDelete);
            resetRecyclerData(fetchedData);
        }
    }

    private void resetRecyclerData(List<Itemable> newData) {
        data.clear();
        data.addAll(newData);
        indexAdapter.notifyDataSetChanged();
    }

    @Override
    public void setItemData(List<? extends Item> data) { this.setRecyclerData(data); }

    @Override
    public void afterCreate(View view) { }

    @Override
    public void afterItemLoad(Itemable item) { }

    @Override
    public void afterUpdate(View view) { }
}
