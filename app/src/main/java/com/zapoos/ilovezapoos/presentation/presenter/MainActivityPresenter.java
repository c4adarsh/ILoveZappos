package com.zapoos.ilovezapoos.presentation.presenter;

import android.util.Log;

import com.zapoos.ilovezapoos.model.ProductDetails;
import com.zapoos.ilovezapoos.model.ProductList;
import com.zapoos.ilovezapoos.network.GetProductDetailsService;
import com.zapoos.ilovezapoos.presentation.contract.MainActivityContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by adarsh on 2/4/2017.
 */

public class MainActivityPresenter  implements MainActivityContract.Presenter{

    Retrofit mRetrofit;

    MainActivityContract.View mView;

    String TAG = "MainActivityPresenter";

    List<Subscription> mSubscriptionProducts = new ArrayList<Subscription>();

    @Inject
    MainActivityPresenter(Retrofit pRetrofit, MainActivityContract.View pView){
        mRetrofit = pRetrofit;
        mView = pView;
    }

    @Override
    public void loadProductDetails(final String mURL) {

        if(mSubscriptionProducts.size()!=0){
            for(Subscription mSubscription : mSubscriptionProducts){
                mSubscription.unsubscribe();
            }
        }

        mSubscriptionProducts.clear();

        Observable<ProductList> observable = mRetrofit.create(GetProductDetailsService.class).getProductList(mURL);
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
                            mDetail.setSearchKey(mURL);
                            mView.showProductDetails(mDetail);
                        }
                    }
                });


        mSubscriptionProducts.add(subscription);
    }
}
