package com.aniket.moviedbpractice.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aniket.moviedbpractice.R
import com.aniket.moviedbpractice.databinding.FragmentMovieListBinding
import com.aniket.moviedbpractice.repositories.MovieListReposirory
import com.aniket.moviedbpractice.viewmodel.MovieListViewModel
import com.aniket.moviedbpractice.viewmodel.MovieListViewModelFactory


class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding

    private val viewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory(MovieListReposirory())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)

        initView()

        return binding.root
    }

    fun initView() {
        binding.viewModel = viewModel
    }


}
