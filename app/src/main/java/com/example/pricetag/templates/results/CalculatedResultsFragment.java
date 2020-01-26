package com.example.pricetag.templates.results;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pricetag.R;
import com.example.pricetag.data.requests.CalculateItemResponse;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatedResultsFragment extends Fragment {


    public CalculatedResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        List<CalculateItemResponse> resultsData =
                (List<CalculateItemResponse>)getArguments().getSerializable("data");

        return inflater.inflate(R.layout.fragment_calculated_results, container, false);
    }

}
