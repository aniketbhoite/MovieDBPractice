package com.aniket.moviedbpractice.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.aniket.moviedbpractice.R

val <T> T.exhaustive: T
    get() = this

@BindingAdapter("loadUrl", requireAll = true)
fun ImageView.loadUrl(imagePath: String?) {

    if (imagePath == null)
        return

    var url = imagePath
    if (!imagePath.contains("https://"))
        url = MOVIEDB_IMAGE_PRFIX_LINK + imagePath


    this.load(url) {
        placeholder(R.drawable.ic_placeholder)
    }
}