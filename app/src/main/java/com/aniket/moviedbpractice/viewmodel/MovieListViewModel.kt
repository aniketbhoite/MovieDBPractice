package com.aniket.moviedbpractice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aniket.moviedbpractice.repositories.MovieListRepository
import com.aniket.moviedbpractice.responses.MovieData
import com.aniket.moviedbpractice.responses.base.Result
import com.aniket.moviedbpractice.util.exhaustive
import kotlinx.coroutines.launch

class MovieListViewModel(private val repo: MovieListRepository) : ViewModel() {

    private val movies: MutableLiveData<List<MovieData>> by lazy {
        MutableLiveData<List<MovieData>>().also {
            loadNowPlayingMovies()
        }
    }


    fun getNowPlayingMovies(): LiveData<List<MovieData>> {
        return movies
    }

    private fun loadNowPlayingMovies() {
        viewModelScope.launch {
            val result = repo.getPopularMovies()
            when (result) {
                is Result.Success -> {
                    result.data.results?.let {
                        val temp: MutableList<MovieData> = mutableListOf()
                        it.map { md ->
                            temp.add(md)
                        }
                        movies.value = temp
                    }
                }
                is Result.Error -> {

                }
            }.exhaustive
        }
    }
}