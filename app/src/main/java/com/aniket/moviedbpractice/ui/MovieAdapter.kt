package com.aniket.moviedbpractice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aniket.moviedbpractice.R
import com.aniket.moviedbpractice.databinding.ItemMovieBinding
import com.aniket.moviedbpractice.responses.MovieData
import com.aniket.moviedbpractice.viewmodel.MovieItemViewModel
import com.aniket.moviedbpractice.viewmodel.MovieListViewModel

class MovieAdapter(private val list: List<MovieData>,
                   private val viewModel: MovieListViewModel ) : RecyclerView.Adapter<MovieVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie, parent, false
            )
        )

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.binding.viewModel = MovieItemViewModel(
            list[position], viewModel
        )
    }

    override fun getItemCount() = list.size
}

class MovieVH(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)