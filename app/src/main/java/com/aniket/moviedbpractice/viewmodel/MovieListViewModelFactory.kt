package com.aniket.moviedbpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aniket.moviedbpractice.repositories.MovieListReposirory

class MovieListViewModelFactory(val repo: MovieListReposirory) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        MovieListViewModel(repo) as T
}