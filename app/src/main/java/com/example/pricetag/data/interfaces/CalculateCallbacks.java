package com.example.pricetag.data.interfaces;

import android.view.View;

import com.example.pricetag.data.requests.CalculateItemResponse;

import java.util.List;

public interface CalculateCallbacks {
    void setCalculateData(List<CalculateItemResponse> data, View view);
}
