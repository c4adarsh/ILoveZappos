package com.zapoos.ilovezapoos.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zapoos.ilovezapoos.R;
import com.zapoos.ilovezapoos.util.listeners.OnItemClickListener;

import java.util.List;

/**
 * Created by adarsh on 2/6/2017.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.CustomViewHolder> {
    private List<String> mSuggestionList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public SearchRecyclerAdapter(Context pContext, List<String> pSuggestionList) {
        mSuggestionList = pSuggestionList;
        mContext = pContext;
    }

    @Override
    public SearchRecyclerAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.suggestion_row, viewGroup, false);
        SearchRecyclerAdapter.CustomViewHolder viewHolder = new SearchRecyclerAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SearchRecyclerAdapter.CustomViewHolder customViewHolder, int i) {
        final String mItem = mSuggestionList.get(i);

        //Setting text view title
        customViewHolder.textView.setText(mItem);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(mItem);
            }
        };
        customViewHolder.textView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return (null != mSuggestionList ? mSuggestionList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.my_list_item);
        }
    }


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
