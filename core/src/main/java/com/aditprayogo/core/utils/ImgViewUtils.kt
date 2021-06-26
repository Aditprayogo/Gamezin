package com.aditprayogo.core.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Aditiya Prayogo.
 */

fun ImageView.load(imageSource : String?) {
    Glide.with(context.applicationContext)
        .load(imageSource)
        .into(this)
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setGone() {
    visibility = View.GONE
}

