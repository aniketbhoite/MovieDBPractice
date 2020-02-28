package com.aniket.moviedbpractice.viewmodel

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.aniket.moviedbpractice.responses.MovieData

class MovieItemViewModel(
    val movieData: MovieData,
    private val viewModel: MovieListViewModel
) : BaseObservable() {


    val onMovieClickListener: View.OnClickListener
        @Bindable
        get() = View.OnClickListener {
            viewModel.openMovie(movieData)
        }
}