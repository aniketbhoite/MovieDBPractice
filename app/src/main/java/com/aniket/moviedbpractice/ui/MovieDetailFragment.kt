package com.aniket.moviedbpractice.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aniket.moviedbpractice.R
import com.aniket.moviedbpractice.databinding.FragmentMovieDetailBinding
import com.aniket.moviedbpractice.network.MovieApiClient
import com.aniket.moviedbpractice.repositories.DetailedRepository
import com.aniket.moviedbpractice.responses.MovieData
import com.aniket.moviedbpractice.responses.base.EventObserver
import com.aniket.moviedbpractice.ui.MovieAdapter.Companion.MOVIE_THUMB_ITEM_TYPE
import com.aniket.moviedbpractice.util.MarginItemDecoration
import com.aniket.moviedbpractice.util.exhaustive
import com.aniket.moviedbpractice.viewmodel.DetailedViewModel
import com.aniket.moviedbpractice.viewmodel.DetailedViewModelFactory


class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    private lateinit var movieData: MovieData

    private val viewModel: DetailedViewModel by viewModels {
        DetailedViewModelFactory(
            DetailedRepository(MovieApiClient.apiServices),
            movieData
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_detail, container, false
        )

        binding.viewModel = viewModel
        binding.movieData = movieData
        binding.lifecycleOwner = this

        initView()

        return binding.root
    }

    private fun initView() {
        viewModel.getDetailsData().observe(this, Observer {
            val data = it
            if (data.reviewsResponse == null || data.reviewsResponse.results.isEmpty())
                hideReviewSection()
            else {
                binding.rvReviews.apply {
                    layoutManager = LinearLayoutManager(activity)
                    setHasFixedSize(true)
                    adapter = ReviewsAdapter(data.reviewsResponse.results)
                }
            }
        })


        viewModel.getSimilarMovies().observe(this, Observer {
            val list = it
            if (list.isEmpty()) {
                hideSimilarSection()
            } else {
                binding.rvSimilar.apply {
                    layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                    setHasFixedSize(true)
                    addItemDecoration(
                        MarginItemDecoration(12)
                    )
                    adapter = MovieAdapter(list, null, MOVIE_THUMB_ITEM_TYPE)
                }
            }
        })

        viewModel.getEvents().observe(this, EventObserver {
            when (it) {
                is DetailedViewModel.ViewEvent.FinishedLoading -> {
                    hideLoading()
                    showDetails()
                }
                is DetailedViewModel.ViewEvent.SimilarMoviesApiError -> hideSimilarSection()
                is DetailedViewModel.ViewEvent.ShowError -> {
                    showError(it.message)
                }
            }.exhaustive
        })

        binding.btRetry.setOnClickListener {
            viewModel.retryLoading()
        }
    }

    companion object {
        fun getInstance(data: MovieData): MovieDetailFragment {
            return MovieDetailFragment().apply {
                movieData = data
            }
        }
    }

    private fun hideReviewSection() {
        binding.apply {
            tvReviewsTitle.visibility = GONE
            rvReviews.visibility = GONE
        }
    }

    private fun hideSimilarSection() {
        binding.apply {
            tvSimilarTo.visibility = GONE
            rvSimilar.visibility = GONE
        }
    }

    private fun hideLoading() {
        binding.apply {
            pbLoading.visibility = GONE
            tvScreenMsg.visibility = GONE
            btRetry.visibility = GONE
        }
    }

    private fun showDetails(){
        binding.clDetailsContainer.visibility = VISIBLE
    }

    private fun showError(message: String) {
        hideLoading()
        binding.apply {
            tvScreenMsg.visibility = VISIBLE
            tvScreenMsg.text = message
            btRetry.visibility = VISIBLE
        }
    }

}
