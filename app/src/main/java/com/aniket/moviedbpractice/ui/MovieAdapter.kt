package com.aniket.moviedbpractice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aniket.moviedbpractice.R
import com.aniket.moviedbpractice.databinding.ItemMovieBinding
import com.aniket.moviedbpractice.responses.MovieData

class MovieAdapter(private val list: List<MovieData>) : RecyclerView.Adapter<MovieVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie, parent, false
            )
        )

    override fun onBindViewHolder(holder: MovieVH, position: Int) {

    }

    override fun getItemCount() = list.size
}

class MovieVH(binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)