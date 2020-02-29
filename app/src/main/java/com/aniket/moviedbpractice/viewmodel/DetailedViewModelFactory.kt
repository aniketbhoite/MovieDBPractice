package com.aniket.moviedbpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aniket.moviedbpractice.repositories.DetailedRepository

class DetailedViewModelFactory(private val repo: DetailedRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailedViewModel(repo) as T
    }
}