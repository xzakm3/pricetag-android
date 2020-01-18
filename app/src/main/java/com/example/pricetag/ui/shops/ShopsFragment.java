package com.example.pricetag.ui.shops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.templates.adapters.IndexAdapter;
import com.example.pricetag.ui.shops.ShopsViewModel;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopsFragment extends Fragment {

    private ShopsViewModel shopsViewModel;

    @BindView(R.id.headingTextView)
    TextView headingTextView;

    @BindView(R.id.searchTextView)
    TextView searchTextView;

    @BindView(R.id.searchButton)
    Button searchButton;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerView.Adapter indexAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shopsViewModel =
                ViewModelProviders.of(this).get(ShopsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_shops, container, false);

        ButterKnife.bind(this, view);

        initData();
        initContent();

        return view;
    }

    private void initData() {
        data = Arrays.asList("Lidl", "Super U", "Carrefour");
    }

    private void initContent() {
        headingTextView.setText(R.string.shops_heading);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        indexAdapter = new IndexAdapter(getActivity(), data);
        recyclerView.setAdapter(indexAdapter);
    }
}