package com.aniket.moviedbpractice.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aniket.moviedbpractice.R
import com.aniket.moviedbpractice.databinding.FragmentMovieDetailBinding
import com.aniket.moviedbpractice.repositories.DetailedRepository
import com.aniket.moviedbpractice.responses.MovieData
import com.aniket.moviedbpractice.viewmodel.DetailedViewModel
import com.aniket.moviedbpractice.viewmodel.DetailedViewModelFactory


class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    private lateinit var movieData: MovieData

    private val viewModel: DetailedViewModel by viewModels {
        DetailedViewModelFactory(DetailedRepository())
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

        initView()

        return binding.root
    }

    private fun initView() {

    }

    companion object {
        fun getInstance(data: MovieData): MovieDetailFragment {
            return MovieDetailFragment().apply {
                movieData = data
            }
        }
    }


}
