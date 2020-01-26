package com.example.pricetag.data.requests;

import com.example.pricetag.data.model.ReportProduct;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CalculateItemResponse implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;


    @SerializedName("address")
    private String address;

    @SerializedName("products_to_report")
    private List<ReportProduct> reportProducts;

    @SerializedName("total")
    private float total;

    public CalculateItemResponse(Integer id, String name, String address, List<ReportProduct> reportProducts, float total) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.reportProducts = reportProducts;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ReportProduct> getReportProducts() {
        return reportProducts;
    }

    public void setReportProducts(List<ReportProduct> reportProducts) {
        this.reportProducts = reportProducts;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
