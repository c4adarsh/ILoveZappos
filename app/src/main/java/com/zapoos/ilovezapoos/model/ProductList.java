package com.zapoos.ilovezapoos.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by adarsh on 2/7/2017.
 */

public class ProductList {
    @SerializedName("results")
    private List<ProductDetails> productList;

    public ProductList(List<ProductDetails> productList) {
        this.productList = productList;
    }

    public List<ProductDetails> getProductList() {
        return productList;
    }
}
