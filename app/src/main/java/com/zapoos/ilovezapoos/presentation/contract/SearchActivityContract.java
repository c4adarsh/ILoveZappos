package com.zapoos.ilovezapoos.presentation.contract;

import com.zapoos.ilovezapoos.model.ProductDetails;
import com.zapoos.ilovezapoos.model.Suggestions;

import java.util.List;

/**
 * Created by adarsh on 2/3/2017.
 */

public class SearchActivityContract {

    public interface View {
        void showSuggestions(Suggestions mSuggestions);

        void showProductDetails(ProductDetails mProductDetails);

        void showError(String message);

        void showComplete();
    }

    public interface Presenter {
        void searchProduct(String mText);

        void getProduct(String mText);
    }
}
