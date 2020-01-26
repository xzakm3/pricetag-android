package com.example.pricetag.templates.results;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pricetag.R;
import com.example.pricetag.data.requests.CalculateItemResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatedResultsFragment extends Fragment {

    @BindView(R.id.resultsRecyclerView)
    RecyclerView resultsRecyclerView;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter itemsForResultsAdapter;

    List<CalculateItemResponse> data;

    public CalculatedResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculated_results, container, false);

        ButterKnife.bind(this, view);
        loadContent(getArguments());

        return view;
    }

    void loadRecyclerView() {
        resultsRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        resultsRecyclerView.setLayoutManager(layoutManager);
        itemsForResultsAdapter = new CalculatedResultsAdapter(getActivity(),this.data);
        resultsRecyclerView.setAdapter(itemsForResultsAdapter);
    }

    void loadContent(Bundle data) {
        this.data = (List<CalculateItemResponse>)data.getSerializable("data");
        loadRecyclerView();
    }

}
