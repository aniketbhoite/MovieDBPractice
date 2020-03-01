package com.aniket.moviedbpractice.ui


import android.app.Activity
import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
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
import com.aniket.moviedbpractice.ui.MovieListAdapter.Companion.MOVIE_FULL_ITEM_TYPE
import com.aniket.moviedbpractice.util.exhaustive
import com.aniket.moviedbpractice.viewmodel.MovieListViewModel
import com.aniket.moviedbpractice.viewmodel.MovieListViewModelFactory


class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding

    private lateinit var movieAdapter: MovieListAdapter

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
        initToolBar()

        setHasOptionsMenu(true)
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

        setObservers()
    }

    private fun setObservers() {

        binding.rvMovieList.apply {
            visibility = VISIBLE
            movieAdapter = MovieListAdapter(viewModel, MOVIE_FULL_ITEM_TYPE)
            adapter = movieAdapter
        }

        viewModel.getNowPlayingMovies().observe(this, Observer {

            movieAdapter.submitListFilterable(it.toMutableList())

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

    private fun initToolBar() {
        binding.toolbar.setTitleTextColor(
            ContextCompat.getColor(
                activity!!.applicationContext,
                android.R.color.white
            )
        )
        binding.toolbar.inflateMenu(R.menu.list_screen_menu)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        (activity as AppCompatActivity).supportActionBar?.title = "Now Playing"
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
        activity?.let {
            hideKeyboard(it)
        }

        (context as FragmentActivity).supportFragmentManager.commit {
            addToBackStack(this@MovieListFragment::class.java.simpleName)
            add(
                R.id.container,
                MovieDetailFragment.getInstance(movieData),
                MovieDetailFragment.FRAGMENT_TAG
            )
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_screen_menu, menu)

        val search = menu.findItem(R.id.action_search)
        val searchView =
            search.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (::movieAdapter.isInitialized) {
                    movieAdapter.filter.filter(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (::movieAdapter.isInitialized) {
                    movieAdapter.filter.filter(query)
                }
                return true
            }
        })


    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        var view = activity.currentFocus

        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}
