package com.zapoos.ilovezapoos.presentation.contract;

import android.graphics.Bitmap;

import com.zapoos.ilovezapoos.model.ProductDetails;

import java.util.List;

/**
 * Created by adarsh on 2/3/2017.
 */

public class MainActivityContract {

    public interface View {

        void showProductDetails(ProductDetails posts);

        void showError(String message);

        void showComplete();
    }

    public interface Presenter {
        void loadProductDetails(String mURL);
    }
}
