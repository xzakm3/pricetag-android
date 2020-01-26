package com.example.pricetag.templates;

import android.view.View;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.pricetag.R;
import com.example.pricetag.utils.ItemType;

public class ActionController {
    public static NavController get(View v) {
        return Navigation.findNavController(v);
    }

    public static void executeCreateEditAction(View v, ItemType itemType, Bundle data) {
        NavController navController = get(v);
        if(itemType.equals(ItemType.PRODUCT)) {
            navController.navigate(R.id.action_navigation_products_to_item_action_fragment, data);
        } else if(itemType.equals(ItemType.SHOP)) {
            navController.navigate(R.id.action_navigation_shops_to_item_action_fragment, data);
        } else if(itemType.equals(ItemType.SHOPPINGLIST)) {
            navController.navigate(R.id.action_navigation_lists_to_item_action_fragment, data);
        }
    }

    public static void executeHomeToBasketProductsAction(View v, Bundle data) {
        NavController navController = get(v);
        navController.navigate(R.id.action_navigation_home_to_productsForBasketFragment, data);
    }

    public static void executeBasketProductsToHomeAction(View v) {
        NavController navController = get(v);
        navController.navigate(R.id.action_productsForBasketFragment_to_navigation_home);
    }
}
