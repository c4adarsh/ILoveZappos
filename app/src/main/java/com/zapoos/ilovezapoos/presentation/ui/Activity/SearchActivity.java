package com.zapoos.ilovezapoos.presentation.ui.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;
import com.zapoos.ilovezapoos.ILoveZapoosApplication;
import com.zapoos.ilovezapoos.R;
import com.zapoos.ilovezapoos.dagger.Component.DaggerSearchActivityComponent;
import com.zapoos.ilovezapoos.dagger.Module.SearchActivityModule;
import com.zapoos.ilovezapoos.model.ProductDetails;
import com.zapoos.ilovezapoos.model.Suggestions;
import com.zapoos.ilovezapoos.presentation.contract.SearchActivityContract;
import com.zapoos.ilovezapoos.presentation.presenter.SearchActivityPresenter;
import com.zapoos.ilovezapoos.presentation.ui.adapters.CustomSuggestionsAdapter;
import com.zapoos.ilovezapoos.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by adarsh on 2/3/2017.
 */

public class SearchActivity extends AppCompatActivity implements SearchActivityContract.View,MaterialSearchBar.OnSearchActionListener, SuggestionsAdapter.OnItemViewClickListener{

    private static final String TAG = "SearchActivity";
    @Inject
    SearchActivityPresenter mSearchActivityPresenter;

    private MaterialSearchBar searchBar;

    CustomSuggestionsAdapter customSuggestionsAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        DaggerSearchActivityComponent.builder()
                .networkComponent(((ILoveZapoosApplication)getApplicationContext()).getNetworkComponent())
                .searchActivityModule(new SearchActivityModule(this))
                .build().inject(this);
        initializeSearchBar();
        initializeAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(searchBar!=null && searchBar.getText().toString()!=null &&
                searchBar.getText().toString().length()!=0){

                searchBar.enableSearch();
                callSearch(searchBar.getText().toString());

        }
    }

    private void initializeSearchBar() {
        searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        searchBar.setSpeechMode(true);
        searchBar.setOnSearchActionListener(this);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                callSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initializeAdapter() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        customSuggestionsAdapter = new CustomSuggestionsAdapter(inflater);
        customSuggestionsAdapter.setListener(this);
        searchBar.setCustomSuggestionAdapter(customSuggestionsAdapter);
        List<String> mList = new ArrayList<String>();
        for(int i=0;i<10;i++){
            mList.add(Constants.BLANK_STRING);
        }

        customSuggestionsAdapter.setUnderlineColor(Constants.WHITE);
        customSuggestionsAdapter.setSuggestions(mList);
        customSuggestionsAdapter.notifyDataSetChanged();
    }

    private void openSearchArea(List<String> suggestions){

        if(suggestions.size()<10){
            for(int i=suggestions.size();i<10;i++){
                suggestions.add(Constants.BLANK_STRING);
            }
        }

            customSuggestionsAdapter.setSuggestions(suggestions);
            customSuggestionsAdapter.notifyDataSetChanged();
    }

    private void callSearch(String newText) {
        mSearchActivityPresenter.searchProduct(newText);
    }

    @Override
    public void showSuggestions(Suggestions pSuggestions) {
        List<String> suggestions = pSuggestions.getResults();
        openSearchArea(suggestions);
    }

    @Override
    public void showProductDetails(ProductDetails mProductDetails) {
        Intent myIntent = new Intent(SearchActivity.this, MainActivity.class);
        myIntent.putExtra(Constants.PRODUCT_DETAILS, mProductDetails); //Optional parameters
        SearchActivity.this.startActivity(myIntent);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showComplete() {
       // Log.i(TAG,"showComplete");
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
       //Call the productGetter method
        mSearchActivityPresenter.getProduct(text.toString());
        List<String> mList = new ArrayList<String>();
        for(int i=0;i<10;i++){
            mList.add(Constants.BLANK_STRING);
        }
        //searchBar.setMaxSuggestionCount(1);
        customSuggestionsAdapter.setUnderlineColor(Constants.WHITE);
        customSuggestionsAdapter.setSuggestions(mList);
        customSuggestionsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        List<String> mList = new ArrayList<String>();
        for(int i=0;i<10;i++){
            mList.add(Constants.BLANK_STRING);
        }
        //searchBar.setMaxSuggestionCount(1);
        customSuggestionsAdapter.setUnderlineColor(Constants.WHITE);
        customSuggestionsAdapter.setSuggestions(mList);
        customSuggestionsAdapter.notifyDataSetChanged();

    }

    @Override
    public void OnItemClickListener(int position, View v) {

        TextView mTv = (TextView) v.findViewById(R.id.my_list_item);
        searchBar.setText(mTv.getText().toString());
        mSearchActivityPresenter.getProduct(mTv.getText().toString());
        ////Call the productGetter method
    }

    @Override
    public void OnItemDeleteListener(int position, View v) {

    }
}
