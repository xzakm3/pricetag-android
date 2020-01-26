package com.example.pricetag.data.requests;

import com.example.pricetag.data.model.CalculateProduct;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CalculateItemsRequest extends BaseRequest {
    @SerializedName("products")
    private List<CalculateProduct> calculateProductList;

    public CalculateItemsRequest(List<CalculateProduct> calculateProductList) {
        this.calculateProductList = calculateProductList;
    }

    public List<CalculateProduct> getCalculateProductList() {
        return calculateProductList;
    }

    public void setCalculateProductList(List<CalculateProduct> calculateProductList) {
        this.calculateProductList = calculateProductList;
    }

    @Override
    public boolean validateRequest() {
        return true;
    }
}
