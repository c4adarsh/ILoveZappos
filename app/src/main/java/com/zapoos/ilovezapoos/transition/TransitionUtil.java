package com.zapoos.ilovezapoos.transition;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.transitionseverywhere.TransitionManager;

/**
 * Created by adarsh on 2/6/2017.
 */

public class TransitionUtil {

    public static void moveToCenter(ViewGroup mViewGroup, View mSearchView) {
        TransitionManager.beginDelayedTransition(mViewGroup);
        RelativeLayout.LayoutParams mPosition = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        mPosition.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        mSearchView.setLayoutParams(mPosition);
    }


    public static void moveToTop(ViewGroup mViewGroup, View mSearchView) {
        TransitionManager.beginDelayedTransition(mViewGroup);
        RelativeLayout.LayoutParams mPosition = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        mPosition.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        mSearchView.setLayoutParams(mPosition);
    }
}
