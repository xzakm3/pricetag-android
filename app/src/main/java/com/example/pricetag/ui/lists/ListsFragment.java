package com.example.pricetag.ui.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.pricetag.IndexFragment;
import com.example.pricetag.R;

import java.util.Arrays;

public class ListsFragment extends IndexFragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState, this);
        return view;
    }

    @Override
    public void loadData() {
        this.data = Arrays.asList("Sunday's specialty", "Thursday shopping", "Pancakes");
        this.headingText = R.string.lists_heading;
    }
}