package com.zapoos.ilovezapoos.util;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.zapoos.ilovezapoos.model.Cart;
import com.zapoos.ilovezapoos.model.Favourite;
import com.zapoos.ilovezapoos.model.MyCart;
import com.zapoos.ilovezapoos.model.MyFavourite;
import com.zapoos.ilovezapoos.model.ProductDetails;

import java.util.List;

/**
 * Created by adarsh on 2/10/2017.
 */

public class CartSharedPreferencesUtil {


    public static void putCartIntoSharedPreferences(MyCart myCart, SharedPreferences mPrefs) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myCart);
        prefsEditor.putString(Constants.CART, json);
        prefsEditor.commit();
    }

    public static MyCart getCartFromSharedPreferences(SharedPreferences mPrefs) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = mPrefs.getString(Constants.CART, "");
        MyCart myCart = gson.fromJson(json, MyCart.class);
        return myCart;
    }

    public static boolean isProductInCart(String mProductId, SharedPreferences mPrefs){
        MyCart myCart = getCartFromSharedPreferences(mPrefs);
        if(myCart==null){
            return false;
        }
        List<Cart> mCartList =  myCart.getCart();
        for(Cart mCart : mCartList){
            if(mCart.getProductId().equalsIgnoreCase(mProductId)){
                return true;
            }
        }
        return false;
    }

    public static boolean addProductIntoCart(ProductDetails mProductDetails, SharedPreferences mPrefs){
        MyCart myCart = getCartFromSharedPreferences(mPrefs);
        if(myCart==null){
            myCart = new MyCart();
        }
        List<Cart> mCartList =  myCart.getCart();
        mCartList.add(new Cart(mProductDetails.getProductId(),mProductDetails.getSearchKey()));
        putCartIntoSharedPreferences(myCart,mPrefs);
        return true;
    }


    public static boolean removeProductFromCart(ProductDetails mProductDetails, SharedPreferences mPrefs){
        MyCart myCart = getCartFromSharedPreferences(mPrefs);
        if(myCart==null){
            return false;
        }
        List<Cart> mCartList =  myCart.getCart();
        for(Cart mCart : mCartList){
            if(mCart.getProductId().equalsIgnoreCase(mProductDetails.getProductId())){
                mCartList.remove(mCart);
                break;
            }
        }

        putCartIntoSharedPreferences(myCart,mPrefs);
        return false;
    }

    public static int getCartItemsCount(SharedPreferences mPrefs){
        MyCart myCart = getCartFromSharedPreferences(mPrefs);
        if(myCart==null){
            return 0;
        }else{
            return myCart.getCart().size();
        }
    }

    public static boolean clearCart(SharedPreferences mPrefs){
        MyCart myCart = getCartFromSharedPreferences(mPrefs);
        if(myCart==null){
            return false;
        }
        List<Cart> mCartList =  myCart.getCart();
        mCartList.clear();
        putCartIntoSharedPreferences(myCart,mPrefs);
        return false;
    }

}
