package com.example.pricetag.templates.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.model.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

abstract public class IndexFragment extends Fragment implements Loadable {
    @BindView(R.id.headingTextView)
    TextView headingTextView;

    @BindView(R.id.searchTextView)
    TextView searchTextView;

    @BindView(R.id.searchButton)
    Button searchButton;

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    protected RecyclerView.Adapter indexAdapter;
    private RecyclerView.LayoutManager layoutManager;

    protected List<? extends Item> data;
    protected int headingText;


    protected View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState, IndexFragment fragment) {

        View view = inflater.inflate(R.layout.fragment_index, container, false);
        ButterKnife.bind(this, view);

        fragment.loadData();
        loadContent();

        return view;
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
}
