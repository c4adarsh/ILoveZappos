package com.zapoos.ilovezapoos.presentation.ui.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zapoos.ilovezapoos.ILoveZapoosApplication;
import com.zapoos.ilovezapoos.R;
import com.zapoos.ilovezapoos.dagger.Component.DaggerMainActivityComponent;
import com.zapoos.ilovezapoos.dagger.Module.MainActivityModule;
import com.zapoos.ilovezapoos.databinding.ActivityMainBinding;
import com.zapoos.ilovezapoos.model.ProductDetails;
import com.zapoos.ilovezapoos.presentation.contract.MainActivityContract;
import com.zapoos.ilovezapoos.presentation.presenter.MainActivityPresenter;
import com.zapoos.ilovezapoos.util.CartSharedPreferencesUtil;
import com.zapoos.ilovezapoos.util.Constants;
import com.zapoos.ilovezapoos.util.FavouriteSharedPreferencesUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.inject.Inject;

/**
 * Created by adarsh on 2/3/2017.
 */

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{

    private static final String TAG = "MainActivity";
    @Inject
    MainActivityPresenter mMainActivityPresenter;

    @Inject
    SharedPreferences mSharedPrefernces;

    private Menu menu;

    ProductDetails model;

    ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent.builder()
                .networkComponent(((ILoveZapoosApplication)getApplicationContext()).getNetworkComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build().inject(this);

        String mSearchString = checkIfFromExternalLink();
        if(mSearchString==null){
            initializeUI();
        }else{
            mMainActivityPresenter.loadProductDetails(mSearchString);
            //Hide the view with a splash screen for some time Until the Data comes
            //If data doesn't come back show an error message
        }
        //Check if Item in cart from shared preferences and set all the required values
    }

    private void initializeUI() {
        bindActivity();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fitIamgeToScreen();
        if(mSharedPrefernces!=null){
            callFavouriteMenuSetter();
            callCartMenuSetter();
            isItemInCart();
        }
    }

    private void isItemInCart() {
        boolean isStarred = CartSharedPreferencesUtil.isProductInCart(model.getProductId(),mSharedPrefernces);
        if(model!=null){
            model.setInCart(isStarred);
        }
    }

    private void callCartMenuSetter() {
        if(model!=null && menu!=null){
            int count = CartSharedPreferencesUtil.getCartItemsCount(mSharedPrefernces);
            if(menu!=null){
                MenuItem menuItem = menu.findItem(R.id.action_cart);
                menuItem.setIcon(buildCounterDrawable(count, R.drawable.ic_local_grocery_store_black_36dp));
            }
        }
    }


    public void callFavouriteMenuSetter(){
        if(model!=null && menu!=null){
            boolean isStarred = FavouriteSharedPreferencesUtil.isProductInFavourite(model.getProductId(),mSharedPrefernces);
            model.setStarred(isStarred);
            if(menu!=null){
                MenuItem menuItem = menu.findItem(R.id.action_favourite);
                if(isStarred){
                    menuItem.setIcon(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
                }else{
                    menuItem.setIcon(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
                }
            }
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            String searchString = uri.getQueryParameter(Constants.SEARCH); // "str" is set
            Log.i(TAG,searchString);
            if(searchString!=null) {
                mMainActivityPresenter.loadProductDetails(searchString);
            }
        }

    }

    private String checkIfFromExternalLink(){
        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            String searchString = uri.getQueryParameter(Constants.SEARCH); // "str" is set
            Log.i(TAG,searchString);
            if(searchString == null){
                return Constants.NOT_FOUND;
            }
            return searchString;
        }
        return null;
    }

    private void bindActivity() {
        if(model==null) {
            model = (ProductDetails) getIntent().getSerializableExtra(Constants.PRODUCT_DETAILS);
        }
        if(model != null) {
            ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
            binding.setProduct(model);
        }else{
            // show the error screen on UI
        }
    }

    private void fitIamgeToScreen() {
        ImageView scrollImage = (ImageView) findViewById(R.id.scroll_image);
        int height = getResources().getDisplayMetrics().heightPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) scrollImage.getLayoutParams();
        params.height = height/2;
        scrollImage.setLayoutParams(params);
        scrollImage.requestLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Fetch and store ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_share);

        callCartMenuSetter();

        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        callFavouriteMenuSetter();
        return true;
    }


    private Drawable buildCounterDrawable(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.cart_menu_item, null);
        TextView textView = (TextView) view.findViewById(R.id.count);
        textView.setText("" + count);

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_favourite){
            if(!model.isStarred()) {
                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
                model.setStarred(true);
                FavouriteSharedPreferencesUtil.addProductIntoFavourites(model,mSharedPrefernces);
            }else{
                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
                model.setStarred(false);
                FavouriteSharedPreferencesUtil.removeProductFromFavourites(model,mSharedPrefernces);
            }
        }
        else if(id == android.R.id.home){
            finish();
        }
        else if(id == R.id.action_share){
            try {

                if(model!=null) {
                    getURLString(model);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }


        return super.onOptionsItemSelected(item);
    }

    @NonNull
    public void getURLString(ProductDetails mModel) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType(Constants.TEXT);
        i.putExtra(Intent.EXTRA_SUBJECT, Constants.MY_APPLICATION);
        String mUrl = "";
        try {
             mUrl = URLEncoder.encode(mModel.getSearchKey(),Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String link  = Constants.RECOMMENDATION + Constants.BASE_SEARCH_URL + mUrl+"\n\n";
        i.putExtra(Intent.EXTRA_TEXT, link);
        startActivity(Intent.createChooser(i,Constants.CHOOSE_ONE));
    }

    @Override
    public void showProductDetails(ProductDetails productDetails) {
        model = productDetails;
        initializeUI();
    }

    @Override
    public void showError(String message) {

    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public void showComplete() {
        //Log.i(TAG,"showComplete");
    }

    public void onClickCart(View v) {
        //check if the element is there in shared preferences
        if(model.isInCart()){
            model.setInCart(false);
            CartSharedPreferencesUtil.removeProductFromCart(model,mSharedPrefernces);
            callCartMenuSetter();
        }else{
            model.setInCart(true);
            CartSharedPreferencesUtil.addProductIntoCart(model,mSharedPrefernces);
            callCartMenuSetter();
        }
    }
}
