package com.example.pricetag.utils;

import com.example.pricetag.data.interfaces.ItemToCalculatable;
import com.example.pricetag.data.model.Item;
import com.example.pricetag.data.model.ItemToCalculate;

import java.util.ArrayList;
import java.util.List;

public class ProductToItemToCalculateMapper {
    public static List<ItemToCalculatable> map(List<? extends Item> products) {
        List<ItemToCalculatable> itemsToCalculate = new ArrayList<>();
        products.forEach((product) ->
                itemsToCalculate.add(new ItemToCalculate(product.getId(), product.getName()))
        );
        return itemsToCalculate;
    }
}
