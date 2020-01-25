package com.example.pricetag.templates.action;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricetag.R;
import com.example.pricetag.data.interfaces.Itemable;
import com.example.pricetag.data.model.ActionFragmentItem;
import com.example.pricetag.templates.ActionParameters;
import com.example.pricetag.utils.ItemType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
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

    protected List<ActionFragmentItem> data;
    protected int headingText;
    ItemActionTemplate itemActionTemplate;

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
        ActionParameters params = new ActionParameters(id, itemType, action);
        itemActionTemplate = new ItemActionTemplate(params.getHeadingText(), "Name", itemType);

        // this.data = RequestForData; <-- here do request for data
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
        itemActionAdapter = new ItemActionAdapter(getActivity(), new ArrayList<>());
        recyclerView.setAdapter(itemActionAdapter);
    }

    private void setButtonListeners() {
        addActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleAddItemClickEvent(view);
            }
        });
    }

    private void handleAddItemClickEvent(View view) {
//        int number = Integer.parseInt(numberEditText.getText().toString());
//        String text = "text";
//
//        ActionFragmentItem item = new ActionFragmentItem(text, number);
//        items.add(item);
//
//
//        data.clear();
//        data.addAll(items);
//        itemActionAdapter.notifyDataSetChanged();
    }
}
