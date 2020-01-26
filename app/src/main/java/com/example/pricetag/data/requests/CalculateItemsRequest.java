package com.example.pricetag.data.requests;

import android.widget.Toast;

import com.example.pricetag.activities.ApplicationWrapper;
import com.example.pricetag.data.model.CalculateProduct;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import es.dmoral.toasty.Toasty;

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
        if(calculateProductList.size() == 0) {
            String message = "You have add some products to basket to calculate price";
            Toasty.error(ApplicationWrapper.getAppContext(), message, Toast.LENGTH_SHORT, true).show();
        }
        return true;
    }
}
