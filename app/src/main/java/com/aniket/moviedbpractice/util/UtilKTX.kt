package com.aniket.moviedbpractice.util

import android.graphics.Color
import android.os.Build
import android.text.SpannableString
import android.text.Spanned
import android.text.style.QuoteSpan
import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("quotedText", requireAll = true)
fun TextView.setQuotedText(string: String) {

    val spannable = SpannableString(string)
    spannable.setSpan(
        getQuotedSpan(), 0, spannable.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    this.text = spannable

}

fun getQuotedSpan(): QuoteSpan {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        QuoteSpan(Color.RED, 6, 10)

    } else
        QuoteSpan(Color.RED)
}