package com.zapoos.ilovezapoos.network;

import com.zapoos.ilovezapoos.model.ProductDetails;
import com.zapoos.ilovezapoos.model.ProductList;
import com.zapoos.ilovezapoos.model.Suggestions;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by adarsh on 2/4/2017.
 */

public interface GetProductDetailsService {
    @GET("Search?key=b743e26728e16b81da139182bb2094357c31d331")
    Observable<ProductList> getProductList(@Query("term") String mTerm);

    @GET("AutoComplete?key=b743e26728e16b81da139182bb2094357c31d331")
    Observable<Suggestions> getSuggestions(@Query("term") String mTerm);
}
