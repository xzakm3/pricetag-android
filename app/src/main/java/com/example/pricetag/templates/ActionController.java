package com.example.pricetag.templates;

import android.view.View;
import android.os.Bundle;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.pricetag.R;
import com.example.pricetag.activities.ApplicationWrapper;
import com.example.pricetag.utils.ItemType;

import es.dmoral.toasty.Toasty;

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

    public static void executeFromAction(View view, ActionParameters parameters) {
        NavController navController = Navigation.findNavController(view);

        ItemType itemType = parameters.getItemType();

        if(itemType.equals(ItemType.PRODUCT)) {
            navController.navigate(R.id.action_item_action_fragment_to_navigation_products);
        } else if(itemType.equals(ItemType.SHOP)) {
            navController.navigate(R.id.action_item_action_fragment_to_navigation_shops);
        } else if(itemType.equals(ItemType.SHOPPINGLIST)) {
            navController.navigate(R.id.action_item_action_fragment_to_navigation_lists);
        }
    }

    public static void executeFromItemByConfirmButtonAction(View view, ActionParameters parameters) {
        String message = parameters.typeOfAction.equals("edit") ? "Item was edited successfuly!" : "Item was created successfuly";
        Toasty.success(ApplicationWrapper.getAppContext(), message, Toast.LENGTH_SHORT, true).show();
        executeFromAction(view, parameters);
    }

    public static void executeBasketoToResultsAction(View v, Bundle data) {
        NavController navController = get(v);
        navController.navigate(R.id.action_navigation_home_to_calculatedResultsFragment, data);
    }

    public static void executeFromItemByCancelButtonAction(View view, ActionParameters parameters) {
        executeFromAction(view, parameters);
    }

    public static void executeFromCalculatedResultsToHome(View view) {
        NavController navController = get(view);
        navController.navigate(R.id.action_calculatedResultsFragment_to_navigation_home);
    }
}
