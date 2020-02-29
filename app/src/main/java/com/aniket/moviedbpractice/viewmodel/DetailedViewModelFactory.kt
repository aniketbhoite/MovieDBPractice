package com.aniket.moviedbpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aniket.moviedbpractice.repositories.DetailedRepository
import com.aniket.moviedbpractice.responses.MovieData

class DetailedViewModelFactory(private val repo: DetailedRepository, private val movieData: MovieData) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailedViewModel(repo, movieData) as T
    }
}