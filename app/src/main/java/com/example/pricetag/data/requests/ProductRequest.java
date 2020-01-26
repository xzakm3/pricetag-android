package com.example.pricetag.data.requests;

import com.example.pricetag.data.model.Product;
import com.google.gson.annotations.SerializedName;

public class ProductRequest extends BaseRequest  {

    @SerializedName("product")
    private Product product;

    public ProductRequest(Product product) {
        this.product = product;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean validate() {
        if (product.getName().equals("")) {
            return false;
        }

        return true;
    }
}
