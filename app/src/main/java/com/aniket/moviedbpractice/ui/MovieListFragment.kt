package com.aniket.moviedbpractice.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aniket.moviedbpractice.R
import com.aniket.moviedbpractice.databinding.FragmentMovieListBinding
import com.aniket.moviedbpractice.network.MovieApiClient
import com.aniket.moviedbpractice.repositories.MovieListRepository
import com.aniket.moviedbpractice.responses.MovieData
import com.aniket.moviedbpractice.responses.base.EventObserver
import com.aniket.moviedbpractice.util.exhaustive
import com.aniket.moviedbpractice.viewmodel.MovieListViewModel
import com.aniket.moviedbpractice.viewmodel.MovieListViewModelFactory


class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding

    private val viewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory(MovieListRepository(MovieApiClient.apiServices))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_list, container, false
        )

        binding.viewModel = viewModel


        initView()

        return binding.root
    }

    private fun initView() {

        binding.apply {
            rvMovieList.layoutManager = LinearLayoutManager(activity)
            btRetry.setOnClickListener {
                pbLoading.visibility = VISIBLE
                viewModel?.retryLoading()
            }
        }


        viewModel.getNowPlayingMovies().observe(this, Observer {

            binding.rvMovieList.apply {
                visibility = VISIBLE
                adapter = MovieAdapter(it, viewModel)
            }

        })

        viewModel.getEvents().observe(this, EventObserver {
            when (it) {
                is MovieListViewModel.ViewEvent.FinishedLoading -> {
                    hideLoading()
                }
                is MovieListViewModel.ViewEvent.ShowError -> {
                    showError(it.errorMsg)
                }
                is MovieListViewModel.ViewEvent.NavigateToDetail -> {
                    openDetailsScreen(it.movieData)
                }
            }.exhaustive
        })
    }

    private fun hideLoading() {
        binding.apply {
            tvScreenMsg.visibility = GONE
            pbLoading.visibility = GONE
            btRetry.visibility = GONE
        }
    }

    private fun showError(message: String) {
        hideLoading()
        binding.tvScreenMsg.apply {
            visibility = VISIBLE
            text = message

        }
        binding.btRetry.visibility = VISIBLE
    }

    private fun openDetailsScreen(movieData: MovieData) {
        (context as FragmentActivity).supportFragmentManager.commit {
            addToBackStack(this@MovieListFragment::class.java.simpleName)
            add(
                R.id.container,
                MovieDetailFragment.getInstance(movieData),
                null
            )
        }
    }


}
