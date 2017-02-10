package com.zapoos.ilovezapoos.util;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.zapoos.ilovezapoos.model.Favourite;
import com.zapoos.ilovezapoos.model.MyFavourite;
import com.zapoos.ilovezapoos.model.ProductDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh on 2/10/2017.
 */

public class FavouriteSharedPreferencesUtil {


    public static void putFavouritesIntoSharedPreferences(MyFavourite myFavourite, SharedPreferences mPrefs) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myFavourite);
        prefsEditor.putString(Constants.FAVOURITES, json);
        prefsEditor.commit();
    }

    public static MyFavourite getFavouritesFromSharedPreferences(SharedPreferences mPrefs) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = mPrefs.getString(Constants.FAVOURITES, "");
        MyFavourite myFavourite = gson.fromJson(json, MyFavourite.class);
        return myFavourite;
    }

    public static boolean isProductInFavourite(String mProductId, SharedPreferences mPrefs){
        MyFavourite myFavourite = getFavouritesFromSharedPreferences(mPrefs);
        if(myFavourite==null){
            return false;
        }
        List<Favourite> mFavouriteList =  myFavourite.getFavourites();
        for(Favourite mFavourite : mFavouriteList){
            if(mFavourite.getProductId().equalsIgnoreCase(mProductId)){
                return true;
            }
        }
        return false;
    }

    public static boolean addProductIntoFavourites(ProductDetails mProductDetails, SharedPreferences mPrefs){
        MyFavourite myFavourite = getFavouritesFromSharedPreferences(mPrefs);
        if(myFavourite==null){
            myFavourite = new MyFavourite();
        }
        List<Favourite> mFavouriteList =  myFavourite.getFavourites();
        mFavouriteList.add(new Favourite(mProductDetails.getProductId(),mProductDetails.getSearchKey()));
        putFavouritesIntoSharedPreferences(myFavourite,mPrefs);
        return true;
    }


    public static boolean removeProductFromFavourites(ProductDetails mProductDetails, SharedPreferences mPrefs){
        MyFavourite myFavourite = getFavouritesFromSharedPreferences(mPrefs);
        if(myFavourite==null){
            return false;
        }
        List<Favourite> mFavouriteList =  myFavourite.getFavourites();
        for(Favourite mFavourite : mFavouriteList){
            if(mFavourite.getProductId().equalsIgnoreCase(mProductDetails.getProductId())){
                mFavouriteList.remove(mFavourite);
                break;
            }
        }

        putFavouritesIntoSharedPreferences(myFavourite,mPrefs);
        return false;
    }

}
