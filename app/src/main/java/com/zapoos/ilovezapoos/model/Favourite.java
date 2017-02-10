package com.zapoos.ilovezapoos.model;

/**
 * Created by adarsh on 2/10/2017.
 */

public class Favourite {

    public String productId;
    public String searchKey;

    public Favourite(String productId, String searchKey) {
        this.productId = productId;
        this.searchKey = searchKey;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
