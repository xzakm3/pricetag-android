package com.example.pricetag.templates;

import android.view.View;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.pricetag.R;
import com.example.pricetag.utils.ItemType;

public class ActionController {
    public static void execute(View v, ItemType itemType, Bundle data) {
        NavController navController = Navigation.findNavController(v);
        if(itemType.equals(ItemType.PRODUCT)) {
            navController.navigate(R.id.action_navigation_products_to_item_action_fragment, data);
        } else if(itemType.equals(ItemType.SHOP)) {
            navController.navigate(R.id.action_navigation_shops_to_item_action_fragment, data);
        } else if(itemType.equals(ItemType.SHOPPINGLIST)) {
            navController.navigate(R.id.action_navigation_lists_to_item_action_fragment, data);
        }
    }
}
