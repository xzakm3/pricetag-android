package com.example.pricetag.data.model;

import com.google.gson.annotations.SerializedName;

public class ProductInShoppingList implements ActionFragmentItemable {
    @SerializedName("id")
    private Integer id;

    @SerializedName("product_id")
    private Integer productId;

    @SerializedName("quantity")
    private Integer quantity;

    @SerializedName("_destroy")
    private Integer destroy;

    @SerializedName("product")
    private Product product;

    private transient String productName;


    public ProductInShoppingList(Integer id, Integer productId, Integer quantity, Integer destroy, Product product, String productName) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.destroy = destroy;
        this.product = product;
        this.productName = productName;
    }

    public ProductInShoppingList(Integer id, Integer productId, Integer quantity, Integer destroy) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.destroy = destroy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public Integer getDestroy() {
        return destroy;
    }

    public void setDestroy(Integer destroy) {
        this.destroy = destroy;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String getName() {
        return productName;
    }

    @Override
    public float getNumber() {
        return quantity.floatValue();
    }

    public void syncProduct() {
        productName = product.getName();
        productId = product.getId();
        product = null;
    }
}
