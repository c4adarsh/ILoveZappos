package com.zapoos.ilovezapoos.presentation.ui.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Point;
import android.view.Display;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by adarsh on 2/7/2017.
 */

public class ImageBindingAdapter {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {

        if (!url.equals("")) {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
    }
}


