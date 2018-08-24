package com.example.mediataptest.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mediataptest.R;

public class BindingHelper {

    @BindingAdapter({"app:svg"})
    public static void setImageViewResource(ImageView imageView, String resource) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.blank_profile);

        Glide.with(imageView.getContext()).load(resource).apply(options).into(imageView);
    }
}
