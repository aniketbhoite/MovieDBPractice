package com.aniket.moviedbpractice.util

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.View
import android.view.ViewTreeObserver
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

    var spannable = SpannableString(string.trim())

    this.setText(spannable, TextView.BufferType.SPANNABLE)


    this.waitForLayout {
        val start: Int = this.layout.getLineStart(0)
        val noLines =
            if (this.lineCount < this.maxLines)
                this.lineCount else this.maxLines

        val end: Int = this.layout.getLineEnd(noLines - 1)

        val displayed: String = this.text.toString().substring(start, end - 5)
        spannable = SpannableString(" ${displayed.trim()}... ")


        spannable.setSpan(
            ImageSpan(this.context, R.drawable.ic_format_quote_start),
            0,
            1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            ImageSpan(this.context, R.drawable.ic_format_quote_end),
            spannable.length - 1,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        this.setText(spannable, TextView.BufferType.SPANNABLE)
    }


}


/**
 * Easy way to use addOnGlobalLayoutListener
 * Checkout full explanation and alternative ways https://antonioleiva.com/kotlin-ongloballayoutlistener/
 */
inline fun View.waitForLayout(crossinline func: () -> Unit) = with(viewTreeObserver) {
    addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            removeOnGlobalLayoutListener(this)
            func()
        }
    })
}
