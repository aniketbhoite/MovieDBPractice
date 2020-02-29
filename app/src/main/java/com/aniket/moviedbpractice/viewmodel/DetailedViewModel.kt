package com.aniket.moviedbpractice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aniket.moviedbpractice.repositories.DetailedRepository
import com.aniket.moviedbpractice.responses.*
import com.aniket.moviedbpractice.responses.base.Result
import com.aniket.moviedbpractice.util.exhaustive
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailedViewModel(
    private val repo: DetailedRepository,
    private val movieData: MovieData
) : ViewModel() {


    private val movieDetailsData: MutableLiveData<MovieDetailsData> by lazy {
        MutableLiveData<MovieDetailsData>().also {
            loadMovieDetailsData()
        }
    }

    private val similarMoviesList: MutableLiveData<List<MovieData>> by lazy {
        MutableLiveData<List<MovieData>>().also {
            loadSimilarMovie()
        }
    }

    fun getDetailsData(): LiveData<MovieDetailsData> = movieDetailsData

    fun getSimilarMovies(): LiveData<List<MovieData>> = similarMoviesList


    private fun loadMovieDetailsData() {
        viewModelScope.launch {
            val synopsisResult = async { repo.getSynopsis(movieData.id) }
            val reviewsResult = async { repo.getReviews(movieData.id) }
            val creditsResult = async { repo.getCredits(movieData.id) }

            checkResult(synopsisResult.await(), reviewsResult.await(), creditsResult.await())
        }
    }

    private fun loadSimilarMovie() {
        viewModelScope.launch {

            val result = repo.getSimilarMovies(movieData.id)
            when (result) {
                is Result.Success -> {
                    result.data.results.let {
                        similarMoviesList.value = it.toList()
                    }
                }
                is Result.Error -> {

                }
            }.exhaustive
        }
    }

    private fun checkResult(
        synopsisResult: Result<MovieSynopsis>,
        reviewsResult: Result<ReviewsResponse>,
        creditsResult: Result<Credits>
    ) {
        if (synopsisResult is Result.Success) {
            val movieSynopsis = synopsisResult.data
            var reviews: ReviewsResponse? = null
            if (reviewsResult is Result.Success) {
                reviews = reviewsResult.data
            }
            var credits: Credits? = null
            if (creditsResult is Result.Success) {
                credits = creditsResult.data
            }

            val tempData =
                MovieDetailsData(
                    movieSynopsis,
                    reviewsResponse = reviews,
                    credits = credits
                )
            movieDetailsData.value = tempData
        }

    }
}