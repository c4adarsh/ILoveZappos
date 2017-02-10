package com.zapoos.ilovezapoos.presentation.ui.adapters;

import android.databinding.BindingAdapter;
import android.support.design.widget.FloatingActionButton;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zapoos.ilovezapoos.R;
import com.zapoos.ilovezapoos.util.Constants;

import static com.zapoos.ilovezapoos.R.anim.fab_scaledown;

/**
 * Created by adarsh on 2/7/2017.
 */

public class AnimationBindingAdapter {
    @BindingAdapter({"inCart"})
    public static void changeFloatingAnimation(FloatingActionButton mFloatingButton, boolean isStarred) {
        Animation fab_scaledown = AnimationUtils.loadAnimation(mFloatingButton.getContext(), R.anim.fab_scaledown);
        if(isStarred){
            mFloatingButton.setImageResource(R.drawable.ic_check_black_24dp);
            mFloatingButton.startAnimation(fab_scaledown);
        }else{
            mFloatingButton.setImageResource(R.drawable.ic_local_grocery_store_black_36dp);
            mFloatingButton.startAnimation(fab_scaledown);
        }
    }
}


