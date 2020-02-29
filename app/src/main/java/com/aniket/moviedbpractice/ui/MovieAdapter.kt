package com.aniket.moviedbpractice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aniket.moviedbpractice.R
import com.aniket.moviedbpractice.databinding.ItemMovieBinding
import com.aniket.moviedbpractice.databinding.ItemMovieThumbBinding
import com.aniket.moviedbpractice.responses.MovieData
import com.aniket.moviedbpractice.viewmodel.MovieItemViewModel
import com.aniket.moviedbpractice.viewmodel.MovieListViewModel

class MovieAdapter(
    private val list: List<MovieData>,
    private val viewModel: MovieListViewModel?,
    private val itemType: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val MOVIE_FULL_ITEM_TYPE = 1001
        const val MOVIE_THUMB_ITEM_TYPE = 1002
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == MOVIE_FULL_ITEM_TYPE)
            MovieVH(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_movie, parent, false
                )
            )
        else
            MovieThumbVH(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_movie_thumb, parent, false
                )
            )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is MovieVH) {
            viewModel?.let {
                holder.binding.viewModel = MovieItemViewModel(
                    list[position], viewModel
                )
            }

        } else if (holder is MovieThumbVH) {
            holder.binding.movieData = list[position]
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return itemType
    }
}

class MovieVH(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

class MovieThumbVH(val binding: ItemMovieThumbBinding) : RecyclerView.ViewHolder(binding.root)