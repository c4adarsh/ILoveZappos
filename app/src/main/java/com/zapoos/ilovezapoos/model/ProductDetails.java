package com.zapoos.ilovezapoos.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.zapoos.ilovezapoos.BR;

import java.io.Serializable;

/**
 * Created by adarsh on 2/3/2017.
 */

public class ProductDetails extends BaseObservable implements Serializable{
    private  String brandName;
    private  String thumbnailImageUrl;
    private  String productId;
    private  String originalPrice;
    private  String styleId;
    private  String colorId;
    private  String price;
    private  String percentOff;
    private  String productUrl;
    private  String productName;

    //External Elements
    private  boolean starred = false;
    private  boolean inCart = false;

    //FOor storing the Searchkey
    private  String searchKey;

    public ProductDetails(String brandName, String thumbnailImageUrl, String productId, String originalPrice, String styleId, String colorId, String price, String percentOff, String productUrl, String productName) {
        this.brandName = brandName;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.productId = productId;
        this.originalPrice = originalPrice;
        this.styleId = styleId;
        this.colorId = colorId;
        this.price = price;
        this.percentOff = percentOff;
        this.productUrl = productUrl;
        this.productName = productName;
    }

    @Bindable
    public String getBrandName() {
        return brandName;
    }

    @Bindable
    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    @Bindable
    public String getProductId() {
        return productId;
    }

    @Bindable
    public String getOriginalPrice() {
        return originalPrice;
    }

    @Bindable
    public String getStyleId() {
        return styleId;
    }

    @Bindable
    public String getColorId() {
        return colorId;
    }

    @Bindable
    public String getPrice() {
        return price;
    }

    @Bindable
    public String getPercentOff() {
        return percentOff;
    }

    @Bindable
    public String getProductUrl() {
        return productUrl;
    }

    @Bindable
    public String getProductName() {
        return productName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
        notifyPropertyChanged(BR.brandName);
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
        notifyPropertyChanged(BR.thumbnailImageUrl);
    }

    public void setProductId(String productId) {
        this.productId = productId;
        notifyPropertyChanged(BR.productId);
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
        notifyPropertyChanged(BR.originalPrice);
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
        notifyPropertyChanged(BR.styleId);
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
        notifyPropertyChanged(BR.colorId);
    }

    public void setPrice(String price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
        notifyPropertyChanged(BR.percentOff);
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
        notifyPropertyChanged(BR.productUrl);
    }

    public void setProductName(String productName) {
        this.productName = productName;
        notifyPropertyChanged(BR.productName);
    }

    @Bindable
    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
        notifyPropertyChanged(BR.starred);
    }

    @Bindable
    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
        notifyPropertyChanged(BR.inCart);
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
