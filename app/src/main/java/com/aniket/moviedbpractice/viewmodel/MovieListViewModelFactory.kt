package com.aniket.moviedbpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aniket.moviedbpractice.repositories.MovieListRepository

class MovieListViewModelFactory(val repo: MovieListRepository) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        MovieListViewModel(repo) as T
}