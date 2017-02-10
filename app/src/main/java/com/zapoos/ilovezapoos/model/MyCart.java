package com.zapoos.ilovezapoos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh on 2/10/2017.
 */

public class MyCart {

    List<Cart> cart = new ArrayList<Cart>();

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }
}
