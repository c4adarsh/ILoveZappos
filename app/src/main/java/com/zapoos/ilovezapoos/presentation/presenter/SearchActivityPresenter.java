package com.zapoos.ilovezapoos.presentation.presenter;

import android.util.Log;

import com.zapoos.ilovezapoos.model.ProductDetails;
import com.zapoos.ilovezapoos.model.ProductList;
import com.zapoos.ilovezapoos.model.Suggestions;
import com.zapoos.ilovezapoos.network.GetProductDetailsService;
import com.zapoos.ilovezapoos.presentation.contract.MainActivityContract;
import com.zapoos.ilovezapoos.presentation.contract.SearchActivityContract;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by adarsh on 2/4/2017.
 */

public class SearchActivityPresenter implements SearchActivityContract.Presenter{

    private static final String TAG = "SearchActivityPresenter";
    Retrofit mRetrofit;

    SearchActivityContract.View mView;

    OkHttpClient mHttpClient;

    List<Subscription> mSubscriptionList = new ArrayList<Subscription>();

    List<Subscription> mSubscriptionProducts = new ArrayList<Subscription>();

    @Inject
    SearchActivityPresenter(Retrofit pRetrofit, SearchActivityContract.View pView, OkHttpClient pHttpClient){
        mRetrofit = pRetrofit;
        mView = pView;
        mHttpClient = pHttpClient;
    }

    @Override
    public void searchProduct(String mText) {

        if(mSubscriptionList.size()!=0){
            for(Subscription mSubscription : mSubscriptionList){
                mSubscription.unsubscribe();
            }
        }

        mSubscriptionList.clear();

        Observable<Suggestions> observable = mRetrofit.create(GetProductDetailsService.class).getSuggestions(mText);

        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Suggestions>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Suggestions suggestions) {
                        mView.showSuggestions(suggestions);
                    }
                });


        mSubscriptionList.add(subscription);
    }



    @Override
    public void getProduct(final String mText) {

        if(mSubscriptionProducts.size()!=0){
            for(Subscription mSubscription : mSubscriptionProducts){
                mSubscription.unsubscribe();
            }
        }

        mSubscriptionProducts.clear();

        Observable<ProductList> observable = mRetrofit.create(GetProductDetailsService.class).getProductList(mText);
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ProductList>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,e.getMessage());
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ProductList pProductlist) {
                        if(pProductlist!=null && pProductlist.getProductList()!=null &&pProductlist.getProductList()                                .size()>0 ) {
                            ProductDetails mDetail = pProductlist.getProductList().get(0);
                            mDetail.setSearchKey(mText);
                            mView.showProductDetails(mDetail);
                        }
                    }
                });


        mSubscriptionProducts.add(subscription);
    }


}
