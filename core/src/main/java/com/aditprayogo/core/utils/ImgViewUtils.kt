package com.aditprayogo.core.utils

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