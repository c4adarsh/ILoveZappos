package com.zapoos.ilovezapoos.presentation.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;
import com.zapoos.ilovezapoos.R;
import com.zapoos.ilovezapoos.util.Constants;

/**
 * Created by adarsh on 2/6/2017.
 */

public class CustomSuggestionsAdapter extends SuggestionsAdapter<String, CustomSuggestionsAdapter.SuggestionHolder> {

    private SuggestionsAdapter.OnItemViewClickListener listener;

    private String mUnderline = Constants.WHITE;

    public CustomSuggestionsAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    public void setListener(SuggestionsAdapter.OnItemViewClickListener listener) {
        this.listener = listener;
    }

    public void setUnderlineColor(String mColor){
        mUnderline = mColor;
    }

    @Override
    public void onBindSuggestionHolder(String suggestion, SuggestionHolder holder, int position) {
        holder.title.setText(suggestion);
        if(mUnderline == Constants.WHITE){
            holder.view.setBackgroundColor(Color.WHITE);
        }else{
            holder.view.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public SuggestionHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.suggestion_row, parent, false);
        return new SuggestionHolder(view);
    }

    @Override
    public int getSingleViewHeight() {
        return 60;
    }

    class SuggestionHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected View view;

        public SuggestionHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.my_list_item);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                   listener.OnItemClickListener(getAdapterPosition(),v);
                }
            } );
            view = (View) itemView.findViewById(R.id.view);
        }
    }
}
