package com.example.pricetag.templates.action;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.model.Item;
import com.example.pricetag.data.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemActionFragment extends Fragment {
    @BindView(R.id.headingTextView)
    TextView headingTextView;

    @BindView(R.id.nameTextView)
    TextView nameTextView;

    @BindView(R.id.addressTextView)
    TextView addressTextView;

    @BindView(R.id.itemToChooseTextView)
    TextView itemToChooseTextView;

    @BindView(R.id.actionTextView)
    TextView actionTextView;

    @BindView(R.id.nameEditText)
    EditText nameEditText;

    @BindView(R.id.addressEditText)
    EditText addressEditText;

    @BindView(R.id.itemToChooseSpinner)
    Spinner itemToChooseSpinner;

    @BindView(R.id.addActionButton)
    FloatingActionButton addActionButton;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerView.Adapter indexAdapter;
    private RecyclerView.LayoutManager layoutManager;

    protected List<? extends Item> data;
    protected int headingText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_item_action, container, false);
        ButterKnife.bind(this, view);

        loadData();
        loadContent();


        return view;
    }

    private void loadData() {
        this.data = generatedata();
    }

    private List<? extends Item> generatedata() {
        return Arrays.asList(
                new Product(1, "Orange"),
                new Product(2, "Lemon"),
                new Product(3, "Potato"),
                new Product(4, "Pepper"),
                new Product(5, "Cucumber"),
                new Product(6, "Rice"),
                new Product(7, "Bread"),
                new Product(8, "Milk"),
                new Product(9, "Sugar"),
                new Product(10, "Slat")
        );
    }

    private void loadContent() {
        loadHeading();
        loadRecyclerView();
    }

    private void loadHeading() {
        this.headingTextView.setText(R.string.shops_heading);
    }

    private void loadRecyclerView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        // indexAdapter = new IndexAdapter(getActivity(), this.data, this);
        recyclerView.setAdapter(indexAdapter);
    }
}
