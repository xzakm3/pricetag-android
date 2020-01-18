package com.example.pricetag.ui.shops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.IndexFragment;
import com.example.pricetag.R;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class ShopsFragment extends Fragment {

    private List<String> data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shops, container, false);

        initData();
        Bundle args = setFragmentArgs();

        Fragment fragment = new IndexFragment();
        fragment.setArguments(args);

        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes

        return view;
    }

    private void initData() {
        data = Arrays.asList("Lidl", "Super U", "Carrefour");
    }

    private Bundle setFragmentArgs() {
        Bundle args = new Bundle();
        args.putInt("heading", R.string.shops_heading);
        args.putSerializable("data", (Serializable) data);
        return args;
    }
}